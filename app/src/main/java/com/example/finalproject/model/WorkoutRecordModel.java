package com.example.finalproject.model;

import java.sql.Time;
import java.util.Date;

public class WorkoutRecordModel {

    private int id;

    private Date date;

    private Time time;

    private int duration;

    private float weightUsed;

    private int personID;

    public WorkoutRecordModel(int id, Date date, Time time, int duration, float weightUsed, int personID) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.weightUsed = weightUsed;
        this.personID = personID;
    }

    public WorkoutRecordModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(float weightUsed) {
        this.weightUsed = weightUsed;
    }

    public int getPersonID() {return personID;}

    public void setPersonID(int personID) {this.personID = personID;}

    @Override
    public String toString() {
        return "WorkoutRecordModel{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", weightUsed=" + weightUsed +
                ", personID=" + personID +
                '}';
    }
}
