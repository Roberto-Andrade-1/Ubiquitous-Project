package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProgressScreen extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_screen);

        dataBaseHelper = new DataBaseHelper(this);

        goBack = findViewById(R.id.buttonBack);

        PersonModel currentUser = DataBaseHelper.getCurrentUser();


        if(currentUser != null){
            TextView cut = findViewById(R.id.cuttingIntake);
            cut.setText("M: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser) - 500) + " / F: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser) - 500));

            TextView maintain = findViewById(R.id.maintenanceIntake);
            maintain.setText("M: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser)) + " / F: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser)));

            TextView bulk = findViewById(R.id.bulkingIntake);
            bulk.setText("M: " + String.valueOf(dataBaseHelper.getCaloriesMale(currentUser) + 500) + " / F: " + String.valueOf(dataBaseHelper.getCaloriesFemale(currentUser) + 500));
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgressScreen.this, RealMain.class);
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