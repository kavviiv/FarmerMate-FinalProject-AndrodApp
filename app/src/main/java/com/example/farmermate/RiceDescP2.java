package com.example.farmermate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiceDescP2 extends AppCompatActivity {
    RecyclerView recyclerView2;
    String s3[],s4[], s5[];
    int image[] = {R.drawable.rice11,R.drawable.rice12,R.drawable.rice13,R.drawable.rice14,R.drawable.rice15,R.drawable.rice16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_desc_p2);

        recyclerView2 = findViewById(R.id.rice_rec2);

        s3 = getResources().getStringArray(R.array.rice_name2);
        s4 = getResources().getStringArray(R.array.rice_desc);
        s5 = getResources().getStringArray(R.array.rice_descrip2);


        RecyclerAdapter2 recyclerAdapter2 = new RecyclerAdapter2(this, s3,s4,s5, image);
        recyclerView2.setAdapter(recyclerAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


    }


    }
