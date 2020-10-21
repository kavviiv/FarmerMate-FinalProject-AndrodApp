package com.example.farmermate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class user extends AppCompatActivity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    String fauhh;
    FirebaseUser firebase;
    View HomePage;
    String name, email;
    TextView name1,mail1;
    Uri uid;
    ImageView ur;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name1 = (TextView) findViewById(R.id.name);
        mail1 = (TextView) findViewById(R.id.mail);

        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(user.this, MainPage.class);
                startActivity(I);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.homep);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user.this, HomePage.class));
            }
        });



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();
                String uid = profile.getUid();

                String email = profile.getEmail();
                mail1.setText(email);
            }
        }

       fauhh = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        DatabaseReference df = db.getReference("User Data");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child(fauhh).child("UserName").getValue().toString();
                name1.setText(name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data");
//        ValueEventListener listener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
//                for (final DataSnapshot child : dataSnapshot.getChildren()) {
//                    String Uname = child.child(fauhh).child("UserName").getValue().toString();
//                    name1.setText(Uname);
//                }
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        }

    public void updateProfile() {
        // [START update_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("User's Username")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

    }}

