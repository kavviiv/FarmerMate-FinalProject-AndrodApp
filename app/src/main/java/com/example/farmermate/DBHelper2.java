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

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DBNAME = "RiceInfo1.sqlite";
    public static final String TABLE_NAME = "sow";
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


    public List<Todo> getToDoList0() {
        Todo todo = null;
        List<Todo> ToDoList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * FROM sow WHERE Name LIKE 'ข้าวดอกมะลิ 105'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            todo = new Todo(cursor.getString(0));
            ToDoList.add(todo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return ToDoList;
    }
}
