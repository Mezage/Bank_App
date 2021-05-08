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

//        log_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
//                startActivity(intent);
//            }
//        });

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetActivity.class);
                startActivity(intent);
            }
        });


        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("signin password",password.getText().toString());
                DBHelper dbh = new DBHelper(getApplicationContext());
                Cursor cursor = dbh.getDataBytUsernameAndPassword(username.getText().toString(), password.getText().toString());
//                Log.d("test",cursor.getString(0));
                if(cursor.getCount() != 0){
                    cursor.moveToNext();

                    Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                    intent.putExtra("username",cursor.getString(1));
                    Log.d("test",cursor.getString(1));
                    startActivity(intent);
                }

//                do {  //check all users with same username
//
//                    if (cursor != null && cursor.moveToFirst()) {
//
//                        //assume that password is 2nd column <- it is 3rd :D
//                        if (cursor.getString(2).compareTo(password.getText().toString()) == 0) {
//                            //launch main and close cursor
//                            cursor.close();
//
////                            Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
////                            startActivity(intent);
//
//                        }  //move to next column, assume its password
//                    }
//
//                }while (cursor.moveToNext());

                //at this point couldn't find matching user
                cursor.close();
                Toast.makeText(getApplicationContext(), "No User found", Toast.LENGTH_SHORT).show();

            }
        });

        /*********** temporary    just for testing database */
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(getApplicationContext());
                Cursor res = dbh.getAllData();
                Log.d("table","~");
                while(res.moveToNext()){
                    Log.d("table", "ID: "+res.getString(0));
                    Log.d("table", "username: "+res.getString(1));
                    Log.d("table", "password: "+res.getString(2));
                    Log.d("table", "balance: "+res.getString(3));
                }
            }
        });
        /*********** temporary    just for testing database */


    }
}