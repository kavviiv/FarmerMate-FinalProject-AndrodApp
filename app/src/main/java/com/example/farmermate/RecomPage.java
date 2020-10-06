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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
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
    public static String Products;
    public static String Advantage;
    //SQLiteDatabase DB;

    private ListView lvProduct;
    private RiceAdapter adapter;
    private List<Product> mProductList;
    private DBHelper mDBHelper;
    private List<Rice> mRiceList;

    private SQLiteDatabase mDatabase;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_page);


        sp1 = (Spinner) findViewById(R.id.province);
        sp2 = (Spinner) findViewById(R.id.solid);

        lvProduct = (ListView)findViewById(R.id.listview_product);

        final RadioButton cb2 = (RadioButton) findViewById(R.id.irrigation) ; //ชล
        final RadioButton cbres = (RadioButton) findViewById(R.id.reservoir); //อ่าง
        final RadioButton cb3 = (RadioButton) findViewById(R.id.nope) ; //ไม่มี
        Button dis = (Button) findViewById(R.id.display);

        dis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RecomPage.this.onPause();
                if (sp1.getSelectedItem().equals("ภาคเหนือ")) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกจังหวัดของพื้นที่นา", Toast.LENGTH_LONG).show();
                }
                else if (!cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                }
                //--------------------------------------------ภาคเหนือ-----------------------------------------------------------------------
                else if (!sp1.getSelectedItem().equals("ภาคเหนือ")) {
                    if (sp1.getSelectedItem().equals("เชียงราย") || sp1.getSelectedItem().equals("พะเยา") || sp1.getSelectedItem().equals("ลำปาง")
                            || sp1.getSelectedItem().equals("แม่ฮ่องสอน") || sp1.getSelectedItem().equals("เชียงใหม่")
                            || sp1.getSelectedItem().equals("ลำพูน") || sp1.getSelectedItem().equals("น่าน")
                            || sp1.getSelectedItem().equals("แพร่") || sp1.getSelectedItem().equals("อุตรดิตถ์")
                            || sp1.getSelectedItem().equals("สุโขทัย") || sp1.getSelectedItem().equals("ตาก")
                            || sp1.getSelectedItem().equals("กำแพงเพชร") || sp1.getSelectedItem().equals("พิษณุโลก")
                            || sp1.getSelectedItem().equals("พิจิตร") || sp1.getSelectedItem().equals("เพชรบูรณ์")) {
                        //-------------------------------------------------------------------ดินเหนียว-----------------------------------------
                        if (sp2.getSelectedItem().equals("ดินเหนียว")) {
                            //Solid_Cray
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice0();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);


                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Irrigation = 1

                                setContentView(R.layout.listview_product);
                                lvProduct = (ListView) findViewById(R.id.listview_product);
                                mDBHelper = new DBHelper(RecomPage.this);

                                //Check exists database
                                File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                                if (!database.exists()) {
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
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Nope = 1
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
                            } else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินร่วน-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินร่วน")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice3();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice4();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice5();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินหทราย-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินทราย")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice6();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);

                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice7();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice8();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                        }
                    }
                    //-----------------------------------ภาคกลาง-------------------------------------------
                    else if (sp1.getSelectedItem().equals("นครสวรรค์") || sp1.getSelectedItem().equals("อุทัยธานี") || sp1.getSelectedItem().equals("ชัยนาท")
                            || sp1.getSelectedItem().equals("สิงห์บุรี") || sp1.getSelectedItem().equals("ลพบุรี")
                            || sp1.getSelectedItem().equals("อ่างทอง") || sp1.getSelectedItem().equals("สระบุรี")
                            || sp1.getSelectedItem().equals("สุพรรณบุรี") || sp1.getSelectedItem().equals("พระนครศรีอยุธยา")
                            || sp1.getSelectedItem().equals("กาญจนบุรี") || sp1.getSelectedItem().equals("ราชบุรี")
                            || sp1.getSelectedItem().equals("นครปฐม") || sp1.getSelectedItem().equals("นนทบุรี")
                            || sp1.getSelectedItem().equals("ปทุมธานี") || sp1.getSelectedItem().equals("กรุงเทพมหานคร") || sp1.getSelectedItem().equals("สมุทรปราการ")
                            || sp1.getSelectedItem().equals("สมุทรสงคราม") || sp1.getSelectedItem().equals("สมุทรสาคร")) {
                        if (sp2.getSelectedItem().equals("ดินเหนียว")) {
                            //Solid_Cray
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice9();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Irrigation = 1

                                setContentView(R.layout.listview_product);
                                lvProduct = (ListView) findViewById(R.id.listview_product);
                                mDBHelper = new DBHelper(RecomPage.this);

                                //Check exists database
                                File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                                if (!database.exists()) {
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
                                mRiceList = mDBHelper.getListRice10();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice11();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            } else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินร่วน-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินร่วน")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice12();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice13();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice14();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินหทราย-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินทราย")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice15();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);

                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice16();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice17();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                        }
                    }
                    //---------------------------------------------------------อีสาน----------------------------
                    else if (sp1.getSelectedItem().equals("หนองคาย") || sp1.getSelectedItem().equals("เลย") || sp1.getSelectedItem().equals("หนองบัวลำภู")
                            || sp1.getSelectedItem().equals("อุดรธานี") || sp1.getSelectedItem().equals("สกลนคร")
                            || sp1.getSelectedItem().equals("นครพนม") || sp1.getSelectedItem().equals("มุกดาหาร")
                            || sp1.getSelectedItem().equals("กาฬสินธุ์") || sp1.getSelectedItem().equals("ขอนแก่น")
                            || sp1.getSelectedItem().equals("มหาสารคาม") || sp1.getSelectedItem().equals("ร้อยเอ็ด")
                            || sp1.getSelectedItem().equals("ยโสธร") || sp1.getSelectedItem().equals("อำนาจเจริญ")
                            || sp1.getSelectedItem().equals("ชัยภูมิ") || sp1.getSelectedItem().equals("บุรีรัมย์")
                            || sp1.getSelectedItem().equals("สุรินทร์") || sp1.getSelectedItem().equals("ศรีสะเกษ")
                            || sp1.getSelectedItem().equals("นครราชสีมา") || sp1.getSelectedItem().equals("อุบลราชธานี")) {
                        if (sp2.getSelectedItem().equals("ดินเหนียว")) {
                            //Solid_Cray
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice18();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Irrigation = 1

                                setContentView(R.layout.listview_product);
                                lvProduct = (ListView) findViewById(R.id.listview_product);
                                mDBHelper = new DBHelper(RecomPage.this);

                                //Check exists database
                                File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                                if (!database.exists()) {
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
                                mRiceList = mDBHelper.getListRice19();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice20();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            } else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินร่วน-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินร่วน")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice21();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice22();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice23();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินหทราย-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินทราย")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice24();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);

                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice25();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice26();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-----------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                        }
                    }

                    //---------------------------------------------------------ใต้----------------------------

                    else if (sp1.getSelectedItem().equals("เพชรบุรี") || sp1.getSelectedItem().equals("ประจวบคีรีขันธ์") || sp1.getSelectedItem().equals("ชุมพร")
                            || sp1.getSelectedItem().equals("สุราษฎร์ธานี") || sp1.getSelectedItem().equals("นครศรีธรรมราช")
                            || sp1.getSelectedItem().equals("พัทลุง") || sp1.getSelectedItem().equals("สงขลา")
                            || sp1.getSelectedItem().equals("ปัตตานี") || sp1.getSelectedItem().equals("ยะลา")
                            || sp1.getSelectedItem().equals("นราธิวาส") || sp1.getSelectedItem().equals("ระนอง")
                            || sp1.getSelectedItem().equals("พังงา") || sp1.getSelectedItem().equals("ภูเก็ต")
                            || sp1.getSelectedItem().equals("กระบี่") || sp1.getSelectedItem().equals("ตรัง")
                            || sp1.getSelectedItem().equals("สตูล")) {
                        if (sp2.getSelectedItem().equals("ดินเหนียว")) {
                            //Solid_Cray
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice27();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Irrigation = 1

                                setContentView(R.layout.listview_product);
                                lvProduct = (ListView) findViewById(R.id.listview_product);
                                mDBHelper = new DBHelper(RecomPage.this);

                                //Check exists database
                                File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                                if (!database.exists()) {
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
                                mRiceList = mDBHelper.getListRice28();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice29();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            } else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินร่วน-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินร่วน")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice30();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice31();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice32();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        }
                        //-------------------------------------------------------------------ดินหทราย-----------------------------------------
                        else if (sp2.getSelectedItem().equals("ดินทราย")) {
                            //-------------------------------------------------------------------
                            if (cbres.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Reservoir = 1
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
                                mRiceList = mDBHelper.getListRice33();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);

                            }
                            //-------------------------------------------------------------------
                            else if (cb2.isChecked() && !cb3.isChecked() && !cbres.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Irrigation = 1
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
                                mRiceList = mDBHelper.getListRice34();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);
                            }
                            //-------------------------------------------------------------------
                            else if (cb3.isChecked() && !cbres.isChecked() && !cb2.isChecked()) {
                                //Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Nope = 1
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
                                mRiceList = mDBHelper.getListRice35();
                                //Init adapter
                                adapter = new RiceAdapter(RecomPage.this, mRiceList);
                                //Set adapter for listview
                                lvProduct.setAdapter(adapter);



                            }
                            //-----------------------------------------------------------------
                            else {
                                Toast.makeText(getApplicationContext(), "กรุณาเลือกแหล่งน้ำ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "กรุณาเลือกลักษณะดิน", Toast.LENGTH_LONG).show();
                        }
                    }

                    lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String Name = mRiceList.get(position).getName();
                            String Description = mRiceList.get(position).getDescription();
                            String Products = mRiceList.get(position).getProducts();
                            String Advantage = mRiceList.get(position).getAdvantage();
                            Intent intent = new Intent(getApplicationContext() ,RecomDetail.class);
                            intent.putExtra("Position", position);
                            intent.putExtra("Name",Name);
                            intent.putExtra("Description", Description);
                            intent.putExtra("Products",Products);
                            intent.putExtra("Advantage",Advantage);
                            startActivity(intent);
                        }
                    });
                }
                RecomPage.this.onPostResume();

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







