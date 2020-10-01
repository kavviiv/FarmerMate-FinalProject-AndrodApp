package com.example.farmermate;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Calendar extends AppCompatActivity {
    private List<Todo> ToDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView ca = (CalendarView)findViewById(R.id.calendarView);
    }

}