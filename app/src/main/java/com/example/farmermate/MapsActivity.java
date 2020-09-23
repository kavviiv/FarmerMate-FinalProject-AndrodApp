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

import androidx.annotation.NonNull;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

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
    FirebaseFirestore db ;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLocationDatabaseReference = mFirebaseDatabase.getReference().child("my current location");
        mLatitudeText = (TextView) findViewById((R.id.latitude_text1));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text1));
        saveLocationToFirebase = (Button) findViewById(R.id.save_location1);
        clo = (Button) findViewById(R.id.CLocate);
       // FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        buildGoogleApiClient();

        client = LocationServices.getFusedLocationProviderClient( this );
//
//
//        if (ActivityCompat.checkSelfPermission( MapsActivity.this,
//                ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
//
//            getCurrentLocation();
//
//
//        }else  {
//            ActivityCompat.requestPermissions( MapsActivity.this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44 );
//
//        }

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




//                    Map<String, Object> user = new HashMap<>();
//                    user.put("Latitude", mLastLocation.getLatitude());
//                    user.put("Longtitude", mLastLocation.getLongitude());
//                    db.collection("users")
//                            .add(user)
//                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                @Override
//                                public void onSuccess(DocumentReference documentReference) {
//                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.w(TAG, "Error adding document", e);
//                                }
//                            });
                    //mLocationDatabaseReference.push().setValue("Latitude : "+value_lat +"  &amp; Longitude : "+value_lng);
                    Toast.makeText(MapsActivity.this, "Location saved to the Firebasedatabase", Toast.LENGTH_LONG).show();
                    MapsActivity.this.finish();

                }
            });}
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


//
//
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
//             TODO: Consider calling
//                ActivityCompat#requestPermissions
//             here to request the missing permissions, and then overriding
//               public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                      int[] grantResults)
//             to handle the case where the user grants the permission. See the documentation
//             for ActivityCompat#requestPermissions for more details.
            return;
        }
//        Task<Location> task = client.getLastLocation();
//        task.addOnSuccessListener( new OnSuccessListener< Location >() {
//            @Override
//            public void onSuccess( final Location location) {
//
//                if (location != null){
//
//                    supportMapFragment.getMapAsync( new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            LatLng latLng = new LatLng(location.getLatitude()
//                                    , location.getLongitude());
//                            MarkerOptions options = new MarkerOptions().position(latLng)
//                                    .title( "ที่อยู่ปัจจุบัน" );
//                            googleMap.animateCamera(  CameraUpdateFactory.newLatLngZoom( latLng,10 ));
//                            googleMap.addMarker(options);
//
//                        }
//                    } );
//                }
//
//            }
//        } );
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
    public void onMapReady(final GoogleMap googleMap) {
        if (mLastLocation != null)
        {
                LatLng latLng = new LatLng(mLastLocation.getLatitude()
                        , mLastLocation.getLongitude());
                MarkerOptions options = new MarkerOptions().position(latLng)
                        .title( "ที่อยู่ปัจจุบัน" );
                googleMap.animateCamera(  CameraUpdateFactory.newLatLngZoom( latLng,10 ));
                googleMap.addMarker(options);
        }
//
//        mMap = googleMap;
//        mLocationDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
//
//        mLocationDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                        //LatLng newLocation = new LatLng(
//                        // ds.child("latitude").getValue(Double.class),
//                        //ds.child("longtitude").getValue(Double.class));
//
//                        if (mLastLocation != null) {
//                            double mylat = mLastLocation.getLatitude();
//                            double mylong = mLastLocation.getLongitude();
//
//                            String exTitle = ds.child("exerciseType").getValue(String.class);
//
//                            LatLng newLocation = new LatLng(mylat, mylong);
//
//                            Log.d("New Location: ", newLocation.toString());
//
//                            //refreshes the map
//                            mMap.clear();
//
//                            MarkerOptions markerOptions = new MarkerOptions();
//                            markerOptions.position(newLocation);
//                            markerOptions.title("ที่นาของคุณ");
//                           // markerOptions.visible(false);
//
//                            Marker locationMarker = mMap.addMarker(markerOptions);
//
//                           googleMap.addMarker(markerOptions);
//
//                            LatLng myLatLang = new LatLng(mylat, mylong);
//                            Log.d("My Location: ", myLatLang.toString());
//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLang, 10.2f));
////                            if (SphericalUtil.computeDistanceBetween(myLatLang, locationMarker.getPosition()) < 1000000) {
////                                locationMarker.setVisible(true);
////                            }
//
//                           // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLang, 10.2f));
//
//
//                        }
//                    }
//                }

            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

}
