package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper mydb = new DBHelper(this);
        Cursor res = mydb.getAllData();
        int i = 0;
        while(res.moveToNext()){
            Log.d("res",res.getString(i++));
        }
    }
}