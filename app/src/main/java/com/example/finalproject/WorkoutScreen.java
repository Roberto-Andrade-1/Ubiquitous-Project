package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WorkoutScreen extends AppCompatActivity {

    Button backButton;
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

            }
        });

    }
}