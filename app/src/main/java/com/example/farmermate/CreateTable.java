package com.example.farmermate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateTable extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView dp,tv22;
    Button createTable,clo;
    Spinner selectRice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);
        dp = (TextView) findViewById(R.id.datepick);
        createTable = (Button) findViewById(R.id.createTable);
        selectRice = (Spinner) findViewById(R.id.rice);
        clo = (Button) findViewById(R.id.CLocate);
        tv22 = (TextView) findViewById(R.id.tv22);




        selectRice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String RiceName = selectRice.getSelectedItem().toString();

                Log.e("Selected item : ", RiceName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        tv22.setVisibility(View.GONE);


        clo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinText = selectRice.getSelectedItem().toString();
                Intent intent = new Intent(CreateTable.this, MapsActivity.class);
                intent.putExtra("Rname",spinText);
                startActivity(intent);
            }
        });


        createTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectRice.getSelectedItem().equals("")){
                }
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePick();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

















    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView dp = (TextView) findViewById(R.id.datepick);
        dp.setText(currentDateString);
    }




}