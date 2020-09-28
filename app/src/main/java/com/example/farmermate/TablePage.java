package com.example.farmermate;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TablePage extends AppCompatActivity {

    ToDoAdapter madapter;
    String Name, Description,Products,Advantage;
    TextView tvName, tvDescription,Pro,Products1,Ad,Advantage1,Rec,Recomm,War,Warning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_page);
        tvName = (TextView) findViewById(R.id.Name);
        tvDescription = (TextView) findViewById(R.id.work);
        String Name = getIntent().getStringExtra("Step");
        String Description = getIntent().getStringExtra("Dtail");
        tvName.setText(Name);
        tvDescription.setText(Description);

        Rec = (TextView) findViewById(R.id.rec);
        Recomm = (TextView) findViewById(R.id.recomm);
        String Recc = getIntent().getStringExtra("Rec");
        Rec.setText("ข้อแนะนำ");
        Recomm.setText(Recc);

        War = (TextView) findViewById(R.id.warn);
        Warning = (TextView) findViewById(R.id.warning);
        String Warr = getIntent().getStringExtra("Warn");
        War.setText("ข้อควรระวัง");
        Warning.setText(Warr);
    }
}