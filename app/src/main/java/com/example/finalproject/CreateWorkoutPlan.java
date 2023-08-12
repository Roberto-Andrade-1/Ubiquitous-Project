package com.example.finalproject;

import android.media.metrics.EditingSession;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CreateWorkoutPlan extends AppCompatActivity {

    private EditText editTextPlanName;
    private MultiAutoCompleteTextView multiAutoCompleteTextViewMuscleGroups;
    private Button buttonCreatePlan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout_plan);

        editTextPlanName=findViewById(R.id.editTextPlanName);
        multiAutoCompleteTextViewMuscleGroups=findViewById(R.id.multiAutoCompleteTextViewMuscleGroups);
        buttonCreatePlan=findViewById(R.id.buttonCreatePlan);

        List<String> allMuscleGroups;
    }

    private List<String> getAllMuscleGroupFromDatabase(){
        DataBaseHelper dataBaseHelper= new DataBaseHelper(this);
        return ;
    }
}
