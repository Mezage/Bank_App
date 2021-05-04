package com.example.bankapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bank.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME varchar(255), PASSWORD varchar(255), BALANCE varchar(255) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String username, String password, String balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("BALANCE",balance);
        long result = db.insert("user",null ,contentValues);
        return result != -1;
    }

    public Cursor getDataByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user WHERE username = '"+username+"'",null);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user",null);
    }

    public boolean updateBalance(String username,String balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = getDataByUsername(username);
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",Integer.parseInt( res.getString(0) ));
        contentValues.put("username",username);
        contentValues.put("password",res.getString(2));
        contentValues.put("balance",balance);
        db.update("user", contentValues,"USERNAME = ?", new String[]{username});
        return true;
    }

}
