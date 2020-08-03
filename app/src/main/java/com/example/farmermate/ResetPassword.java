package com.example.farmermate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    Button re;
    EditText uemail;
    private static final String TAG = "ResetPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Button re = (Button) findViewById(R.id.repass);
        uemail = findViewById(R.id.maill);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uemail.getText().toString() ;
                //------------------------------------------------------------------
                FirebaseAuth.getInstance().sendPasswordResetEmail(String.valueOf(email))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Email sent.");//55
                        openDialogBox();
                    }
                    else {
                        String mess = task.getException().getMessage();
                        Toast.makeText(ResetPassword.this, "Error: "+ mess, Toast.LENGTH_SHORT).show();

                    }
                }
            });
            }
        });

    }
    public void openDialogBox() {
        DialogBox exampleDialog = new DialogBox();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

}