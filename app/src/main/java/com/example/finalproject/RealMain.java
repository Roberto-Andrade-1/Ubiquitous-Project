package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RealMain extends AppCompatActivity {

    Button settingsButton, challengesButton, workoutButton, exercisesButton, calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        settingsButton = findViewById(R.id.settingsButton);
        challengesButton = findViewById(R.id.challengesButton);
        workoutButton = findViewById(R.id.workoutButton);
        exercisesButton = findViewById(R.id.exercisesButton);
        calories = findViewById(R.id.Calories);

        String phoneNumber = getIntent().getStringExtra("userPhone");

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealMain.this, SettingsScreen.class);
                startActivity(intent);
            }
        });

        challengesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealMain.this, ChallengesScreen.class);
                startActivity(intent);
            }
        });

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealMain.this, WorkoutPlanScreen.class);
                startActivity(intent);
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealMain.this, WorkoutScreen.class);
                startActivity(intent);
            }
        });

        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealMain.this, ProgressScreen.class);
                startActivity(intent);
            }
        });
    }
}