package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.WorkoutPlanAdapter;
import com.example.finalproject.database.DataBaseHelper;
import com.example.finalproject.model.WorkoutPlanModel;

import java.util.List;

public class WorkoutPlanScreen extends AppCompatActivity implements WorkoutPlanAdapter.OnWorkoutPlanClickListener {
    Button newWorkoutButton, goBack;
    RecyclerView workoutPlans;
    WorkoutPlanAdapter adapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_menu);

        dataBaseHelper = new DataBaseHelper(this);

        newWorkoutButton = findViewById(R.id.newWorkoutPlanButton);
        goBack = findViewById(R.id.goBackBt);

        workoutPlans = findViewById(R.id.recyclerViewWorkoutPlan);
        workoutPlans.setLayoutManager(new LinearLayoutManager(this));

        List<WorkoutPlanModel> allWorkoutPlans = getAllWorkoutPlansFromDatabase();

        adapter = new WorkoutPlanAdapter(allWorkoutPlans, dataBaseHelper);
        adapter.setOnWorkoutPlanClickListener(this);

        workoutPlans.setAdapter(adapter);

        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPlanScreen.this, CreateWorkoutPlan.class);
                startActivity(intent);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutPlanScreen.this, RealMain.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWorkoutPlanClick(WorkoutPlanModel workoutPlan) {
        Log.d("DEBUG", "Clicked on workout plan: " + workoutPlan.getName());
        Intent intent = new Intent(WorkoutPlanScreen.this, WorkoutPlanExercises.class);
        intent.putExtra("WORKOUT_PLAN_ID", workoutPlan.getId());
        startActivity(intent);
    }


    private List<WorkoutPlanModel> getAllWorkoutPlansFromDatabase() {
        dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getAllWorkoutPlans();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBaseHelper != null) {
            dataBaseHelper.close();
        }
    }
}
