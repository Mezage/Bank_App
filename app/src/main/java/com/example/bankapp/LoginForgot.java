package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forgot);

        Button back = findViewById(R.id.back);
        Button email = findViewById(R.id.verify);

        EditText username = findViewById(R.id.editTextTextEmailAddress);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Send email", "");

                DBHelper dbh = new DBHelper(getApplicationContext());
                Cursor cursor = dbh.getDataByUsername(username.getText().toString());

                //if user exists
                //TODO: somehow return entire cursor
                if (cursor != null && cursor.moveToFirst()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    //emailIntent.putExtra(Intent.EXTRA_EMAIL, cursor.getString(#));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password Reminder");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is: \n" + cursor.getString(2));

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                        Log.i("Finished sending email...", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(LoginForgot.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }//else no user found
                else
                    Toast.makeText(LoginForgot.this, "Sorry, you are not a registered user", Toast.LENGTH_SHORT).show();
            }
        });


    }
}