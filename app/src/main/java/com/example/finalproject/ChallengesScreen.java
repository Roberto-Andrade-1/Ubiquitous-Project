package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

public class ChallengesScreen extends AppCompatActivity {

    private TextView exercise, muscleGroup;
    private ImageView exerciseImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges_screen);

        exercise=findViewById(R.id.exerciseNameChallenge);
        muscleGroup=findViewById(R.id.exerciseMuscleChallenge);
        exerciseImage=findViewById(R.id.exerciseImageChallenge);

        ExerciseModel randExercise= randomExercise();

        if(randExercise!=null){
            exercise.setText(randExercise.getExerciseName());
            muscleGroup.setText(randExercise.getWorkedMuscles());

            int imageResource = getResources().getIdentifier(
                    randExercise.getExerciseName().toLowerCase().replace(" ", "_").replace("-","_"),
                    "drawable",
                    getPackageName()
            );

            Glide.with(this)
                    .asGif()
                    .load(imageResource)
                    .into(exerciseImage);
        }
    }

    private ExerciseModel randomExercise(){
        DataBaseHelper dataBaseHelper= new DataBaseHelper(this);
        List<ExerciseModel> allExercises=dataBaseHelper.getAllExercises();

        int listSize=allExercises.size();

        if (listSize>0){
            Random rand=new Random();
            int randIndex= rand.nextInt(listSize);

            ExerciseModel randExercise= allExercises.get(randIndex);
            return randExercise;
        }
        else {
            System.out.println("Exercise list is empty.");
            return null;
        }
    }
}