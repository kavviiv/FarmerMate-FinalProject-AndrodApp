package com.example.farmermate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiceDescP2 extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[], s3[];
    int image[] = {R.drawable.rice11,R.drawable.rice12,R.drawable.rice13,R.drawable.rice14,R.drawable.rice15,R.drawable.rice16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_desc_p2);

        recyclerView = findViewById(R.id.rice_rec);

        s1 = getResources().getStringArray(R.array.rice_name);
        s2 = getResources().getStringArray(R.array.rice_desc);
        s3 = getResources().getStringArray(R.array.rice_descrip);


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, s1,s2,s3, image);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    }
