package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.finalproject.R;
import com.example.finalproject.model.ExerciseModel;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private Context context;
    private List<ExerciseModel> exerciseList;

    public ExerciseAdapter(Context context, List<ExerciseModel> exerciseList) {
        this.context = context;
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
        holder.exerciseMuscleTextView.setText(currentExercise.getWorkedMuscles());

        int gifResource = context.getResources().getIdentifier(
                currentExercise.getExerciseName().toLowerCase().replace("-", "_").replace(" ", "_"),
                "drawable",
                context.getPackageName()
        );

        Glide.with(context)
                .asGif()
                .load(gifResource)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.exerciseImageView);
    }


    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTextView;
        TextView exerciseMuscleTextView;
        ImageView exerciseImageView;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseName);
            exerciseMuscleTextView = itemView.findViewById(R.id.exerciseMuscles);
            exerciseImageView = itemView.findViewById(R.id.exerciseImage);
        }
    }
}

