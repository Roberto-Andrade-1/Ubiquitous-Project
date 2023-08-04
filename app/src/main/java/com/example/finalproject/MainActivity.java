package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button personRegister;
    EditText personName, personSurname, personPhone, personAge, personWeight, personHeight, personPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personRegister = findViewById(R.id.personRegister);
        personName = findViewById(R.id.personName);
        personSurname = findViewById(R.id.personSurname);
        personPhone = findViewById(R.id.personPhone);
        personAge = findViewById(R.id.personAge);
        personWeight = findViewById(R.id.personWeight);
        personHeight = findViewById(R.id.personHeigth);
        personPassword = findViewById(R.id.personPassword);

        // button listener to add a new person
        personRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonModel personModel;
                try {
                    personModel = new PersonModel(-1, personName.getText().toString(), personSurname.getText().toString(), Integer.parseInt(personPhone.getText().toString()), Integer.parseInt(personAge.getText().toString()), Integer.parseInt(personWeight.getText().toString()), Integer.parseInt(personHeight.getText().toString()), personPassword.getText().toString());

                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    personModel = new PersonModel(-1, "error", "error", 0, 0, 0, 0, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success =dataBaseHelper.addOne(personModel);

                Toast.makeText(MainActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, RealMain.class);
                startActivity(intent);
            }
        });
    }
}