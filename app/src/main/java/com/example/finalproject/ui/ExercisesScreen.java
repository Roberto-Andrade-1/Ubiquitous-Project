package com.example.finalproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ExerciseAdapter;
import com.example.finalproject.database.DataBaseHelper;
import com.example.finalproject.model.ExerciseModel;

import java.util.List;

public class ExercisesScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_screen);

        selectedCategory = getIntent().getStringExtra("category");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ExerciseModel> exerciseList;
        if (selectedCategory.equals("all")){
            exerciseList = fetchExerciseFromDatabase();
        }
        else{
            exerciseList = fetchExerciseFromDatabase(selectedCategory);
        }
        exerciseAdapter= new ExerciseAdapter(this,exerciseList);
        recyclerView.setAdapter(exerciseAdapter);
    }

    private List<ExerciseModel> fetchExerciseFromDatabase(String category){
        DataBaseHelper dbHelper= new DataBaseHelper(this);
        return dbHelper.getExercisesByCategory(category);
    }

    private List<ExerciseModel> fetchExerciseFromDatabase(){
        DataBaseHelper dbHelper= new DataBaseHelper(this);
        return dbHelper.getAllExercises();
    }
}