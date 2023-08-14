package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutPlanExercises extends AppCompatActivity {

    private RecyclerView recyclerViewWorkoutPlanExercise;
    private WorkoutPlanExerciseAdapter adapter;
    private int planID;
    private Button goBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_exercises);

        goBack = findViewById(R.id.backBUTTON);

        planID = getIntent().getIntExtra("WORKOUT_PLAN_ID",-1);

        recyclerViewWorkoutPlanExercise=findViewById(R.id.recyclerViewExercisesWorkoutPlan);
        recyclerViewWorkoutPlanExercise.setLayoutManager(new LinearLayoutManager(this));

        adapter=new WorkoutPlanExerciseAdapter(getExercisesFromWorkoutPlan(planID),this);
        recyclerViewWorkoutPlanExercise.setAdapter(adapter);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutPlanExercises.this, WorkoutPlanScreen.class);
                startActivity(intent);
            }
        });
    }

    private List<ExerciseModel> getExercisesFromWorkoutPlan(int planID){
        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        return dataBaseHelper.getAllExercisesFromWorkout(planID);
    }
}
