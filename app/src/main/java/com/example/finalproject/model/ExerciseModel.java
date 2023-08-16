package com.example.finalproject.model;

public class ExerciseModel {

    private int id;

    private String exerciseName;

    private String workedMuscles;

    private String description;


    public ExerciseModel(int id, String exerciseName, String workedMuscles, String description) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.workedMuscles = workedMuscles;
        this.description = description;
    }

    public ExerciseModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getWorkedMuscles() {
        return workedMuscles;
    }

    public void setWorkedMuscles(String workedMuscles) {
        this.workedMuscles = workedMuscles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", workedMuscles='" + workedMuscles + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
