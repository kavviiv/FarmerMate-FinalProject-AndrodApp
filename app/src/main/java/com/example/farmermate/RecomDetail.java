package com.example.farmermate;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RecomDetail extends AppCompatActivity {
    RiceAdapter adapter; 

    String Name, Description,Products,Advantage;
    TextView tvName, tvDescription,Pro,Products1,Ad,Advantage1;

    int position = 0  ;
    private List<Rice> mRiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_detail);
        tvName = (TextView) findViewById(R.id.Name);
        tvDescription = (TextView) findViewById(R.id.Description);
        String Name = getIntent().getStringExtra("Name");
        String Description = getIntent().getStringExtra("Description");
        tvName.setText(Name);
        tvDescription.setText(Description);

        Pro = (TextView) findViewById(R.id.Pro);
        Products1 = (TextView) findViewById(R.id.Products);
        String Products = getIntent().getStringExtra("Products");
        Pro.setText("ผลผลิต");
        Products1.setText(Products);

        Ad = (TextView) findViewById(R.id.Ad);
        Advantage1 = (TextView) findViewById(R.id.Advantage);
        String Advantage = getIntent().getStringExtra("Advantage");
        Ad.setText("จุดเด่น");
        Advantage1.setText(Advantage);

//        Intent intent = getIntent();
//        position = intent.getExtras().getInt("Position");
////        Name = intent.getExtras().getString("Name");
////        Description = intent.getExtras().getString("Description");
//
//
//
//       // final RiceAdapter adapter = new RiceAdapter(this);
//        final ImageView imgV = (ImageView) findViewById(R.id.mainimg);
//        TextView tvName = (TextView) findViewById(R.id.Name);
//        TextView tvDescription = (TextView) findViewById(R.id.Description);
//
//        imgV.setImageResource(R.drawable.riceim2);
////        tvName.setText(RiceAdapter.get(position).getName());
////        tvDescription.setText(RiceAdapter.get(position).getDescription());



    }


}