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
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginForgot.class);
                startActivity(intent);
            }
        });


        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(getApplicationContext());
                Cursor cursor = dbh.getDataBytUsernameAndPassword(username.getText().toString(), password.getText().toString());
                if(cursor != null && cursor.getCount() != 0){
                    cursor.moveToFirst();

                    Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                    intent.putExtra("username",cursor.getString(1));
                    startActivity(intent);
                }

                else{
                    Toast.makeText(getApplicationContext(), "No User found", Toast.LENGTH_SHORT).show();
                }
//                cursor.close();

            }
        });
    }
}