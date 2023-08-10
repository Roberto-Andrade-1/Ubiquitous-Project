package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SettingsScreen extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        dataBaseHelper = new DataBaseHelper(this);

        String userPhone = getIntent().getStringExtra("userPhone");
        Log.d("SettingsScreen", "User Phone: " + userPhone);

        PersonModel user = dataBaseHelper.getUser(userPhone);
        Log.d("SettingsScreen", "User Data: " + user);

        EditText editTextName = findViewById(R.id.name);
        if (user != null) {
            editTextName.setText(user.getName());
        } else {
            Log.e("SettingsScreen", "User not found in database");
        }



    }
}