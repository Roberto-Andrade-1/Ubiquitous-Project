package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutPlanScreen extends AppCompatActivity {
    Button newWorkoutButton;
    RecyclerView workoutPlans;
    WorkoutPlanAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_menu);

        newWorkoutButton=findViewById(R.id.newWorkoutPlanButton);

        workoutPlans=findViewById(R.id.recyclerViewWorkoutPlan);
        workoutPlans.setLayoutManager(new LinearLayoutManager(this));

        List<WorkoutPlanModel> allWorkoutPlans = getAllWorkoutPlansFromDatabse();
        adapter=new WorkoutPlanAdapter(allWorkoutPlans);
        workoutPlans.setAdapter(adapter);


        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WorkoutPlanScreen.this,CreateWorkoutPlan.class);
                startActivity(intent);
            }
        });
    }

    private List<WorkoutPlanModel> getAllWorkoutPlansFromDatabse(){
        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        return dataBaseHelper.getAllWorkoutPlans();
    }
}
