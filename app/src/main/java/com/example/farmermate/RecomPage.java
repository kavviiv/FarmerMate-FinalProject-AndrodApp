package com.example.farmermate;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static com.example.farmermate.DBHelper.TABLE_NAME;

public class RecomPage extends AppCompatActivity  {
    Spinner sp1, sp2;
    TextView show;
    CheckBox cbres, cb2, cb3;
    String names[] = {"Red","Blue","Green"};
    ArrayAdapter <String> adapter;
    String record= "";
    Button dis;
    private ArrayList<Rice> RiceList;
    private Context context;
    public static String Name;
    public static String Description;
    DBHelper DBHelper;
    SQLiteDatabase DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_page);
        sp1 = (Spinner) findViewById(R.id.province);
        sp2 = (Spinner) findViewById(R.id.solid);



        final CheckBox cbres = (CheckBox) findViewById(R.id.reservoir); //อ่าง
        final CheckBox cb2 = (CheckBox) findViewById(R.id.irrigation) ; //ชล
        final CheckBox cb3 = (CheckBox) findViewById(R.id.nope) ; //ไม่มี
        Button dis = (Button) findViewById(R.id.display);



        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                }
                else if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked() ) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                }
                //--------------------------------------------ภาคเหนือ-----------------------------------------------------------------------
                else if (sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน")  || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่")  || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย")  || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร")  || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร")  || sp1.getSelectedItem().equals("เพชรบูรณ์")){
                        if ( sp2.getSelectedItem().equals("ดินเหนียว")){
                           if(cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()){
                               //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Reservoir = 1 AND Zone = N
                               Intent intent = new Intent(RecomPage.this, RiceDescriptionPage.class);
                               startActivity(intent);
                           }
                           else if(cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()){
                               //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Irrigation = 1 AND Zone = N
                           }
                           else if(cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()){
                               //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Nope = 1 AND Zone = N
                           }
                           else{
                               Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                           }
                        }
                        else if ( sp2.getSelectedItem().equals("ดินทราย")){
                            if(cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()){
                                //Select * FROM RiceRec WHERE Solid_Mold = 1 AND Reservoir = 1 AND Zone = N
                            }
                            else if(cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()){
                                //Select * FROM RiceRec WHERE Solid_Mold = 1AND Irrigation = 1 AND Zone = N
                            }
                            else if(cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()){
                                //Select * FROM RiceRec WHERE Solid_Mold = 1 AND Nope = 1 AND Zone = N
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                        }
                }
                //--------------------------------------------ภาคอีสาน-----------------------------------------------------------------------

            }
        });

    }



}



