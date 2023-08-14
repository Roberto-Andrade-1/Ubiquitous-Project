package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Choose_exercise extends AppCompatActivity implements ChooseExerciseAdapter.ExerciseSelectionCallback {

    private Button confirm;
    private RecyclerView selectExercise;

    private List<ExerciseModel> selectedExercises;

    private ChooseExerciseAdapter adapter;

    private final int SETS = 3;

    private String repetitions;

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

        selectedExercises = new ArrayList<>();

        adapter = new ChooseExerciseAdapter(this, exercises, this);

        selectExercise.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TESTE", "Confirm button clicked");
                Log.d("TESTE", "Selected exercises size: " + selectedExercises.size());

                if (selectedExercises.isEmpty()) {
                    Toast.makeText(Choose_exercise.this, "Need to select an exercise", Toast.LENGTH_SHORT).show();
                } else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Choose_exercise.this);

                    for (int i = 0; i < selectedExercises.size(); i++) {
                        ExerciseModel selectedExercise = exercises.get(i);

                        WorkoutModel workoutModel;
                        Log.d("TESTE", "plan id: " + dataBaseHelper.getLastPlanID());
                        if (selectedExercise.getWorkedMuscles().equals("Core")) {
                            Log.d("TESTE", "Creating workout for Core exercise: " + selectedExercise.getExerciseName());
                            workoutModel = new WorkoutModel(-1, SETS, "1 minute", dataBaseHelper.getLastPlanID(), selectedExercise.getId());
                        } else {
                            Log.d("TESTE", "Creating workout for exercise: " + selectedExercise.getExerciseName());
                            workoutModel = new WorkoutModel(-1, SETS, "10-15 reps", dataBaseHelper.getLastPlanID(), selectedExercise.getId());
                        }
                        dataBaseHelper.addWorkout(workoutModel);
                        Log.d("TESTE", "Workout added for exercise: " + selectedExercise.getExerciseName());

                    }
                    dataBaseHelper.close();
                    Log.d("TESTE", "Database closed");

                    Intent intentReturn=new Intent(Choose_exercise.this, WorkoutPlanScreen.class);
                    startActivity(intentReturn);
                }
            }
        });
    }

    @Override
    public void onExerciseSelected(int position, boolean isSelected) {
        if (isSelected) {
            selectedExercises.add(adapter.getSelectedExercises().get(position));
        } else {
            selectedExercises.remove(adapter.getSelectedExercises().get(position));
        }
    }
}
