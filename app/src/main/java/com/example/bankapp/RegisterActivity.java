package com.example.bankapp;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
        EditText balance = findViewById(R.id.editTextTextBalance);

        Button reg_btn = findViewById(R.id.button_reg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning = (TextView) findViewById(R.id.hint);
                Cursor res = mydb.getDataByUsername(username.getText().toString());

                Log.d("table!",Integer.toString(res.getCount()) );
                if(username.getText().toString().equals("") || password.getText().toString().equals("") || balance.getText().toString().equals("")){
                    warning.setText("Please fill in all the information");
                }
                else if(res.getCount() == 0) {
                    warning.setText("Success!");
                    boolean ins = mydb.insertUser(username.getText().toString(), password.getText().toString(), balance.getText().toString());

                    /************************
                     * Go to USER PAGE HERE *
                     ************************/
                }else{

                    warning.setText("Username already in use!");
                }



            }
        });


        /*********** temporary    just for testing database */
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();
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