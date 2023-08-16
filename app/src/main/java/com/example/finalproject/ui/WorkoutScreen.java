package com.example.finalproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.finalproject.R;

public class WorkoutScreen extends AppCompatActivity {

    Button backButton, allExercises;
    ImageView core, back, legs, arms, chest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);

        core = findViewById(R.id.coreImage);
        back = findViewById(R.id.backImage);
        legs = findViewById(R.id.legsImage);
        arms = findViewById(R.id.armsImage);
        chest = findViewById(R.id.chestImage);
        backButton = findViewById(R.id.backButton);
        allExercises = findViewById(R.id.allExercisesButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutScreen.this, RealMain.class);
                startActivity(intent);
            }
        });

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkoutScreen.this, ExercisesScreen.class);
                intent.putExtra("category","Chest");
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkoutScreen.this,ExercisesScreen.class);
                intent.putExtra("category","Back");
                startActivity(intent);
            }
        });

        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkoutScreen.this,ExercisesScreen.class);
                intent.putExtra("category","Core");
                startActivity(intent);
            }
        });

        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkoutScreen.this,ExercisesScreen.class);
                intent.putExtra("category","Legs");
                startActivity(intent);
            }
        });

        arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkoutScreen.this,ExercisesScreen.class);
                intent.putExtra("category","Arms");
                startActivity(intent);
            }
        });

        allExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WorkoutScreen.this,ExercisesScreen.class);
                intent.putExtra("category","all");
                startActivity(intent);
            }
        });

    }
}