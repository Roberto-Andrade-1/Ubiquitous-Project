package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanScreen extends AppCompatActivity {
    Button newWorkoutButton;
    RecyclerView workoutPlans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_menu);

        newWorkoutButton=findViewById(R.id.newWorkoutPlanButton);

        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WorkoutPlanScreen.this,CreateWorkoutPlan.class);
                startActivity(intent);
            }
        });
    }
}
