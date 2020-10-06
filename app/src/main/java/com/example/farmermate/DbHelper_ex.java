package com.example.farmermate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VINEELA on 17-04-2017.
 */


public class DbHelper_ex extends SQLiteOpenHelper {
    public static final String DB_NAME="database";
    public static final String TABLE_NAME="expensetable";
    public static final String ROW_ID="rowid";
    public static final String COL_AMOUNT="amount";
    public static final String COL_CAT="category";
    public static final String COL_NOTE="notes";
    public static final String COL_DATE="date";


    public DbHelper_ex(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME+"(rowid integer primary key autoincrement,amount text,category text,notes text,date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
