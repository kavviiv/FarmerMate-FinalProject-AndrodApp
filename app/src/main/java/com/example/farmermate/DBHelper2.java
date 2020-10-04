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

import static android.database.sqlite.SQLiteDatabase.*;

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DBNAME = "RiceInfo1.sqlite";
    public static final String TABLE_NAME = "R1";
    public static final String DBLOCATION = "/data/data/com.example.farmermate/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    RecomPage recomPage;

    public DBHelper2(Context context) {
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
            InputStream inputStream = mContext.getAssets().open(DBHelper2.DBNAME);
            String outFileName = DBHelper2.DBLOCATION + DBHelper2.DBNAME;
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
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDatabase != null;
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }
    public static String RiceWork = "SELECT * FROM R1 " ;


    public List<Todo> getToDoList0() {
        Todo todo = null;
        List<Todo> ToDoList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM R1 ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            todo = new Todo(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            ToDoList.add(todo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return ToDoList;
    }

    public ArrayList<String> getAllWork() {
        Work work = null;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Work = new ArrayList<>();
        Cursor res = db.rawQuery( "select * FROM R1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            work = new Work (res.getInt(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));
            res.moveToNext();
        }
        return Work;
    }

}
