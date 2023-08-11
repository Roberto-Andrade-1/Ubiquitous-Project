package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
                } else {
                    Boolean checkCredentials = dataBaseHelper.checkPhonePassword(phon, pass);

                    if(checkCredentials == true){
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        PersonModel currentUser = dataBaseHelper.getUser(phon);
                        dataBaseHelper.setCurrentUser(currentUser);


                        Intent intent = new Intent (Login.this, RealMain.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Need to register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}