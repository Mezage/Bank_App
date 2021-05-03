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
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE test (ID int PRIMARY KEY AUTOINCREMENT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", 30);
        db.insert("test",null, contentValues);
        Cursor res = db.rawQuery("SELECT * FROM test", null);
        int i = 0;
        while(res.moveToNext()){
            Log.d("asd",res.getString(i++));
        }
    }
}
