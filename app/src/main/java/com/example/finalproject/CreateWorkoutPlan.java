package com.example.finalproject;

import android.media.metrics.EditingSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

        DataBaseHelper dataBaseHelper= new DataBaseHelper(this);

        buttonCreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkoutPlanModel workoutPlanModel;

                String name = editTextPlanName.getText().toString();
                LocalDate localDate= LocalDate.now();

                Date date=Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                if (name.equals("")){
                    Toast.makeText(CreateWorkoutPlan.this,"Need a name for workout",Toast.LENGTH_SHORT).show();
                }
                else{
                    workoutPlanModel=new WorkoutPlanModel(-1,name, date);
                    dataBaseHelper.addWorkoutPlan(workoutPlanModel);
                }
            }
        });

    }

    private List<String> getAllMuscleGroupFromDatabase(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getAllMuscleGroups();
    }
}
