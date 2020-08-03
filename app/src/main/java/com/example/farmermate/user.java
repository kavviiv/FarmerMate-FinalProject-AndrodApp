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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

public class user extends AppCompatActivity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    View HomePage;
    String name, email;
    TextView name1,mail1;
    Uri uid;
    ImageView ur1;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "user";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name1 = (TextView) findViewById(R.id.name);
        mail1 = (TextView) findViewById(R.id.mail);
        ur1 = (ImageView) findViewById(R.id.ur);
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
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.edit);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();
                Uri photoUrl = profile.getPhotoUrl();
                ur1.setImageURI(photoUrl);

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                mail1.setText(name);

                String email = profile.getEmail();
                name1.setText(email);
            }
        }

        }

    public void updateProfile() {
        // [START update_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
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

