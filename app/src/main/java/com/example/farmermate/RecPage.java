package com.example.farmermate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RecPage extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_page);

        final RadioButton have = (RadioButton) findViewById(R.id.have); //อ่าง
        final RadioButton no = (RadioButton) findViewById(R.id.no) ;

        final CheckBox irr = (CheckBox) findViewById(R.id.irr); //อ่าง
        final CheckBox res = (CheckBox) findViewById(R.id.res) ;

        final Spinner sp1 = (Spinner) findViewById(R.id.pro);
        final Spinner sp2= (Spinner) findViewById(R.id.solid);
        final Spinner sp3 = (Spinner) findViewById(R.id.Norths);
        final Spinner sp4= (Spinner) findViewById(R.id.Cens);
        final Spinner sp5 = (Spinner) findViewById(R.id.Es);
        final Spinner sp6= (Spinner) findViewById(R.id.Nes);
        final Spinner sp7= (Spinner) findViewById(R.id.Souths);


        no.setChecked(true);
        irr.setVisibility(View.INVISIBLE);
        res.setVisibility(View.INVISIBLE);

        sp3.setVisibility(View.INVISIBLE);
        sp4.setVisibility(View.INVISIBLE);
        sp5.setVisibility(View.INVISIBLE);
        sp6.setVisibility(View.INVISIBLE);
        sp7.setVisibility(View.INVISIBLE);


//        if (sp1.getSelectedItem().equals("ภาคเหนือ")){
//            sp3.setVisibility(View.VISIBLE);
//            sp4.setVisibility(View.INVISIBLE);
//            sp5.setVisibility(View.INVISIBLE);
//            sp6.setVisibility(View.INVISIBLE);
//            sp7.setVisibility(View.INVISIBLE);
//
//        }
//        else if (sp1.getSelectedItem().equals("ภาคกลาง")){
//            sp4.setVisibility(View.VISIBLE);
//            sp3.setVisibility(View.INVISIBLE);
//            sp5.setVisibility(View.INVISIBLE);
//            sp6.setVisibility(View.INVISIBLE);
//            sp7.setVisibility(View.INVISIBLE);
//
//        }
//        else if (sp1.getSelectedItem().equals("ภาคตะวันออกเฉียงเหนือ")){
//            sp5.setVisibility(View.VISIBLE);
//            sp4.setVisibility(View.INVISIBLE);
//            sp3.setVisibility(View.INVISIBLE);
//            sp6.setVisibility(View.INVISIBLE);
//            sp7.setVisibility(View.INVISIBLE);
//
//        } else if (sp1.getSelectedItem().equals("ภาคตะวันออก")){
//            sp6.setVisibility(View.VISIBLE);
//            sp4.setVisibility(View.INVISIBLE);
//            sp5.setVisibility(View.INVISIBLE);
//            sp3.setVisibility(View.INVISIBLE);
//            sp7.setVisibility(View.INVISIBLE);
//
//        } else if (sp1.getSelectedItem().equals("ภาคใต้")){
//            sp7.setVisibility(View.VISIBLE);
//            sp4.setVisibility(View.INVISIBLE);
//            sp5.setVisibility(View.INVISIBLE);
//            sp6.setVisibility(View.INVISIBLE);
//            sp3.setVisibility(View.INVISIBLE);
//
//        }

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (sp1.getSelectedItem().equals("ภาคเหนือ")){
                    sp3.setVisibility(View.VISIBLE);
                    sp4.setVisibility(View.INVISIBLE);
                    sp5.setVisibility(View.INVISIBLE);
                    sp6.setVisibility(View.INVISIBLE);
                    sp7.setVisibility(View.INVISIBLE);

                }
                else if (sp1.getSelectedItem().equals("ภาคกลาง")){
                    sp4.setVisibility(View.VISIBLE);
                    sp3.setVisibility(View.INVISIBLE);
                    sp5.setVisibility(View.INVISIBLE);
                    sp6.setVisibility(View.INVISIBLE);
                    sp7.setVisibility(View.INVISIBLE);

                }
                else if (sp1.getSelectedItem().equals("ภาคตะวันออกเฉียงเหนือ")){
                    sp5.setVisibility(View.VISIBLE);
                    sp4.setVisibility(View.INVISIBLE);
                    sp3.setVisibility(View.INVISIBLE);
                    sp6.setVisibility(View.INVISIBLE);
                    sp7.setVisibility(View.INVISIBLE);

                } else if (sp1.getSelectedItem().equals("ภาคตะวันออก")){
                    sp6.setVisibility(View.VISIBLE);
                    sp4.setVisibility(View.INVISIBLE);
                    sp5.setVisibility(View.INVISIBLE);
                    sp3.setVisibility(View.INVISIBLE);
                    sp7.setVisibility(View.INVISIBLE);

                } else if (sp1.getSelectedItem().equals("ภาคใต้")){
                    sp7.setVisibility(View.VISIBLE);
                    sp4.setVisibility(View.INVISIBLE);
                    sp5.setVisibility(View.INVISIBLE);
                    sp6.setVisibility(View.INVISIBLE);
                    sp3.setVisibility(View.INVISIBLE);

                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

         have.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 irr.setVisibility(View.VISIBLE);
                 res.setVisibility(View.VISIBLE);

             }
         });

         no.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 irr.setVisibility(View.INVISIBLE);
                 res.setVisibility(View.INVISIBLE);
             }
         });




        Button dis = (Button) findViewById(R.id.display);
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินเหนียว") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNC();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }


                else if (sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินร่วน") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNM();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                else if (sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินทราย") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNS();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                //-----------------------------------------------------------------------------------------
               else if (sp1.getSelectedItem().equals("ภาคกลาง") && sp2.getSelectedItem().equals("ดินเหนียว") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllCC();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }


                else if (sp1.getSelectedItem().equals("ภาคกลาง") && sp2.getSelectedItem().equals("ดินร่วน") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllCM();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                else if (sp1.getSelectedItem().equals("ภาคกลาง") && sp2.getSelectedItem().equals("ดินทราย") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllCS();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                //-----------------------------------------------------------------------------------------
                else if (sp1.getSelectedItem().equals("ภาคตะวันออกเฉียงเหนือ") && sp2.getSelectedItem().equals("ดินเหนียว") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNEC();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }


                else if (sp1.getSelectedItem().equals("ภาคตะวันออกเฉียงเหนือ") && sp2.getSelectedItem().equals("ดินร่วน") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNEM();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                else if (sp1.getSelectedItem().equals("ภาคตะวันออกเฉียงเหนือ") && sp2.getSelectedItem().equals("ดินทราย") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllNES();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }
                //-----------------------------------------------------------------------------------------

                else if (sp1.getSelectedItem().equals("ภาคตะวันออก") && sp2.getSelectedItem().equals("ดินเหนียว") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllEC();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }


                else if (sp1.getSelectedItem().equals("ภาคตะวันออก") && sp2.getSelectedItem().equals("ดินร่วน") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllEM();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                else if (sp1.getSelectedItem().equals("ภาคตะวันออก") && sp2.getSelectedItem().equals("ดินทราย") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllES();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                //-----------------------------------------------------------------------------------------
                else if (sp1.getSelectedItem().equals("ภาคใต้") && sp2.getSelectedItem().equals("ดินเหนียว") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllSC();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }


                else if (sp1.getSelectedItem().equals("ภาคใต้") && sp2.getSelectedItem().equals("ดินร่วน") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllSM();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                else if (sp1.getSelectedItem().equals("ภาคใต้") && sp2.getSelectedItem().equals("ดินทราย") && (res.isChecked() && irr.isChecked())){
                    {
                        setContentView(R.layout.listview_product);
                        lvProduct = (ListView) findViewById(R.id.listview_product);
                        mDBHelper = new DBHelper(RecPage.this);
                        //return productList;
                        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                        if (false == database.exists()) {
                            mDBHelper.getReadableDatabase();
                            //Copy db
                            if (copyDatabase(RecPage.this)) {
                                Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        //Get product list in db when db exists
                        mRiceList = mDBHelper.getAllSS();
                        //Init adapter
                        adapter = new RiceAdapter(RecPage.this, mRiceList);
                        //Set adapter for listview
                        lvProduct.setAdapter(adapter);

                    }
                }

                //-----------------------------------------------------------------------------------------


                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินเหนียว") && res.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice0();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}

                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินเหนียว") && irr.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}

                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินเหนียว") && no.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice2();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}

//-----------------------------------------------------------------------------------------------------------------------------
                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินร่วน") && res.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice3();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}

                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินร่วน") && irr.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice4();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}

                else if ( sp1.getSelectedItem().equals("ภาคเหนือ") && sp2.getSelectedItem().equals("ดินร่วน") && no.isChecked()){{
                    setContentView(R.layout.listview_product);
                    lvProduct = (ListView) findViewById(R.id.listview_product);
                    mDBHelper = new DBHelper(RecPage.this);
                    //return productList;
                    File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
                    if (false == database.exists()) {
                        mDBHelper.getReadableDatabase();
                        //Copy db
                        if (copyDatabase(RecPage.this)) {
                            Toast.makeText(RecPage.this, "Copy database succes", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecPage.this, "Copy data error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //Get product list in db when db exists
                    mRiceList = mDBHelper.getListRice5();
                    //Init adapter
                    adapter = new RiceAdapter(RecPage.this, mRiceList);
                    //Set adapter for listview
                    lvProduct.setAdapter(adapter);

                }}






























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






    }
}