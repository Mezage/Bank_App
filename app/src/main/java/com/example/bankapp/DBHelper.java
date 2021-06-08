package com.example.bankapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bank.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME varchar(255), PASSWORD varchar(255), BALANCE INTEGER, EMAIL varchar(255) )");

        ContentValues contentValues = new ContentValues();
        contentValues.put("username","Carl#1");
        contentValues.put("password","123456");
        contentValues.put("BALANCE",1000);
        contentValues.put("email","carl@hhh");
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Wendy_2");
        contentValues.put("password","33333");
        contentValues.put("BALANCE",150);
        contentValues.put("email","wendy@email");
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Joseph!!!3");
        contentValues.put("password","233322");
        contentValues.put("BALANCE",555000);
        contentValues.put("email","JJJ@hotmail.com");
        db.insert("user",null ,contentValues);

        contentValues = new ContentValues();
        contentValues.put("username","Alba4,,,");
        contentValues.put("password","948794djl6");
        contentValues.put("BALANCE",777);
        contentValues.put("email","AlbaAlpha@omega");
        db.insert("user",null ,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String username, String password, double balance, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("BALANCE",balance);
        contentValues.put("EMAIL",email);
        long result = db.insert("user",null ,contentValues);
        return result != -1;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public Cursor getDataByEmail(String email){
        if (isValidEmail(email)) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.rawQuery("select * from user WHERE email = '" + email + "'", null);
        }
        return null;
    }

    public Cursor getDataByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        if(username.matches("[0-9a-zA-Z_]+"))
            return db.rawQuery("select * from user WHERE username = '"+username+"'",null);
        Log.d("illegal username or password","illegal username or password");
        return null;
    }

    public Cursor getDataBytUsernameAndPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        if(username.matches("[0-9a-zA-Z_]+") && password.matches("[0-9a-zA-Z_]+"))
            return db.rawQuery("select * from user WHERE username = '"+username+"' AND  password = '"+ password +"'",null);
        Log.d("illegal username or password","illegal username or password");
        return null;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user",null);
    }

    public void resetPassword(String email, String newPass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", newPass);

        db.update("user", contentValues,"EMAIL = ?", new String[]{email});
    }

    public boolean updateBalance(String username,double balance) {
        if(!username.matches("[0-9a-zA-Z_]+"))
            return false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = getDataByUsername(username);
        while(res.moveToNext()){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",Integer.parseInt( res.getString(0) ));
            contentValues.put("username",res.getString(1));
            contentValues.put("password",res.getString(2));
            if(Double.parseDouble( res.getString(3))+balance < 0){
                Log.d("balance_update: ",res.getString(1)+" does not have enough in balance");
                return false;
            }
            contentValues.put("balance", Double.parseDouble( res.getString(3))+balance );
            db.update("user", contentValues,"USERNAME = ?", new String[]{username});
            Double finalbalance = Double.parseDouble(res.getString(3))+balance;
            Log.d("balance_update: ",res.getString(1)+" has changed the balance to be "+ finalbalance );
        }
        return true;
    }

}
