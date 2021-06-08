package com.example.bankapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity {

    private DBHelper mydb = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText username = findViewById(R.id.editTextTextUsername);
        EditText password = findViewById(R.id.editTextTextPassword);
        EditText balance = findViewById(R.id.editTextNumberDecimalBalance);
        EditText email = findViewById(R.id.editTextTextEmail);

        Button reg_btn = findViewById(R.id.button_reg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning = (TextView) findViewById(R.id.hint);

                if(!username.getText().toString().matches("[0-9a-zA-Z_]+") || !password.getText().toString().matches("[0-9a-zA-Z_]+")){
                    warning.setText("Invalid username or password");
                }else {
                    Cursor res = mydb.getDataByUsername(username.getText().toString());

                    if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                            balance.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                        warning.setText("Please fill in all the information");
                    } else if (res == null || res.getCount() == 0) {
                        if (Double.parseDouble(balance.getText().toString()) < 0) {
                            warning.setText("Please fill in a valid amount.");
                        }
                        if (!isValidEmail(email.getText().toString())) {    //check if provided email is valid
                            warning.setText("Invalid email");
                        } else {
                            warning.setText("Success!");
                            boolean ins = mydb.insertUser(username.getText().toString(), password.getText().toString(), Double.parseDouble(balance.getText().toString()), email.getText().toString());

                            Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                            intent.putExtra("username", username.getText().toString());
                            startActivity(intent);
                        }
                    } else {
                        warning.setText("Username already in use!");
                    }
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}