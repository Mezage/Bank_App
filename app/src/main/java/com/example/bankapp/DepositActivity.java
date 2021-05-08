package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DepositActivity extends AppCompatActivity {


    DBHelper mydb = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        EditText amount = findViewById(R.id.editTextNumberDecimalAmount);
        EditText password = findViewById(R.id.editTextPassword);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("username");
//        Cursor res = mydb.getDataByUsername(user);
//        res.moveToNext();

        Button btn_withdraw = findViewById(R.id.Withdraw);
        Button btn_deposit = findViewById(R.id.Deposit_de);

        Log.d("amoutn",amount.toString());

        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getDataBytUsernameAndPassword(user,password.getText().toString());

                if(res.getCount() != 0){
                    res.moveToNext();
                    int currentBalance = Integer.parseInt(res.getString(3));
                    mydb.updateBalance(user,currentBalance-Integer.parseInt(amount.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                    intent.putExtra("username",res.getString(1));
                    startActivity(intent);

                }

            }
        });

        btn_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getDataBytUsernameAndPassword(user,password.getText().toString());

                if(res.getCount() != 0){

                    res.moveToNext();
                    int currentBalance = Integer.parseInt(res.getString(3));
                    mydb.updateBalance(user,currentBalance+Integer.parseInt(amount.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                    intent.putExtra("username",res.getString(1));
                    startActivity(intent);
                }


            }
        });

        Button logout_btn = findViewById(R.id.Logout_deposit);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}