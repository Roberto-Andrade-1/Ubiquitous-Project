package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.finalproject.R;
import com.example.finalproject.model.ExerciseModel;

import java.util.List;

public class WorkoutPlanExerciseAdapter extends RecyclerView.Adapter<WorkoutPlanExerciseAdapter.WorkoutExerciseViewHolder> {

    private List<ExerciseModel> exercises;
    private Context context;

    public WorkoutPlanExerciseAdapter(List<ExerciseModel> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout_plan_exercises,parent,false);
        return new WorkoutExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutExerciseViewHolder holder, int position) {
        ExerciseModel currentExercise=exercises.get(position);

        holder.exerciseName.setText(currentExercise.getExerciseName());
        holder.exerciseMuscle.setText(currentExercise.getWorkedMuscles());
        holder.workoutSets.setText("3");
        holder.workoutRepetitions.setText(currentExercise.getDescription());

        int gifResource = context.getResources().getIdentifier(
                currentExercise.getExerciseName().toLowerCase().replace("-", "_").replace(" ", "_"),
                "drawable",
                context.getPackageName()
        );

        Glide.with(context)
                .asGif()
                .load(gifResource)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.exerciseImage);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class WorkoutExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName;
        TextView exerciseMuscle;
        TextView workoutRepetitions;
        TextView workoutSets;
        ImageView exerciseImage;

        public WorkoutExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseMuscle = itemView.findViewById(R.id.workoutPlanExerciseMuscles);
            exerciseName = itemView.findViewById(R.id.workoutPlanExerciseName);
            exerciseImage = itemView.findViewById(R.id.workoutPlanExerciseImage);
            workoutRepetitions = itemView.findViewById(R.id.workoutPlanRepetitions);
            workoutSets = itemView.findViewById(R.id.workoutPlanSets);
        }
    }
}
