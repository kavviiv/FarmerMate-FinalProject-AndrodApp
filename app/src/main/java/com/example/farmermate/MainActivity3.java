package com.example.farmermate;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    Typeface weatherFont;

    FusedLocationProviderClient mFusedLocationClient;

    TextView tvCityName;
    TextView tvWeatherIcon;
    TextView tvWeatherInfo;
    TextView tvTemperature;
    TextView tvLastUpdatedInfo;
    SwipeRefreshLayout mySwipeRefreshLayout;

    Task<Location> task;
    Location currentLocation;
    Double[] currentCoordinates = new Double[2]; // 0 is lat, 1 is lon
    private static final String CHANNEL_ID = "rain_notif_channel";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static String PREF_KEY_TIME = "pref_key_time";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvCityName = (TextView) findViewById(R.id.tv_city);
        tvWeatherIcon = (TextView) findViewById(R.id.tv_weather_icon);
        tvWeatherInfo = (TextView) findViewById(R.id.tv_weather_info);
        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
        tvLastUpdatedInfo = (TextView) findViewById(R.id.tv_last_updated_info);

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        //get instance of Fused Location Provider Client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons.ttf");
        tvWeatherIcon.setTypeface(weatherFont);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("onRefresh", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        getWeatherData();
                    }
                }
        );

        myCheckPermissions();
        getWeatherData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWeatherData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.settings_action:
                Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID);
                double[] unboxCoordinates = {currentCoordinates[0], currentCoordinates[1]};
                intent.putExtra(AlarmReceiver1.RECEIVER_COORDINATES, unboxCoordinates);
                intent.setClass(this, SettingActivity1.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void myCheckPermissions() {
        //check permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                } else {
                    finish();
                }
            }
        }
    }

    public void getWeatherData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        task = mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity3.this,
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        currentLocation = task.getResult();
                        currentCoordinates[0] = currentLocation.getLatitude();
                        currentCoordinates[1] = currentLocation.getLongitude();
                        new FetchWeatherData().execute(currentCoordinates);
                    }
                });
    }

    class FetchWeatherData extends AsyncTask<Double[], Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            mySwipeRefreshLayout.setRefreshing(true);
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(Double[]... params) {
            URL requestUrl = NetworkUtilities.buildUrl(NetworkUtilities.CURRENT_WEATHER_URL,
                    params[0]);

            try{
                String jsonStringResponse = NetworkUtilities.getResponseFromHttpUrl(requestUrl);
                JSONObject jsonResponseObject =  new JSONObject(jsonStringResponse);
                if (jsonResponseObject.getInt("cod") != 200) {
                    return null;
                }
                return jsonResponseObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject weatherData) {
            try {
                tvCityName.setText(weatherData.getString("name").toUpperCase(Locale.US) +
                        ", " + weatherData.getJSONObject("sys").getString("country"));

                tvWeatherInfo.setText(weatherData.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description").toUpperCase(Locale.US));

                tvTemperature.setText(String.format("%.2f", weatherData.getJSONObject("main")
                        .getDouble("temp"))+ " ℃");

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String updatedOn = df.format(new Date(weatherData.getLong("dt")*1000));
                tvLastUpdatedInfo.setText("Last Updated: " + updatedOn);

                setWeatherIcon(weatherData.getJSONArray("weather")
                                .getJSONObject(0).getInt("id"),
                        weatherData.getJSONObject("sys").getLong("sunrise") * 1000,
                        weatherData.getJSONObject("sys").getLong("sunset") * 1000);

                mySwipeRefreshLayout.setRefreshing(false);
            } catch(Exception e) {
                Log.e("onPostExecute error", "One or more fields not found in JSON data");
            }
        }
    }

    private void setWeatherIcon(int id, long sunrise, long sunset){
        String weatherCode = "wi_owm_";
        long currentTime = new Date().getTime();
        if(currentTime>=sunrise && currentTime<sunset) {
            weatherCode += "day_";
        } else {
            weatherCode += "night_";
        }
        weatherCode += id;
        int stringId = getResources().getIdentifier(weatherCode, "string", this.getPackageName());
        tvWeatherIcon.setText(getString(stringId));
    }

}