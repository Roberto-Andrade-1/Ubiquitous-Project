package com.example.finalproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.PersonModel;
import com.example.finalproject.R;
import com.example.finalproject.database.DataBaseHelper;

public class Login extends AppCompatActivity {

    Button logIN;
    EditText phone, password;
    TextView register;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logIN = findViewById(R.id.loginButton);
        phone = findViewById(R.id.phoneNumberLogin);
        password = findViewById(R.id.passwordLogin);
        register = findViewById(R.id.textRegister);

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

                        dataBaseHelper.close();

                        Intent intent = new Intent (Login.this, RealMain.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Need to register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseHelper.close(); // Close the database when the activity is destroyed
    }

}