package com.example.finalproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MuscleGroupAdapter extends RecyclerView.Adapter<MuscleGroupAdapter.ViewHolder> {

    private List<String> muscleGroups;
    private List<Boolean> selectedItems;

    public MuscleGroupAdapter(List<String> muscleGroups) {
        this.muscleGroups = muscleGroups;
        selectedItems = new ArrayList<>(Collections.nCopies(muscleGroups.size(), false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_muscle_group, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return muscleGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxMuscleGroup);
            textView = itemView.findViewById(R.id.textViewMuscleGroup);
        }

        public void bind(final int position) {
            textView.setText(muscleGroups.get(position));
            checkBox.setChecked(selectedItems.get(position));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItems.set(position, !selectedItems.get(position));
                    notifyDataSetChanged();
                }
            });
        }
    }


    public List<String> getSelectedMuscleGroups() {
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < muscleGroups.size(); i++) {
            if (selectedItems.get(i)) {
                selected.add(muscleGroups.get(i));
            }
        }
        return selected;
    }
}

