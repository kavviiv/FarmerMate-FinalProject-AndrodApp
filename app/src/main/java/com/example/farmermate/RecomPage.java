package com.example.farmermate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecomPage extends AppCompatActivity {
    Spinner sp1, sp2;
    TextView show;
    CheckBox cbres, cb2, cb3;
    String names[] = {"Red","Blue","Green"};
    ArrayAdapter <String> adapter;
    String record= "";
    Button dis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_page);
        sp1 = (Spinner) findViewById(R.id.province);

        // Create an ArrayAdapter using the string array and a default spinner layout
      //  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       // sp1.setAdapter(adapter);
        sp2 = (Spinner) findViewById(R.id.solid);
        // Create an ArrayAdapter using the string array and a default spinner layout
       /* ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.solid_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp2.setAdapter(adapter2);*/

        //TextView show = (TextView) findViewById(R.id.show);
        final CheckBox cbres = (CheckBox) findViewById(R.id.reservoir);
        final CheckBox cb2 = (CheckBox) findViewById(R.id.irrigation) ;
        final CheckBox cb3 = (CheckBox) findViewById(R.id.nope) ;
        Button dis = (Button) findViewById(R.id.display);

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--------------------------------------------ภาคเหนือ-----------------------------------------------------------------------
//ภาคเหนือ + ดินเหนียว + มีอ่างเก็บน้ำ/บ่อน้ำ
                //----------------------------------Province Choose-----------------------------------------------------------------------
                if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();

                    }
                }
                if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินเหนียว") && cbres.isChecked() )  ){
                            Intent intent = new Intent(RecomPage.this, RiceDescriptionPage.class);
                            startActivity(intent);

                  }
                //------------------------------------------------------------------------------------------------------------------------
// ภาคเหนือ + ดินเหหนียว + มีชลประทาน
                //----------------------------------Province Choose-----------------------------------------------------------------------
               if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินเหนียว") && cb2.isChecked() )  ){
                    if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(RecomPage.this, RiceDescriptionPage.class);
                        startActivity(intent);
                    }
                }
                //-----------------------------------------------------------------------------------------------------------------------------
//ภาคเหนือ + ดินเหหนียว + ไม่มีแหล่งน้ำ
                //----------------------------------Province Choose-----------------------------------------------------------------------
       /*          if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินเหนียว") && cb3.isChecked() )  ){
                    if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(RecomPage.this, Rice1.class);
                        startActivity(intent);}
                    if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                    }
                }
                //------------------
                //
                //
                //
                //
                //--------------------------------------------ภาคเหนือ-----------------------------------------------------------------------
//ภาคเหนือ + ดินทราย + มีอ่างเก็บน้ำ/บ่อน้ำ
                //----------------------------------Province Choose-----------------------------------------------------------------------
                if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินทราย") && cbres.isChecked() )  ){
                    if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(RecomPage.this, Rice1.class);
                        startActivity(intent);}
                    if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                    }
                }
                //------------------------------------------------------------------------------------------------------------------------
// ภาคเหนือ + ดินทราย + มีชลประทาน
                //----------------------------------Province Choose-----------------------------------------------------------------------
                else if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินทราย") && cb2.isChecked() )  ){
                    if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(RecomPage.this, Rice1.class);
                        startActivity(intent);}
                    if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                    }

                }
                //-----------------------------------------------------------------------------------------------------------------------------
//ภาคเหนือ + ดินทราย + ไม่มีแหล่งน้ำ
                //----------------------------------Province Choose-----------------------------------------------------------------------
                else if ((sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")
                        //--------------------------------------------Solid & Water Choose--------------------------------------------------
                        && sp2.getSelectedItem().equals("ดินทราย") && cb3.isChecked() )  ){
                    if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(RecomPage.this, Rice1.class);
                        startActivity(intent);}
                    if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                    }
                }






                else {

                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูล", Toast.LENGTH_LONG).show();
                }*/

            }});

    }






}


