package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<ExerciseModel> exerciseList;

    public ExerciseAdapter(List<ExerciseModel> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseModel currentExercise = exerciseList.get(position);

        holder.exerciseNameTextView.setText(currentExercise.getExerciseName());
        holder.exerciseImageView.setText(currentExercise.getWorkedMuscles());


        int gifResource = context.getResources().getIdentifier(
                currentExercise.getExerciseName().toLowerCase().replace(" ", "_"),
                "drawable",
                context.getPackageName()
        );
        holder.exerciseImageView.setImageResource(gifResource);
    }


    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTextView;
        ImageView exerciseImageView;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseName);
            exerciseImageView = itemView.findViewById(R.id.exerciseMuscles);
        }
    }
}

