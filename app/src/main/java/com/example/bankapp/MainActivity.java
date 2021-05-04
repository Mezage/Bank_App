package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username  = findViewById(R.id.username_input);
        EditText password  = findViewById(R.id.password_Input);

        Button reg_btn = findViewById(R.id.regButton);
        Button log_btn = findViewById(R.id.loginButton);
        Button forgot_btn = findViewById(R.id.forgotLogin);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                startActivity(intent);
            }
        });

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetActivity.class);
                startActivity(intent);
            }
        });

/*
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(getApplicationContext());
                Cursor cursor = dbh.getDataByUsername(username.getText().toString());

                do {  //check all users with same username

                    if (cursor != null && cursor.moveToFirst()) {

                        //assume that password is 2nd column
                        if (cursor.getString(1).compareTo(password.getText().toString()) == 0) {
                            //launch main and close cursor
                            cursor.close();

//                            Intent intent = new Intent(getApplicationContext(), something.class);
//                            startActivity(intent);

                        }  //move to next column, assume its password
                    }

                }while (cursor.moveToNext());

                //at this point couldn't find matching user
                cursor.close();
                Toast.makeText(getApplicationContext(), "No User found", Toast.LENGTH_SHORT).show();

            }
        });
*/

    }
}