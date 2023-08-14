package com.example.finalproject;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseExerciseAdapter extends RecyclerView.Adapter<ChooseExerciseAdapter.ChooseViewHolder> {

    private List<ExerciseModel> exercises;
    private List<Boolean> selectedExercises;
    private Context context;

    public ChooseExerciseAdapter(Context context, List<ExerciseModel> exercises){
        this.context=context;
        this.exercises = exercises;
        selectedExercises= new ArrayList<>(Collections.nCopies(exercises.size(),false));
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
        ExerciseModel currentExercise = exercises.get(position);

        holder.exerciseName.setText(currentExercise.getExerciseName());
        holder.exerciseWorkedMuscle.setText(currentExercise.getWorkedMuscles());

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

        holder.checkBox.setChecked(selectedExercises.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedExercises.set(position, !selectedExercises.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ChooseViewHolder  extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView exerciseName;
        TextView exerciseWorkedMuscle;
        ImageView exerciseImage;

        public ChooseViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkBoxExercise);
            exerciseName=itemView.findViewById(R.id.chooseExerciseName);
            exerciseWorkedMuscle=itemView.findViewById(R.id.chooseExerciseMuscles);
            exerciseImage=itemView.findViewById(R.id.chooseExerciseImage);
        }
    }

    public List<ExerciseModel> getSelectedExercises(){
        List<ExerciseModel> selected = new ArrayList<>();
        for (int i = 0; i < exercises.size(); i++){
            if(selectedExercises.get(i)){
                selected.add(exercises.get(i));
            }
        }
        return selected;
    }
}
