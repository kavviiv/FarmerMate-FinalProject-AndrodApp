package com.example.farmermate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

public class AddActivity_ex extends AppCompatActivity{
    EditText amount,notes;
    Spinner cat;
    ExpenseTable_ex expenseTableEx;
    Button save;
    String Catitem;
    Cursor c;
    String[] category={"ปุ๋ย","ค่าแรงงาน","ค่าบำรุงรักษา","ค่าอุปกรณ์เครื่องมือ","ค่าสารกําจัดวัชพืช","ค่าสารกําจัดศัตรูพืช","อื่นๆ"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ex );
        expenseTableEx =new ExpenseTable_ex(this);
        amount=(EditText)findViewById(R.id.editText_amount);
        notes=(EditText)findViewById(R.id.editText_notes);
        cat=(Spinner)findViewById(R.id.spinner_cat);
        save=(Button)findViewById(R.id.button_save);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,category);
        cat.setAdapter(adapter);
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Catitem=category[i];
//                switch (i){
//                    case 0:
//                        Catitem=category[i];
//                        break;
//                    case 1:
//                        Catitem=category[1];
//                        break;
//                    case 2:
//                        Catitem=category[2];
//                        break;
//                    case 3:
//                        Catitem=category[3];
//                        break;
//                    case 4:
//                        Catitem=category[4];
//                        break;
//                    case 5:
//                        Catitem=category[5];
//                        break;
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent i = getIntent();
        int option = i.getIntExtra("edit",100);
        if (option == 1) {
            int position = i.getIntExtra("position", 0);
            expenseTableEx.opendb();
            c = expenseTableEx.getallrecords();
            c.moveToPosition(position);
            final String crowid=c.getString(0);
            String camount = c.getString(1);
            String ccat = c.getString(2);
            String cnotes=c.getString(3);
            amount.setText(camount);
            amount.setSelection(amount.getText().length());
            notes.setText(cnotes);
            notes.setSelection(notes.getText().length());
            save.setText("update");

            save.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {

                    String samount = amount.getText().toString();
                    String snotes = notes.getText().toString();
                    Date date = new Date(System.currentTimeMillis());
                    expenseTableEx.opendb();
                    expenseTableEx.update(crowid, samount,Catitem, snotes, DateFormat.getDateTimeInstance().format(date));
                    Toast.makeText(getApplicationContext(), "อัพเดท", Toast.LENGTH_LONG).show();
                    finish();

                }
            });
        }
        else {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String samount = amount.getText().toString();
                    String snotes = notes.getText().toString();
                    Date date = new Date(System.currentTimeMillis());
                    expenseTableEx.opendb();
                    expenseTableEx.insertrecord(samount, Catitem, snotes, DateFormat.getDateTimeInstance().format(date));
                    Toast.makeText(getApplicationContext(), " บันทึก", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }

    }
}
