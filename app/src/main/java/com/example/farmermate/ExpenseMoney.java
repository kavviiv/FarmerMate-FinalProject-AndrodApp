package com.example.farmermate;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.*;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.*;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ExpenseMoney extends AppCompatActivity {
    ListView listView;
    Cursor c;
    ExpenseTable_ex expenseTableEx;
    Toolbar mActionBarToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ex );
        expenseTableEx =new ExpenseTable_ex(this);
        expenseTableEx.opendb();
        listView=(ListView)findViewById(R.id.listview);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        //getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent( ExpenseMoney.this, AddActivity_ex.class);
                startActivity(i);
            }
        });

        setadapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent( ExpenseMoney.this, ViewActivity_ex.class);
                in.putExtra("position",i);
                startActivity(in);
            }
        });

    }
    private void setadapter(){
        c= expenseTableEx.getallrecords();
        CustomAdaptor ca = new CustomAdaptor(this);
        listView.setAdapter(ca);
    }



    class CustomAdaptor extends BaseAdapter {
        private Context mcontext;
        public CustomAdaptor(Context c){
            mcontext=c;
        }
        @Override
        public int getCount() {
            return c.getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            c.moveToPosition(i);
            String samount=c.getString(1);
            String scat=c.getString(2);
            String sdate=c.getString(4);


            view=getLayoutInflater().inflate(R.layout.customlayout_ex,null);
            TextView amount=(TextView)view.findViewById(R.id.textView_amount);
            TextView cat=(TextView)view.findViewById(R.id.textView_cat);
            TextView date=(TextView)view.findViewById(R.id.textView_date);
            amount.setText("จำนวนเงินที่ใช้ "+samount +"บาท");
            cat.setText("ประเภท :"+scat);
            date.setText(sdate);
            return view;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setadapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_ex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteall) {
            AlertDialog.Builder b=new AlertDialog.Builder( ExpenseMoney.this);
            b.setTitle("ลบ ");
            b.setMessage("คุณแน่ใจไหม?");
            b.setNegativeButton("ไม่",null);
            b.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    expenseTableEx.deleteallrecords();
                    Toast.makeText(getApplicationContext(),"ลบบันทึกทั้งหมดแล้ว",Toast.LENGTH_SHORT).show();
                    setadapter();
                }
            });
            b.show();
        }
        return super.onOptionsItemSelected(item);
    }

}