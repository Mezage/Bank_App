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
        EditText targetuser = findViewById(R.id.editTextTextTargetUser);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("username");

        Button btn_withdraw = findViewById(R.id.Withdraw);
        Button btn_deposit = findViewById(R.id.Deposit_de);
        Button btn_transfer = findViewById(R.id.btntransfer);


        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getDataBytUsernameAndPassword(user,password.getText().toString());

                if(res.getCount() != 0){
                    res.moveToNext();
                    int currentBalance = Integer.parseInt(res.getString(3));
                    mydb.updateBalance(user,-Double.parseDouble(amount.getText().toString()));

                }

                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                intent.putExtra("username",user);
                startActivity(intent);

            }
        });

        btn_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getDataBytUsernameAndPassword(user,password.getText().toString());

                if(res.getCount() != 0){

                    res.moveToNext();
                    int currentBalance = Integer.parseInt(res.getString(3));
                    mydb.updateBalance(user,Double.parseDouble(amount.getText().toString()));

                }
                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                intent.putExtra("username",user);
                startActivity(intent);

            }
        });

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getDataBytUsernameAndPassword(user,password.getText().toString());

                if(res.getCount() != 0){
                    res.moveToNext();
                    int currentBalance = Integer.parseInt(res.getString(3));
                    mydb.updateBalance(user,-Double.parseDouble(amount.getText().toString()));
                    mydb.updateBalance(targetuser.getText().toString(), Double.parseDouble(amount.getText().toString()));

                }

                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                intent.putExtra("username",user);
                startActivity(intent);


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