package com.example.finalproject;

public class WorkoutExerciseModel {

    private int exerciseID;

    private int workoutID;

    public WorkoutExerciseModel(int exerciseID, int workoutID) {
        this.exerciseID = exerciseID;
        this.workoutID = workoutID;
    }

    public WorkoutExerciseModel() {
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    @Override
    public String toString() {
        return "WorkoutExerciseModel{" +
                "exerciseID=" + exerciseID +
                ", workoutID=" + workoutID +
                '}';
    }
}
