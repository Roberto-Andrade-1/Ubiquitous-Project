package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        dataBaseHelper = new DataBaseHelper(this);

        PersonModel currentUser = DataBaseHelper.getCurrentUser(); // Get the current user

        if (currentUser != null) {

            saveButton = findViewById(R.id.saveButton);

            EditText Name = findViewById(R.id.name);
            Name.setText(currentUser.getName());
            String newName = Name.getText().toString();

            EditText Surname = findViewById(R.id.surname);
            Surname.setText(currentUser.getSurname());
            String newSurname = Surname.getText().toString();

            EditText Phone = findViewById(R.id.phone);
            Phone.setText(String.valueOf(currentUser.getPhone()));
            String newPhone = Phone.getText().toString();

            EditText Age = findViewById(R.id.age);
            Age.setText(String.valueOf(currentUser.getAge()));
            String newAge = Age.getText().toString();

            EditText Weight = findViewById(R.id.weight);
            Weight.setText(String.valueOf(currentUser.getWeight()));
            String newWeight = Weight.getText().toString();

            EditText Height = findViewById(R.id.height);
            Height.setText(String.valueOf(currentUser.getHeight()));
            String newHeight = Height.getText().toString();

            EditText Password = findViewById(R.id.password);
            Password.setText(currentUser.getPassword());
            String newPassword = Password.getText().toString();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText Name = findViewById(R.id.name);
                    String newName = Name.getText().toString();

                    EditText Surname = findViewById(R.id.surname);
                    String newSurname = Surname.getText().toString();

                    EditText Phone = findViewById(R.id.phone);
                    String newPhone = Phone.getText().toString();

                    EditText Age = findViewById(R.id.age);
                    String newAge = Age.getText().toString();

                    EditText Weight = findViewById(R.id.weight);
                    String newWeight = Weight.getText().toString();

                    EditText Height = findViewById(R.id.height);
                    String newHeight = Height.getText().toString();

                    EditText Password = findViewById(R.id.password);
                    String newPassword = Password.getText().toString();

                    if (newName.equals("") || newSurname.equals("") || newPhone.equals("") || newAge.equals("") || newWeight.equals("") || newHeight.equals("") || newPassword.equals("")) {
                        Toast.makeText(SettingsScreen.this, "You can't leave fields empty", Toast.LENGTH_SHORT).show();
                    } else {
                        currentUser.setName(newName);
                        currentUser.setSurname(newSurname);
                        currentUser.setPhone(Integer.parseInt(newPhone));
                        currentUser.setAge(Integer.parseInt(newAge));
                        currentUser.setWeight(Integer.parseInt(newWeight));
                        currentUser.setHeight(Integer.parseInt(newHeight));
                        currentUser.setPassword(newPassword);

                        boolean isUpdated = dataBaseHelper.updateUser(currentUser);

                        if (isUpdated) {
                            Toast.makeText(SettingsScreen.this, "User updated with success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SettingsScreen.this, "Couldn't update the user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }
    }
}