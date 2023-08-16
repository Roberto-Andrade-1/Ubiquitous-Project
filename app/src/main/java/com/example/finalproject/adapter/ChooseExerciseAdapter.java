package com.example.finalproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.finalproject.model.ExerciseModel;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseExerciseAdapter extends RecyclerView.Adapter<ChooseExerciseAdapter.ChooseViewHolder> {

    private List<ExerciseModel> exercises;
    private List<Boolean> selectedExercises;

    public ChooseExerciseAdapter(List<ExerciseModel> exercises) {
        this.exercises = exercises;
        selectedExercises = new ArrayList<>(exercises.size());
        for (int i = 0; i < exercises.size(); i++) {
            selectedExercises.add(false);
        }
    }

    @NonNull
    @Override
    public ChooseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_choose_exercise, parent, false);
        return new ChooseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ChooseViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView exerciseName;
        TextView exerciseWorkedMuscle;
        ImageView exerciseImage;

        public ChooseViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxExercise);
            exerciseName = itemView.findViewById(R.id.chooseExerciseName);
            exerciseWorkedMuscle = itemView.findViewById(R.id.chooseExerciseMuscles);
            exerciseImage = itemView.findViewById(R.id.chooseExerciseImage);
        }

        public void bind(final int position) {
            ExerciseModel currentExercise = exercises.get(position);

            exerciseName.setText(currentExercise.getExerciseName());
            exerciseWorkedMuscle.setText(currentExercise.getWorkedMuscles());

            int gifResource = exerciseImage.getContext().getResources().getIdentifier(
                    currentExercise.getExerciseName().toLowerCase().replace("-", "_").replace(" ", "_"),
                    "drawable",
                    exerciseImage.getContext().getPackageName()
            );

            Glide.with(exerciseImage.getContext())
                    .asGif()
                    .load(gifResource)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(exerciseImage);

            checkBox.setChecked(selectedExercises.get(position));

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedExercises.set(position, checkBox.isChecked());
                }
            });
        }
    }

    public List<ExerciseModel> getSelectedExercises() {
        List<ExerciseModel> selected = new ArrayList<>();
        for (int i = 0; i < exercises.size(); i++) {
            if (selectedExercises.get(i)) {
                selected.add(exercises.get(i));
            }
        }
        return selected;
    }
}
