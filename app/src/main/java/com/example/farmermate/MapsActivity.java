package com.example.farmermate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity < MarkerList > extends AppCompatActivity implements OnMapReadyCallback  {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.google_map );

        LocationHelper location = new LocationHelper();
        LocationHelper helper = new LocationHelper(
                location.getLongitude(),
                location.getLatitude()
        );

        FirebaseDatabase.getInstance().getReference("Current Location")
                .setValue( helper ).addOnCompleteListener( new OnCompleteListener< Void >() {
            @Override
            public void onComplete(@NonNull Task< Void > task) {
                if (task.isSuccessful()){
                    Toast.makeText( MapsActivity.this,"Location Saved",Toast.LENGTH_SHORT );
                }
                else {
                    Toast.makeText( MapsActivity.this,"Location Not Saved" ,Toast.LENGTH_SHORT).show();

                }

            }
        } );

        client = LocationServices.getFusedLocationProviderClient( this );


        if (ActivityCompat.checkSelfPermission( MapsActivity.this,
                ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();


        }else  {
            ActivityCompat.requestPermissions( MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44 );

        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getCurrentLocation();

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}