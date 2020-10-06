package com.example.farmermate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Calendar.MONTH;

public class CreateTable extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView dp,tv22,looc;
    Button createTable,clo;
    EditText size;
    Spinner selectRice;
    private Context context;
    public static int Day;
    public static String Step,Dtail,Rec,Warn;
    private ListView todoList;
    private ToDoAdapter madapter;
    private List<Todo> ToDoList;
    private DBHelper2 mDBHelper2;
    //private List<Rice> mRiceList;
    public static String Name1;
    public static String s1,r1,w1,s2,r2,w2,s3,r3,w3,s4,r4,w4,s5,r5,w5,s6,r6,w6,s7,r7,w7,s8,r8,w8,s9,r9,w9;
    public static String s10,r10,w10,s11,r11,w11,s12,r12,w12,s13,r13,w13,s14,r14,w14,s15,r15,w15,s16,r16,w16,s17,r17,w17,s18,r18,w18;
    public static String s19,r19,w19,s20,r20,w20,s21,r21,w21,s22,r22,w22,s23,r23,w23,s24,r24,w24,s25,r25,w25,s26,r26,w26;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);
        dp = (TextView) findViewById(R.id.datepick);
        createTable = (Button) findViewById(R.id.createTable);
        selectRice = (Spinner) findViewById(R.id.rice);
        clo = (Button) findViewById(R.id.CLocate);
        tv22 = (TextView) findViewById(R.id.tv22);
        size = (EditText)findViewById(R.id.sizze);
        looc = (TextView)findViewById(R.id.looc);



        looc.setText("ละติจูด: " + getIntent().getStringExtra("la")  + " ลองจิจูด: " + getIntent().getStringExtra("lo"));




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

                String size1 = size.getText().toString() ;
                String spinText = selectRice.getSelectedItem().toString();
                Intent intent = new Intent(CreateTable.this, MapsActivity.class);
                intent.putExtra("Rname",spinText);
                intent.putExtra("Size",size1);
                startActivity(intent);
            }
        });


        createTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectRice.getSelectedItem().equals("เลือกพันธุ์ข้าว")){
                    Toast.makeText(getApplicationContext(), "เลือกพันธุ์ข้าวที่ท่านต้องการจะปลูก", Toast.LENGTH_LONG).show();
                }
                else if( selectRice.getSelectedItem().equals("ข้าวดอกมะลิ 105")) {
                        String dat = dp.getText().toString();
                        String[] dateParts = dat.split("-");
                        String day = dateParts[0];
                        String month = dateParts[1];
                        String year = dateParts[2];
                        Intent intent = new Intent(CreateTable.this, CalendarAc.class);
                        intent.putExtra("Date", day);
                        intent.putExtra("Month", month);
                        intent.putExtra("Year", year);
                        startActivity(intent);
                    }
//                    setContentView(R.layout.todo_listview);
//                    todoList = (ListView) findViewById(R.id.todo_listview);
//                    mDBHelper2 = new DBHelper2(CreateTable.this);
//                    //return productList;
//                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
//                    if (false == database.exists()) {
//                        mDBHelper2.getReadableDatabase();
//                        //Copy db
//                        if (copyDatabase(CreateTable.this)) {
//                            Toast.makeText(CreateTable.this, "Copy database succes", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(CreateTable.this, "Copy data error", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//                    //Get product list in db when db exists
//                   ToDoList = mDBHelper2.getToDoList0();
//                    //Init adapter
//                    madapter = new ToDoAdapter(CreateTable.this, ToDoList);
//                    //Set adapter for listview
//                    todoList.setAdapter(madapter);

                else if( selectRice.getSelectedItem().equals("สันป่าตอง")){
                    String dat = dp.getText().toString();
                    String [] dateParts = dat.split("-");
                    String day = dateParts[0];
                    String month = dateParts[1];
                    String year = dateParts[2];
                    Intent intent = new Intent(CreateTable.this, CalendarAc1.class);
                    intent.putExtra("Date",day);
                    intent.putExtra("Month",month);
                    intent.putExtra("Year",year);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(CreateTable.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }

//                todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String Step = ToDoList.get(position).getStep();
//                        String Dtail = ToDoList.get(position).getDtail();
//                        String Rec = ToDoList.get(position).getRec();
//                        String Warn = ToDoList.get(position).getWarn();
//                        Intent intent = new Intent(getApplicationContext() ,TablePage.class);
//                        intent.putExtra("Position", position);
//                        intent.putExtra("Step", Step);
//                        intent.putExtra("Dtail", Dtail);
//                        intent.putExtra("Rec", Rec);
//                        intent.putExtra("Warn",Warn);
//                        startActivity(intent);
//                    }
//                });

                }


            private boolean copyDatabase(Context context) {
                try {
                    InputStream inputStream = context.getAssets().open(DBHelper.DBNAME);
                    String outFileName = DBHelper.DBLOCATION + DBHelper.DBNAME;
                    OutputStream outputStream = new FileOutputStream(outFileName);
                    byte[] buff = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(buff)) > 0) {
                        outputStream.write(buff, 0, length);
                    }
                    outputStream.flush();
                    outputStream.close();
                    Log.w("MainActivity", "DB copied");
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
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
    public void onDateSet(DatePicker view, int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        int a = month + 1 ;
        c.set(MONTH, a);
        int b;
        c.set(Calendar.DATE, date);
        int aa = date;

        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        TextView dp = (TextView) findViewById(R.id.datepick);
        //dp.setText(currentDateString);
        dp.setText(date + "-" + a  +"-" + year);
        String crd = dp.getText().toString();

        Intent intent = new Intent(getApplicationContext() , CalendarAc.class);
        intent.putExtra("StartDate", crd);
        intent.putExtra("aaa", aa);
    }

}