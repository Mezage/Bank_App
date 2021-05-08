package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DBHelper mydb = new DBHelper(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("username");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Button deposit_btn = findViewById(R.id.Deposit);
        TextView amount = findViewById(R.id.Amount);
        Cursor res = mydb.getDataByUsername(user);
        res.moveToNext();
//        Log.d("test",res.getString(3));
//        amount.setText("tset");
        amount.setText(res.getString(3));

        deposit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepositActivity.class);
                startActivity(intent);
            }
        });
    }
}