package com.example.farmermate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RiceDetail2 extends AppCompatActivity {
    ImageView mainimg;
    TextView title, detail;

    String data3, data5;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_detail2);

        mainimg = findViewById(R.id.mainimg);
        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("data3") && getIntent().hasExtra("data5")){

            data3 = getIntent().getStringExtra("data3");
            data5 = getIntent().getStringExtra("data5");
            myImage = getIntent().getIntExtra("myImage", 1);

        }else {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }

    }
    private void setData(){
        title.setText(data3);
        detail.setText(data5);
        mainimg.setImageResource(myImage);
    }
}