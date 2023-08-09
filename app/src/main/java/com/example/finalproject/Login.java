package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button logIN;
    EditText phone, password;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logIN = findViewById(R.id.loginButton);
        phone = findViewById(R.id.phoneNumberLogin);
        password = findViewById(R.id.passwordLogin);

        dataBaseHelper = new DataBaseHelper(this);

        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phon = phone.getText().toString();
                String pass = password.getText().toString();

                if(phon.equals("") || pass.equals("")){
                    Toast.makeText(Login.this,"All fields are required", Toast.LENGTH_SHORT).show();
                } else{

                }
            }
        });

    }
}