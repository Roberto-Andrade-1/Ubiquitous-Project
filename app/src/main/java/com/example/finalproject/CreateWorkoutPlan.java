package com.example.finalproject;

import android.media.metrics.EditingSession;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CreateWorkoutPlan extends AppCompatActivity {

    private EditText editTextPlanName;
    private RecyclerView recyclerViewMuscleGroup;
    private Button buttonCreatePlan;
    private MuscleGroupAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout_plan);

        editTextPlanName = findViewById(R.id.editTextPlanName);
        recyclerViewMuscleGroup=findViewById(R.id.recyclerViewMuscleGroup);
        buttonCreatePlan = findViewById(R.id.buttonCreatePlan);

        recyclerViewMuscleGroup.setLayoutManager(new LinearLayoutManager(this));

        List<String> allMuscleGroups= getAllMuscleGroupFromDatabase();
        adapter=new MuscleGroupAdapter(allMuscleGroups);
        recyclerViewMuscleGroup.setAdapter(adapter);

    }

    private List<String> getAllMuscleGroupFromDatabase(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getAllMuscleGroups();
    }
}
