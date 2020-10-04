package com.example.farmermate;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class RetrieveMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    FirebaseAuth fauth;
    //private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.push().setValue(marker);

        //firebase = new Firebase(FIREBASE_URL).child("users");


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Location");

        ValueEventListener listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String lat = child.child("Latitude").getValue().toString();
                    String lng = child.child("Longtitude").getValue().toString();
                    String Rname = child.child("RiceName").getValue().toString();
                    String Size = child.child("Size").getValue().toString();



                    double latitude = Double.parseDouble(lat);
                    double longitude = Double.parseDouble(lng);
                    LatLng location = new LatLng(latitude, longitude);

//                Double latitude = dataSnapshot.child("Latitude").getValue(Double.class);
//                Double longitude = dataSnapshot.child("Longtitude").getValue(Double.class);
//
//                LatLng location = new LatLng(latitude,longitude);

                    mMap.addMarker(new MarkerOptions().position(location).title(Rname +" "+ "จำนวน"+ " " + Size + " " +"ไร่"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14F));

                    Circle circle = mMap.addCircle(
                            new CircleOptions()
                                    .center(location)
                                    .radius(100)
                                    .strokeWidth(0f)
                                    .fillColor(0x66aaaFFF)
                    );


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private String getCOmpleteAddress(double Latitude, double Longtitude){

       String address = "";

       Geocoder geocoder = new Geocoder(RetrieveMapActivity.this, Locale.getDefault());

       try{

           List<Address> addresses = geocoder.getFromLocation(Latitude,Longtitude,1);

           if(address!=null){

               Address returnAddress = addresses.get(0);
               StringBuilder stringBuilderReturnAddress =  new StringBuilder("");

               for(int i=0; i<=returnAddress.getMaxAddressLineIndex();i++){
                   stringBuilderReturnAddress.append(returnAddress.getAddressLine(i)).append("\n");
               }

               address = stringBuilderReturnAddress.toString();

           }
           else{
               Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show();
           }

       }
       catch (Exception e){
           Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
       }
        return address;
    }

}
