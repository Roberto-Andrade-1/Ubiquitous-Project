package com.example.finalproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanAdapter.WorkoutPlanViewHolder>{

    private List<WorkoutPlanModel> workoutPlans;

    private OnWorkoutPlanClickListener listener;

    public void setOnWorkoutPlanClickListener(OnWorkoutPlanClickListener listener) {
        this.listener = listener;
    }

    public WorkoutPlanAdapter(List<WorkoutPlanModel> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    @NonNull
    @Override
    public WorkoutPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_workout_plan,parent,false);
       return new WorkoutPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanViewHolder holder, int position) {
        WorkoutPlanModel currentWorkoutPlan=workoutPlans.get(position);

        holder.textViewNamePlan.setText(currentWorkoutPlan.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "Clicked on RecyclerView item at position: " + position);
                if (listener != null) {
                    listener.onWorkoutPlanClick(currentWorkoutPlan);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return workoutPlans.size();
    }

    public class WorkoutPlanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNamePlan;

        public WorkoutPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamePlan=itemView.findViewById(R.id.planName);
        }

    }

    public interface OnWorkoutPlanClickListener {
        void onWorkoutPlanClick(WorkoutPlanModel workoutPlan);
    }


}
