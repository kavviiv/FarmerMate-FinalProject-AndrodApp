package com.example.farmermate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RiceDetail extends AppCompatActivity {
    ImageView mainimg;
    TextView title, detail;

    String data1, data3;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_detail);

        mainimg = findViewById(R.id.mainimg);
        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data3")){

            data1 = getIntent().getStringExtra("data1");
            data3 = getIntent().getStringExtra("data3");
            myImage = getIntent().getIntExtra("myImage", 1);



        }else {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }

    }
    private void setData(){
        title.setText(data1);
        detail.setText(data3);
        mainimg.setImageResource(myImage);
    }



}