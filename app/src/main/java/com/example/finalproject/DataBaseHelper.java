package com.example.finalproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    //PERSON TABLE
    public static final String PERSON_TABLE = "PERSON_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PERSON_NAME = "PERSON_NAME";
    public static final String COLUMN_PERSON_SURNAME = "PERSON_SURNAME";
    public static final String COLUMN_PERSON_PHONE = "PERSON_PHONE";
    public static final String COLUMN_PERSON_AGE = "PERSON_AGE";
    public static final String COLUMN_PERSON_WEIGHT = "PERSON_WEIGHT";
    public static final String COLUMN_PERSON_HEIGHT = "PERSON_HEIGHT";
    public static final String COLUMN_PERSON_PASSWORD = "PERSON_PASSWORD";

    //FEEDBACK TABLE
    public static final String FEEDBACK_TABLE = "FEEDBACK_TABLE";
    public static final String COLUMN_FEEDBACK_ID = "FEEDBACK_ID";
    public static final String COLUMN_SUBJECT = "SUBJECT";
    public static final String COLUMN_CONTENT = "CONTENT";
    public static final String COLUMN_PERSON_ID = "PERSON_ID"; // Foreign key for the Person ID

    //WORKOUT PLAN
    public static final String WORKOUT_PLAN_TABLE = "WORKOUT_PLAN_TABLE";
    public static final String COLUMN_PLAN_ID = "PLAN_ID";
    public static final String COLUMN_PLAN_NAME = "PLAN_NAME";
    public static final String COLUMN_CREATION_DATE = "CREATION_DATE";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table for the user
        String createPerson = "CREATE TABLE " + PERSON_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PERSON_NAME + " TEXT, " + COLUMN_PERSON_SURNAME + " TEXT, " + COLUMN_PERSON_PHONE + " INT, " + COLUMN_PERSON_AGE + " INT, " + COLUMN_PERSON_WEIGHT + " INT, " + COLUMN_PERSON_HEIGHT + " INT, " + COLUMN_PERSON_PASSWORD + " TEXT)";
        //table for the feedback
        String createFeedback = "CREATE TABLE " + FEEDBACK_TABLE + " (" + COLUMN_FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBJECT + " TEXT, " + COLUMN_CONTENT + " TEXT, " + COLUMN_PERSON_ID + " INTEGER, FOREIGN KEY (" + COLUMN_PERSON_ID + ") REFERENCES " + PERSON_TABLE + " (" + COLUMN_ID + ") ON DELETE CASCADE )";
        //table for the workout plan
        String createWorkoutPlan = "CREATE TABLE " + WORKOUT_PLAN_TABLE + " (" + COLUMN_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLAN_NAME + " TEXT, " + COLUMN_CREATION_DATE + " DATE)";
        //table for the workout
        String createWorkout = "CREATE TABLE WORKOUT_TABLE (WORKOUT_ID INTEGER PRIMARY KEY AUTOINCREMENT, REPETITIONS INT, SETS INT, WORKOUT_PLAN_ID INT, FOREIGN KEY (WORKOUT_PLAN_ID) REFERENCES " + WORKOUT_PLAN_TABLE + " ( " + COLUMN_PLAN_ID + ") )"; //STILL ONE FK LEFT
        //table for the workout record
        String createWorkoutRecord = "";
        //table for the workout exercise
        String createWorkoutExercise = "";
        //table for the exercise
        String createExercise = "";

        db.execSQL(createPerson);
        db.execSQL(createFeedback);
        db.execSQL(createWorkoutPlan);
        db.execSQL(createWorkout);
        db.execSQL(createWorkoutRecord);
        db.execSQL(createWorkoutExercise);
        db.execSQL(createExercise);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(PersonModel personModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PERSON_NAME, personModel.getName());
        cv.put(COLUMN_PERSON_SURNAME, personModel.getSurname());
        cv.put(COLUMN_PERSON_PHONE, personModel.getPhone());
        cv.put(COLUMN_PERSON_AGE, personModel.getAge());
        cv.put(COLUMN_PERSON_WEIGHT, personModel.getWeight());
        cv.put(COLUMN_PERSON_HEIGHT, personModel.getHeight());
        cv.put(COLUMN_PERSON_PASSWORD, personModel.getPassword());

        long insert = db.insert(PERSON_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
}
