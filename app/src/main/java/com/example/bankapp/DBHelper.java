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

    //this won't be called if not in a service
    // will have to add this to constructor or something
    //maybe restrict balance to only integers, to avoid exceptions
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME varchar(255), PASSWORD varchar(255), BALANCE INTEGER )");

//        db.execSQL("INSERT INTO bank.user (ID, username, password, balance) VALUES ('Carl#1', '123456', '1000'),('Wendy_2','22222','500') , ('Joseph3!', '010101', '26000'), ('Alba4', '987654', '15')");

        ContentValues contentValues = new ContentValues();
        contentValues.put("username","Carl#1");
        contentValues.put("password","123456");
        contentValues.put("BALANCE",1000);
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Wendy_2");
        contentValues.put("password","33333");
        contentValues.put("BALANCE",150);
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Joseph!!!3");
        contentValues.put("password","233322");
        contentValues.put("BALANCE",555000);
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Alba4,,,");
        contentValues.put("password","948794djl6");
        contentValues.put("BALANCE",777);
        db.insert("user",null ,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String username, String password, int balance){
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

    public Cursor getDataBytUsernameAndPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user WHERE username = '"+username+"' AND  password = '"+ password +"'",null);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user",null);
    }

    public boolean updateBalance(String username,int balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = getDataByUsername(username);
        while(res.moveToNext()){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",Integer.parseInt( res.getString(0) ));
            contentValues.put("username",res.getString(1));
            contentValues.put("password",res.getString(2));
            contentValues.put("balance",balance);
            db.update("user", contentValues,"USERNAME = ?", new String[]{username});

            Log.d("balance_update: ",res.getString(1)+"has changed the balance to be "+res.getString(3));
        }
        return true;
    }

}
