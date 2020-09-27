package com.example.farmermate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "RiceInfo1.sqlite";
    public static final String TABLE_NAME = "RiceRec";
    public static final String DBLOCATION = "/data/data/com.example.farmermate/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    RecomPage recomPage;

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        //onCreate(sqLiteDatabase);
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDatabase() throws IOException {
        boolean mDatabaseExist = checkDataBase();
        if(!mDatabaseExist)
        {
            this.getReadableDatabase();
            this.close();
            //Copy the database from assests
            copyDatabase();
            //Log.e(TAG, "createDatabase database created");
        }

    }

    private boolean checkDataBase()
    {
        File databasePath = new File(DBLOCATION + DBNAME);
        //File databasePath = mContext.getDatabasePath(DB_NAME);

        return databasePath.exists();
    }

    private boolean copyDatabase() {
        try {
            InputStream inputStream = mContext.getAssets().open(DBHelper.DBNAME);
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

    public boolean openDatabase() {
        String mPath = DBLOCATION + DBNAME;
        //Log.v("mPath", mPath);
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDatabase != null;
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }
    //ดินเหนียว
    public List<Rice> getListRice0() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice2(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Cray = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
//------------------------------------------------------------------------------------------------------------------------------------
//ดินร่วน
    public List<Rice> getListRice3() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice4(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice5() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Mold = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
//------------------------------------------------------------------------------------------------------------------------------------
    //ดินทราย
    public List<Rice> getListRice6(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    public List<Rice> getListRice7(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    public List<Rice> getListRice8(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE N = 1 AND Solid_Sandy = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }


    //---------------------------------------------------------------ภาคกลาง---------------------------------------------------------------------
    public List<Rice> getListRice9() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Cray = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice10() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Cray = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice11(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Cray = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
//ดินร่วน
    public List<Rice> getListRice12() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Mold = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice13(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Mold = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice14() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Mold = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
    //ดินทราย
    public List<Rice> getListRice15(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Sandy = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    public List<Rice> getListRice16(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Sandy = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    public List<Rice> getListRice17(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE C = 1 AND Solid_Sandy = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    //
    //---------------------------------------------------------------ภาคอีสาน---------------------------------------------------------------------
    public List<Rice> getListRice18() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Cray = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice19() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Cray = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice20(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Cray = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
//ดินร่วน
    public List<Rice> getListRice21() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Mold = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice22(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Mold = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice23() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Mold = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
    //ดินทราย
    public List<Rice> getListRice24(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Sandy = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice25(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Sandy = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice26(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE NE = 1 AND Solid_Sandy = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    //---------------------------------------------------------------ภาคใต้---------------------------------------------------------------------
    public List<Rice> getListRice27() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Cray = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice28() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Cray = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice29(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Cray = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
//ดินร่วน
    public List<Rice> getListRice30() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Mold = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice31(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Mold = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice32() {
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Mold = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
    //ดินทราย
    public List<Rice> getListRice33(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Sandy = 1 AND Reservoir = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice34(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Sandy = 1 AND Irrigation = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }

    public List<Rice> getListRice35(){
        Rice rice = null;
        List<Rice> RiceList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM RiceRec WHERE S = 1 AND Solid_Sandy = 1 AND Nope = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rice = new Rice(cursor.getInt(0), cursor.getString(1), cursor.getString(9), cursor.getString(16), cursor.getString(17));
            RiceList.add(rice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return RiceList;
    }




}


