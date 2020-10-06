package com.example.farmermate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity_ex extends AppCompatActivity {
    TextView amount,cat,notes,date,notestitle;
    Button delete,edit;
    int position;
    ExpenseTable_ex expenseTableEx;
    String samount,scat,snotes,rowid,sdate;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ex );
        amount=(TextView)findViewById(R.id.textView_amount);
        cat=(TextView)findViewById(R.id.textView_cat);
        notes=(TextView)findViewById(R.id.textView_notes);
        notestitle=(TextView)findViewById(R.id.textView_notestitle);
        date=(TextView)findViewById(R.id.textView_date);

        delete=(Button)findViewById(R.id.button_delete);
        edit=(Button)findViewById(R.id.button_edit);

        Intent intent=getIntent();
        position= intent.getIntExtra("ตำแหน่ง",0);
        expenseTableEx =new ExpenseTable_ex(this);
        expenseTableEx.opendb();
        setdata();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b=new AlertDialog.Builder( ViewActivity_ex.this);
                b.setTitle("ลบ ");
                b.setMessage("คุณแน่ใจไหม?");
                b.setNegativeButton("ไม่",null);
                b.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        expenseTableEx.deleterecord(rowid);
                        Toast.makeText(getApplicationContext(),"ลบ",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                b.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent( ViewActivity_ex.this, AddActivity_ex.class);
                i.putExtra("แกเ้ไข",1);
                i.putExtra("ตำแหน่ง",position);
                startActivity(i);
            }
        });


    }
    public void setdata(){
        c= expenseTableEx.getallrecords();
        c.moveToPosition(position);
        rowid=c.getString(0);
        samount= c.getString(1);
        scat=c.getString(2);
        snotes=c.getString(3);
        sdate=c.getString(4);

        amount.setText(samount + "  บาท");
        cat.setText(scat);
        if (!snotes.isEmpty()){
            notestitle.setText("โน๊ต");
            notes.setText(snotes);
        }
        else {
            notestitle.setText(null);
            notes.setText(null);
        }
        date.setText(sdate);
    }

    @Override
    protected void onRestart() {
        setdata();
        super.onRestart();
    }
}

