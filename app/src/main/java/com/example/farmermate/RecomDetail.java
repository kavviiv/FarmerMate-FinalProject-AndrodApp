package com.example.farmermate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RecomDetail extends AppCompatActivity {
    RiceAdapter adapter; 

    String Name, Description;

    int position = 0  ;
    private List<Rice> mRiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_detail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");
//        Name = intent.getExtras().getString("Name");
//        Description = intent.getExtras().getString("Description");



       // final RiceAdapter adapter = new RiceAdapter(this);
        final ImageView imgV = (ImageView) findViewById(R.id.mainimg);
        TextView tvName = (TextView) findViewById(R.id.Name);
        TextView tvDescription = (TextView) findViewById(R.id.Description);

        imgV.setImageResource(R.drawable.riceim2);
//        tvName.setText(RiceAdapter.get(position).getName());
//        tvDescription.setText(RiceAdapter.get(position).getDescription());



    }


}