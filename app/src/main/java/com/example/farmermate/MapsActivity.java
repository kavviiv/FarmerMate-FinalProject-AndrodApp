package com.example.farmermate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ConstraintLayout constraintLayout;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    private GoogleMap mMap;
    ConstraintLayout l1;
    TextView dp,tv22;
    Button createTable,clo;
    private static final String TAG = "CurrentLocationApp";
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private TextView mLatitudeText;
    private TextView mLongitudeText;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mLocationDatabaseReference;
    Button saveLocationToFirebase;
    String value_lat = null;
    String value_lng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLocationDatabaseReference = mFirebaseDatabase.getReference().child("my current location");
        mLatitudeText = (TextView) findViewById((R.id.latitude_text1));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text1));
        saveLocationToFirebase = (Button) findViewById(R.id.save_location1);
        clo = (Button) findViewById(R.id.CLocate);

        buildGoogleApiClient();

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
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
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {

            value_lat= String.valueOf(mLastLocation.getLatitude());
            value_lng =String.valueOf(mLastLocation.getLongitude());
            mLatitudeText.setText(value_lat);
            mLongitudeText.setText(value_lng);

            saveLocationToFirebase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLocationDatabaseReference.push().setValue("Latitude : "+value_lat +"  &amp; Longitude : "+value_lng);
                    Toast.makeText(MapsActivity.this ,"Location saved to the Firebasedatabase",Toast.LENGTH_LONG).show();
                    MapsActivity.this.finish();
//                    clo.setVisibility(View.GONE);
//                    tv22.setText("บันทึกพิ้นที่นาแล้ว");
//                    tv22.setVisibility(View.VISIBLE);
                }
            });
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }




    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener( new OnSuccessListener< Location >() {
            @Override
            public void onSuccess( final Location location) {

                if (location != null){

                    supportMapFragment.getMapAsync( new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            LatLng latLng = new LatLng(location.getLatitude()
                                    , location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title( "ที่อยู่ปัจจุบัน" );
                            googleMap.animateCamera(  CameraUpdateFactory.newLatLngZoom( latLng,10 ));
                            googleMap.addMarker(options);

                        }
                    } );
                }

            }
        } );
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}