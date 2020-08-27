package com.example.farmermate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RecomPage extends Activity {
    Spinner sp1, sp2;
    TextView show;
    CheckBox cbres, cb2, cb3;
    String names[] = {"Red","Blue","Green"};
    //ArrayAdapter <String> adapter;
    String record= "";
    Button dis;
    private Context context;
    public static String Name;
    public static String Description;
    //SQLiteDatabase DB;

    private ListView lvProduct;
    private RiceAdapter adapter;
    private List<Product> mProductList;
    private DBHelper mDBHelper;
    private List<Rice> mRiceList;

    private SQLiteDatabase mDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_page);
        sp1 = (Spinner) findViewById(R.id.province);
        sp2 = (Spinner) findViewById(R.id.solid);

        lvProduct = (ListView)findViewById(R.id.listview_product);




        final CheckBox cbres = (CheckBox) findViewById(R.id.reservoir); //อ่าง
        final CheckBox cb2 = (CheckBox) findViewById(R.id.irrigation) ; //ชล
        final CheckBox cb3 = (CheckBox) findViewById(R.id.nope) ; //ไม่มี
        Button dis = (Button) findViewById(R.id.display);



        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp1.getSelectedItem().equals("ภาคเหนือ")) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                } else if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                }
                //--------------------------------------------ภาคเหนือ-----------------------------------------------------------------------
                else if (sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                        || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                        || sp1.getSelectedItem().equals("ลำพูน") || sp1.getSelectedItem().equals("น่าน")
                        || sp1.getSelectedItem().equals("แพร่") || sp1.getSelectedItem().equals("อุตรดิตถ์")
                        || sp1.getSelectedItem().equals("สุโขทัย") || sp1.getSelectedItem().equals("ตาก")
                        || sp1.getSelectedItem().equals("กำแพงเพชร") || sp1.getSelectedItem().equals("พิษณุโลก")
                        || sp1.getSelectedItem().equals("พิจิตร") || sp1.getSelectedItem().equals("เพชรบูรณ์")) {
                    if (sp2.getSelectedItem().equals("ดินเหนียว")) {
                        //-------------------------------------------------------------------
                        if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                            //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Reservoir = 1 AND Zone = N
                            Intent intent = new Intent(RecomPage.this, RiceDescriptionPage.class);
                            startActivity(intent);
                        }
                        //-------------------------------------------------------------------
                        else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                            //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Irrigation = 1 AND Zone = N

                            setContentView(R.layout.listview_product);
                            lvProduct = (ListView) findViewById(R.id.listview_product);
                            mDBHelper = new DBHelper(RecomPage.this);

                            //Check exists database
                            File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                            if (false == database.exists()) {
                                mDBHelper.getReadableDatabase();
                                //Copy db
                                if (copyDatabase(RecomPage.this)) {
                                    Toast.makeText(RecomPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RecomPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            //Get product list in db when db exists
                            mRiceList = mDBHelper.getListRice();
                            //Init adapter
                            adapter = new RiceAdapter(RecomPage.this, mRiceList);
                            //Set adapter for listview
                            lvProduct.setAdapter(adapter);
                            lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {

                                    // ListView Clicked item index
                                    int itemPosition     = position;
                                    View v = View.inflate(RecomPage.this, R.layout.rice_detail, null);
                                    ImageView imgV =(ImageView)v.findViewById(R.id.mainimg);
                                    TextView tvName = (TextView)v.findViewById(R.id.title);
                                    TextView tvDescription = (TextView)v.findViewById(R.id.detail);
                                }
                            });
                        }
                        //-------------------------------------------------------------------
                        else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                            //Select * FROM RiceRec WHERE Solid_Cray = 1 AND Nope = 1 AND Zone = N
                            setContentView(R.layout.listview_product);
                            lvProduct = (ListView) findViewById(R.id.listview_product);
                            mDBHelper = new DBHelper(RecomPage.this);
                            //return productList;
                            File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                            if (false == database.exists()) {
                                mDBHelper.getReadableDatabase();
                                //Copy db
                                if (copyDatabase(RecomPage.this)) {
                                    Toast.makeText(RecomPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RecomPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            //Get product list in db when db exists
                            mRiceList = mDBHelper.getListRice2();
                            //Init adapter
                            adapter = new RiceAdapter(RecomPage.this, mRiceList);
                            //Set adapter for listview
                            lvProduct.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }

                    //-------------------------------------------------------------------
                } else if (sp2.getSelectedItem().equals("ดินทราย")) {
                    //-------------------------------------------------------------------
                    if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                        //Select * FROM RiceRec WHERE Solid_Mold = 1 AND Reservoir = 1 AND Zone = N
                    }
                    //-------------------------------------------------------------------
                    else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                        //Select * FROM RiceRec WHERE Solid_Mold = 1 AND Irrigation = 1 AND Zone = N
                    }
                    //-------------------------------------------------------------------
                    else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                        //Select * FROM RiceRec WHERE Solid_Mold = 1 AND Nope = 1 AND Zone = N
                    }
                    //-------------------------------------------------------------------
                    else {
                        Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                }
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


        }
      );



        }
}







