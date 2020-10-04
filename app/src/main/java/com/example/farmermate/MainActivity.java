package com.example.farmermate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public EditText Username,emailId, passwd, cpasswd;
    Button btnSignUp,fbl;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    String Fauth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
//        Fauth = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Username = findViewById(R.id.username);
        emailId = findViewById(R.id.ETemail);
        passwd = findViewById(R.id.ETpassword);
        cpasswd = findViewById(R.id.ETpassword1);
        btnSignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.TVSignIn);
        btnSignUp.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                final String uname = Username.getText().toString();
                final String emailID = emailId.getText().toString();
                String paswd = passwd.getText().toString();
                String cpaswd = cpasswd.getText().toString();
                if (uname.isEmpty()){
                    Username.setError("กรุณากรอกชื่อผู้ใช้");
                    Username.requestFocus();
                }
                else if (emailID.isEmpty()) {
                    emailId.setError("กรุณากรอกอีเมลล์");
                    emailId.requestFocus();
                }else if (paswd.isEmpty()) {
                    passwd.setError("กรุณากรอกรหัสผ่าน");
                    passwd.requestFocus();
                }else if (cpaswd.isEmpty()) {
                    cpasswd.setError("กรุณายืนยันรหัสผ่าน");
                    cpasswd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty() && cpaswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูล", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty()) && paswd.equals(cpaswd)) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Map<String, Object> user = new HashMap<>();
                                user.put("UserEmail", emailID);
                                user.put("UserName", uname);
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("User Data").child(FirebaseAuth.getInstance().getUid()).setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Write was successful!
                                                // ...
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Write failed
                                                // ...
                                            }
                                        });

                                String un = Username.getText().toString();
                                startActivity(new Intent(MainActivity.this, HomePage.class));
                            }
                        }
                    });
                } else if (!(emailID.isEmpty() && paswd.isEmpty()) && !(paswd.equals(cpaswd))){
                    cpasswd.setError("กรุณายืนยันรหัสผ่านอีกครั้ง");
                }
                else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(I);
            }
        });

    }
}