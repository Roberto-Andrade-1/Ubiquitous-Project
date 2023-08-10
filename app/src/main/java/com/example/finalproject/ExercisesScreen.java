package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ExercisesScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_screen);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ExerciseModel> exerciseList=fetchExerciseFromDatabase();
        exerciseAdapter= new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(exerciseAdapter);
    }

    private List<ExerciseModel> fetchExerciseFromDatabase(){
        DataBaseHelper dbHelper= new DataBaseHelper(this);
        return dbHelper.getAllExercises();
    }
}