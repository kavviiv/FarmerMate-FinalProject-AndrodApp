package com.example.farmermate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.CalendarView;

import java.util.Calendar;

public class CalendV extends AppCompatActivity {
    private CalendarView ca ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calend_v);
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        ca = (CalendarView)findViewById(R.id.calendarView2);



    }
}