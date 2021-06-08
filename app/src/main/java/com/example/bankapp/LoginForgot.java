package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginForgot extends AppCompatActivity {

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        Button back = findViewById(R.id.back);
        Button verify = findViewById(R.id.verify);
        Button reset = findViewById(R.id.reset);

        EditText address = findViewById(R.id.emailEditText);
        EditText oldPass = findViewById(R.id.oldPass);
        EditText newPass = findViewById(R.id.newPass);

        TextView passDescript = findViewById(R.id.resetPassText);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(getApplicationContext());
                cursor = dbh.getDataByEmail(address.getText().toString());

                //if user exists
                if (cursor != null && cursor.moveToFirst()) {
                    //reveal hidden fields if valid user and disable this button
                    reset.setVisibility(View.VISIBLE);
                    newPass.setVisibility(View.VISIBLE);
                    passDescript.setVisibility(View.VISIBLE);
                    oldPass.setVisibility(View.VISIBLE);
                    verify.setClickable(false);

                }//else no user found
                else
                    Toast.makeText(LoginForgot.this, "Sorry, you are not a registered user", Toast.LENGTH_SHORT).show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cursor.getString(2).equals(oldPass.getText().toString())){
                    String pass = newPass.getText().toString();
                    if (!pass.isEmpty() && pass.matches("[0-9a-zA-Z_]+")) {
                        DBHelper dbh = new DBHelper(getApplicationContext());
                        dbh.resetPassword(cursor.getString(4), pass);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(LoginForgot.this, "Incorrect Format for New Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginForgot.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}