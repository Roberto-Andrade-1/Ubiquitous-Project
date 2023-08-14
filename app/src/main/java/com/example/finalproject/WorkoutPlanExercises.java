package com.example.finalproject;

import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_exercises);

        planID = getIntent().getIntExtra("WORKOUT_PLAN_ID",-1);

        recyclerViewWorkoutPlanExercise=findViewById(R.id.recyclerViewExercisesWorkoutPlan);
        recyclerViewWorkoutPlanExercise.setLayoutManager(new LinearLayoutManager(this));

        adapter=new WorkoutPlanExerciseAdapter(getExercisesFromWorkoutPlan(planID),this);
        recyclerViewWorkoutPlanExercise.setAdapter(adapter);
    }

    private List<ExerciseModel> getExercisesFromWorkoutPlan(int planID){
        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        return dataBaseHelper.getAllExercisesFromWorkout(planID);
    }
}
