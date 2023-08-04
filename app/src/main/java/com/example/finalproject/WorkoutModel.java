package com.example.finalproject;

public class WorkoutModel {

    private int id;

    private int sets;

    private int repetitions;

    private int planID;

    private int exerciseID;

    public WorkoutModel(int id, int sets, int repetitions, int planID, int exerciseID) {
        this.id = id;
        this.sets = sets;
        this.repetitions = repetitions;
        this.planID = planID;
        this.exerciseID = exerciseID;
    }

    public WorkoutModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    @Override
    public String toString() {
        return "WorkoutModel{" +
                "id=" + id +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                ", planID=" + planID +
                ", exerciseID=" + exerciseID +
                '}';
    }
}
