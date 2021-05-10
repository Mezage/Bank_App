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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        DBHelper mydb = new DBHelper(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("username");
        TextView greeting = findViewById(R.id.textView);
        greeting.setText("Hello, "+ user);



        Button deposit_btn = findViewById(R.id.Deposit);
        TextView amount = findViewById(R.id.Amount);
        Cursor res = mydb.getDataByUsername(user);
        res.moveToNext();
        amount.setText(res.getString(3));

        deposit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepositActivity.class);
                intent.putExtra("username",res.getString(1));
                startActivity(intent);
            }
        });

        Button logout_btn = findViewById(R.id.Logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}