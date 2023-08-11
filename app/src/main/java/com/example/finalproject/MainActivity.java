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

    DataBaseHelper dataBaseHelper;

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

        dataBaseHelper = new DataBaseHelper(this);

        // button listener to add a new person
        personRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonModel personModel;

                String name = personName.getText().toString();
                String surname = personSurname.getText().toString();
                String phone = personPhone.getText().toString();
                String age = personAge.getText().toString();
                String weight = personWeight.getText().toString();
                String height = personHeight.getText().toString();
                String password = personPassword.getText().toString();

                if (name.equals("") || surname.equals("") || phone.equals("") || age.equals("") || weight.equals("") || height.equals("") || password.equals("")){

                    Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                } else {

                    if(dataBaseHelper.checkPhones(phone) == false){

                        personModel = new PersonModel(-1, name, surname, Integer.parseInt(phone), Integer.parseInt(age), Integer.parseInt(weight), Integer.parseInt(height), password);
                        dataBaseHelper.addOne(personModel);

                        Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        PersonModel currentUser = dataBaseHelper.getUser(phone);
                        dataBaseHelper.setCurrentUser(currentUser);

                        Intent intent1 = new Intent(MainActivity.this, RealMain.class);
                        startActivity(intent1);

                    } else {

                        Toast.makeText(MainActivity.this, "This phone number already exists, please login.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}