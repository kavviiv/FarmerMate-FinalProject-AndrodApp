package com.example.farmermate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TableP extends AppCompatActivity {
    View view8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_p);

        view8 = (View)findViewById(R.id.view8);
        view8.setVisibility(View.INVISIBLE);
        view8.setOnClickListener(new
                                         View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 startActivity(new Intent(TableP.this, CalendarAc.class));
                                             }
                                         });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view8.setVisibility(View.VISIBLE);
                startActivity(new Intent(TableP.this, CreateTable.class));
            }
        });
    }
}