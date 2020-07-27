package com.example.farmermate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiceDescriptionPage extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[], s3[];
    int image[] = {R.drawable.riceim1, R.drawable.riceim2, R.drawable.rice3,R.drawable.rice4,R.drawable.rice5,
            R.drawable.rice6,R.drawable.rice7,R.drawable.rice8,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_description_page);

        recyclerView = findViewById(R.id.rice_rec);

        s1 = getResources().getStringArray(R.array.rice_name);
        s2 = getResources().getStringArray(R.array.rice_desc);
        s3 = getResources().getStringArray(R.array.rice_descrip);


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, s1,s2,s3, image);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}