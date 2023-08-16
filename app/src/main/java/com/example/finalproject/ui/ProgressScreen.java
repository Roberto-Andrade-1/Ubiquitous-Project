package com.example.finalproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.database.DataBaseHelper;
import com.example.finalproject.model.PersonModel;

public class ProgressScreen extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_screen);

        dataBaseHelper = new DataBaseHelper(this);

        PersonModel currentUser = DataBaseHelper.getCurrentUser();


        if(currentUser != null){
            TextView cut = findViewById(R.id.cuttingIntake);
            cut.setText("Male: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser) - 500) + " / Female: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser) - 500));

            TextView maintain = findViewById(R.id.maintenanceIntake);
            maintain.setText("Male: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser)) + " / Female: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser)));

            TextView bulk = findViewById(R.id.bulkingIntake);
            bulk.setText("Male: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser) + 500) + " / Female: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser) + 500));
        }
    }
}