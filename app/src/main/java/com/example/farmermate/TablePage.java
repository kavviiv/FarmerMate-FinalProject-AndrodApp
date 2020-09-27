package com.example.farmermate;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TablePage extends AppCompatActivity {

    ToDoAdapter madapter;
    String Name, Description,Products,Advantage;
    TextView tvName, tvDescription,Pro,Products1,Ad,Advantage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_page);
        tvName = (TextView) findViewById(R.id.Name);
        tvDescription = (TextView) findViewById(R.id.work);
        String Name = getIntent().getStringExtra("Name");
        String Description = getIntent().getStringExtra("Name1");
        tvName.setText(Name);
        tvDescription.setText(Description);

//        Pro = (TextView) findViewById(R.id.Pro);
//        Products1 = (TextView) findViewById(R.id.Products);
//        String Products = getIntent().getStringExtra("Products");
//        Pro.setText("ผลผลิต");
//        Products1.setText(Products);
//
//        Ad = (TextView) findViewById(R.id.Ad);
//        Advantage1 = (TextView) findViewById(R.id.Advantage);
//        String Advantage = getIntent().getStringExtra("Advantage");
//        Ad.setText("จุดเด่น");
//        Advantage1.setText(Advantage);
    }
}