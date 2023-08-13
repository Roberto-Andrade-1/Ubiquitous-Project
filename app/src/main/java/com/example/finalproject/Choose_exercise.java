package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Choose_exercise extends AppCompatActivity {

    private Button confirm;
    private RecyclerView selectExercise;

    private List<ExerciseModel> selectedExercise;

    private ChooseExerciseAdapter adapter;

    private final int SETS = 3;

    private String repetitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        DataBaseHelper dataBaseHelper= new DataBaseHelper(this);

        Intent intent = getIntent();
        List<String> receivedExercises = intent.getStringArrayListExtra("selection");

        confirm = findViewById(R.id.confirmSelection);
        selectExercise = findViewById(R.id.recyclerSelection);

        selectExercise.setLayoutManager(new LinearLayoutManager(this));


        List<ExerciseModel> exercises = dataBaseHelper.showSelectedExercises(receivedExercises);
        adapter = new ChooseExerciseAdapter(this, exercises);
        selectExercise.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedExercise = adapter.getSelectedExercises();

                for (ExerciseModel i : selectedExercise){
                    WorkoutModel workoutModel;
                    Log.d("TESTE", "plan id: " + dataBaseHelper.getLastPlanID());
                    if(i.getWorkedMuscles().equals("Core")){
                        workoutModel = new WorkoutModel(-1, SETS, "1 minute", dataBaseHelper.getLastPlanID(), i.getId());

                    }
                    else{
                        workoutModel = new WorkoutModel(-1, SETS, "10-15 reps", dataBaseHelper.getLastPlanID(), i.getId());
                    }
                    dataBaseHelper.addWorkout(workoutModel);
                }
                dataBaseHelper.close();
            }
        });

    }
}