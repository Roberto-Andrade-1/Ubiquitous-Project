package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ChooseExerciseAdapter;
import com.example.finalproject.database.DataBaseHelper;
import com.example.finalproject.model.ExerciseModel;
import com.example.finalproject.model.WorkoutModel;

import java.util.List;

public class ChooseExercise extends AppCompatActivity {

    private Button confirm;
    private RecyclerView selectExercise;
    private ChooseExerciseAdapter adapter;
    private final int SETS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        List<String> receivedExercises = intent.getStringArrayListExtra("selection");

        confirm = findViewById(R.id.confirmSelection);
        selectExercise = findViewById(R.id.recyclerSelection);

        selectExercise.setLayoutManager(new LinearLayoutManager(this));

        List<ExerciseModel> exercises = dataBaseHelper.showSelectedExercises(receivedExercises);

        adapter = new ChooseExerciseAdapter(exercises);
        selectExercise.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ExerciseModel> selectedExercises = adapter.getSelectedExercises();
                if (selectedExercises.isEmpty()) {
                    Toast.makeText(ChooseExercise.this, "Need to select at least one exercise", Toast.LENGTH_SHORT).show();
                } else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(ChooseExercise.this);
                    int lastPlanId = dataBaseHelper.getLastPlanID();

                    for (ExerciseModel selectedExercise : selectedExercises) {
                        String repetitions;
                        if (selectedExercise.getWorkedMuscles().equals("Core")) {
                            repetitions = "1 minute";
                        } else {
                            repetitions = "10-15 reps";
                        }

                        WorkoutModel workoutModel= new WorkoutModel(-1,SETS,repetitions,lastPlanId,selectedExercise.getId());
                        dataBaseHelper.addWorkout(workoutModel);
                    }

                    dataBaseHelper.close();
                    Intent intentReturn = new Intent(ChooseExercise.this, WorkoutPlanScreen.class);
                    startActivity(intentReturn);
                }
            }
        });
    }
}
