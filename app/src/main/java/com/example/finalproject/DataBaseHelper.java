package com.example.finalproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String FEEDBACK_ID = "FEEDBACK_ID";
    public static final String COLUMN_SUBJECT = "SUBJECT";
    public static final String COLUMN_CONTENT = "CONTENT";
    public static final String COLUMN_PERSON_ID = "PERSON_ID"; // Foreign key for the Person ID

    //WORKOUT PLAN
    public static final String WORKOUT_PLAN_TABLE = "WORKOUT_PLAN_TABLE";
    public static final String PLAN_ID = "PLAN_ID";
    public static final String COLUMN_PLAN_NAME = "PLAN_NAME";
    public static final String COLUMN_CREATION_DATE = "CREATION_DATE";

    //WORKOUT
    public static final String WORKOUT_TABLE = "WORKOUT_TABLE";
    public static final String WORKOUT_ID = "WORKOUT_ID";
    public static final String COLUMN_REPETITIONS = "REPETITIONS";
    public static final String COLUMN_SETS = "SETS";
    public static final String COLUMN_WORKOUT_PLAN_ID = "WORKOUT_PLAN_ID"; //Foreign key for workout ID

    //WORKOUT RECORD
    public static final String WORKOUT_RECORD_TABLE = "WORKOUT_RECORD_TABLE";
    public static final String RECORD_ID = "RECORD_ID";
    public static final String COLUMN_RECORD_DATE = "RECORD_DATE";
    public static final String COLUMN_RECORD_TIME = "RECORD_TIME";
    public static final String COLUMN_RECORD_DURATION = "RECORD_DURATION";
    public static final String COLUMN_WEIGHT_USED = "WEIGHT_USED";
    public static final String COLUMN_WORKOUT_ID = "COLUMN_WORKOUT_ID";

    //EXERCISE
    public static final String EXERCISE_TABLE = "EXERCISE_TABLE";
    public static final String WORKOUT_EXERCISE_TABLE = "WORKOUT_EXERCISE_TABLE";
    public static final String EXERCISE_ID = "EXERCISE_ID";
    public static final String COLUMN_EXERCISE_ID = "COLUMN_EXERCISE_ID";
    public static final String COLUMN_EXERCISE_NAME = "EXERCISE_NAME";
    public static final String COLUMN_WORKED_MUSCLES = "WORKED_MUSCLES";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";


    //creates the database along with it's name
    public DataBaseHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    // creates all the tables needed in the database, where the information will be stored
    @Override
    public void onCreate(SQLiteDatabase db) {
        //table for the user
        String createPerson = "CREATE TABLE " + PERSON_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PERSON_NAME + " TEXT, " + COLUMN_PERSON_SURNAME + " TEXT, " + COLUMN_PERSON_PHONE + " INT, " + COLUMN_PERSON_AGE + " INT, " + COLUMN_PERSON_WEIGHT + " INT, " + COLUMN_PERSON_HEIGHT + " INT, " + COLUMN_PERSON_PASSWORD + " TEXT)";
        //table for the feedback
        String createFeedback = "CREATE TABLE " + FEEDBACK_TABLE + " (" + FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBJECT + " TEXT, " + COLUMN_CONTENT + " TEXT, " + COLUMN_PERSON_ID + " INTEGER, FOREIGN KEY (" + COLUMN_PERSON_ID + ") REFERENCES " + PERSON_TABLE + " (" + COLUMN_ID + ") ON DELETE CASCADE )";
        //table for the workout plan
        String createWorkoutPlan = "CREATE TABLE " + WORKOUT_PLAN_TABLE + " (" + PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLAN_NAME + " TEXT, " + COLUMN_CREATION_DATE + " DATE)";
        //table for the workout
        String createWorkout = "CREATE TABLE " + WORKOUT_TABLE + " (" + WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_REPETITIONS + " INT, " + COLUMN_SETS + " INT, " + COLUMN_WORKOUT_PLAN_ID + " INT, " + COLUMN_EXERCISE_ID + " INT, FOREIGN KEY (" + COLUMN_WORKOUT_PLAN_ID + ") REFERENCES " + WORKOUT_PLAN_TABLE + " ( " + PLAN_ID + "), FOREIGN KEY (" + COLUMN_EXERCISE_ID + ") REFERENCES " + EXERCISE_TABLE + " (" + EXERCISE_ID + ") )";
        //table for the workout record
        String createWorkoutRecord = "CREATE TABLE " + WORKOUT_RECORD_TABLE + " (" + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECORD_DATE + " DATE, " + COLUMN_RECORD_TIME + " TIME, " + COLUMN_RECORD_DURATION + " INT, " + COLUMN_WEIGHT_USED + " FLOAT, " + COLUMN_PERSON_ID + " INT, " + COLUMN_WORKOUT_ID + " INT, FOREIGN KEY (" + COLUMN_PERSON_ID + ") REFERENCES " + PERSON_TABLE + " ( " + COLUMN_ID + " ), FOREIGN KEY (" + COLUMN_WORKOUT_ID + ") REFERENCES " + WORKOUT_TABLE + " ( " + WORKOUT_ID + ") )";
        //table for the workout exercise
        String createWorkoutExercise = "CREATE TABLE " + WORKOUT_EXERCISE_TABLE + " (" + COLUMN_EXERCISE_ID + " INT, " + COLUMN_WORKOUT_ID + " INT, FOREIGN KEY (" + COLUMN_WORKOUT_ID + ") REFERENCES " + WORKOUT_TABLE + " (" + WORKOUT_ID + "), FOREIGN KEY (" + COLUMN_EXERCISE_ID + ") REFERENCES " + EXERCISE_TABLE + " (" + EXERCISE_ID + ") )";
        //table for the exercise
        String createExercise = "CREATE TABLE " + EXERCISE_TABLE + " (" + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EXERCISE_NAME + " TEXT, " + COLUMN_WORKED_MUSCLES + " TEXT, " + COLUMN_DESCRIPTION + " TEXT)";

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

    //method to add a new person with it's information on the database
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
        } else {
            return true;
        }
    }

    //method to add a new Feedback
    public boolean addFeedback(FeedbackModel feedbackModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, feedbackModel.getSubject());
        cv.put(COLUMN_CONTENT, feedbackModel.getContent());
        cv.put(COLUMN_PERSON_ID, feedbackModel.getUserId());

        long insert = db.insert(FEEDBACK_TABLE, null, cv);

        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    //method to add a new WorkoutPlan
    public boolean addWorkoutPlan(WorkoutPlanModel workoutPlanModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAN_NAME, workoutPlanModel.getName());
        cv.put(COLUMN_CREATION_DATE, workoutPlanModel.getDate().toString());

        long insert = db.insert(WORKOUT_PLAN_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    //method to add a new Workout
    public boolean addWorkout(WorkoutModel workoutModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REPETITIONS, workoutModel.getRepetitions());
        cv.put(COLUMN_SETS, workoutModel.getSets());
        cv.put(COLUMN_WORKOUT_PLAN_ID, workoutModel.getPlanID());
        cv.put(COLUMN_EXERCISE_ID, workoutModel.getExerciseID());

        long insert = db.insert(WORKOUT_TABLE, null, cv);

        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }
    //method to add a new Workout Record
    public boolean addWorkoutRecord(WorkoutRecordModel workoutRecordModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put();

        long insert = db.insert(, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    //method to add a new Exercise
    public boolean (){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        long insert = db.insert(, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    //method to check the login information
    public boolean checkPhonePassword (String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + PERSON_TABLE + " where " + COLUMN_PERSON_PHONE + " = ? and " + COLUMN_PERSON_PASSWORD + " = ? ", new String[]{phone, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhones (String phone){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + PERSON_TABLE + " where " + COLUMN_PERSON_PHONE + " = ? ", new String[]{phone});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
