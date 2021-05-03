package com.example.bankapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        Log.d("asd","constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("asd","created");
        db.execSQL("CREATE TABLE test (ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", 30);
        db.insert("test",null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}


    public Cursor getAllData() {
        Log.d("asd","getdata");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from test",null);
        return res;
    }

}
