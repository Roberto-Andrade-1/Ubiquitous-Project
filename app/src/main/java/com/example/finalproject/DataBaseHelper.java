package com.example.finalproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static PersonModel currentUser;

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
        String createWorkout = "CREATE TABLE " + WORKOUT_TABLE + " (" + WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_REPETITIONS + " STRING, " + COLUMN_SETS + " INT, " + COLUMN_WORKOUT_PLAN_ID + " INT, " + COLUMN_EXERCISE_ID + " INT, FOREIGN KEY (" + COLUMN_WORKOUT_PLAN_ID + ") REFERENCES " + WORKOUT_PLAN_TABLE + " ( " + PLAN_ID + "), FOREIGN KEY (" + COLUMN_EXERCISE_ID + ") REFERENCES " + EXERCISE_TABLE + " (" + EXERCISE_ID + ") )";
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

        insertDefaultExercises(db);
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

        cv.put(COLUMN_RECORD_DATE, workoutRecordModel.getDate().toString());
        cv.put(COLUMN_RECORD_TIME, workoutRecordModel.getTime().toString());
        cv.put(COLUMN_RECORD_DURATION, workoutRecordModel.getDuration());
        cv.put(COLUMN_WEIGHT_USED, workoutRecordModel.getWeightUsed());
        cv.put(COLUMN_PERSON_ID, workoutRecordModel.getPersonID());

        long insert = db.insert(WORKOUT_RECORD_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    private void insertDefaultExercises(SQLiteDatabase db) {

        insertExercise(db, "Push-ups", "Chest", "...");
        insertExercise(db, "Bench Press", "Chest", "...");
        insertExercise(db, "Tricep Dips", "Arms", "...");
        insertExercise(db, "Diamond Push-ups", "Arms", "...");
        insertExercise(db, "Bodyweight Squats", "Legs", "...");
        insertExercise(db, "Lunges", "Legs", "...");
        insertExercise(db, "Plank", "Core", "...");
        insertExercise(db, "Russian Twists", "Core", "...");
        insertExercise(db, "Superman", "Back", "...");
        insertExercise(db, "Bent-over Rows", "Back", "...");


    }

    private long insertExercise(SQLiteDatabase db, String name, String workedMuscles, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISE_NAME, name);
        values.put(COLUMN_WORKED_MUSCLES, workedMuscles);
        values.put(COLUMN_DESCRIPTION, description);
        return db.insert(EXERCISE_TABLE, null, values);
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

    public List<ExerciseModel> getAllExercises(){

        List<ExerciseModel> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(EXERCISE_TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(EXERCISE_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_NAME));
                String workedMuscles = cursor.getString(cursor.getColumnIndex(COLUMN_WORKED_MUSCLES));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

                ExerciseModel exercise = new ExerciseModel(id, name, workedMuscles, description);
                exerciseList.add(exercise);
            }
            cursor.close();
        }
        db.close();

        return exerciseList;
    }

    public PersonModel getUser (String userPhone){

        PersonModel user = new PersonModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + PERSON_TABLE + " where " + COLUMN_PERSON_PHONE + " = '" + userPhone + "' ";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor != null) {
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SURNAME));
                int phone = cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_PHONE));
                int age = cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_AGE));
                int weight = cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_WEIGHT));
                int height = cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_HEIGHT));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_PASSWORD));

                user.setId(id); user.setName(name); user.setSurname(surname); user.setPhone(phone);
                user.setAge(age); user.setWeight(weight); user.setHeight(height); user.setPassword(password);
            }
        }
        return user;
    }

    public static void setCurrentUser(PersonModel user) {
        currentUser = user;
    }

    public static PersonModel getCurrentUser() {
        return currentUser;
    }

    public boolean updateUser(PersonModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PERSON_NAME, user.getName());
        values.put(COLUMN_PERSON_SURNAME,user.getSurname());
        values.put(COLUMN_PERSON_PHONE,user.getPhone());
        values.put(COLUMN_PERSON_AGE, user.getAge());
        values.put(COLUMN_PERSON_WEIGHT, user.getWeight());
        values.put(COLUMN_PERSON_HEIGHT,user.getHeight());
        values.put(COLUMN_PERSON_PASSWORD,user.getPassword());


        int rowsAffected = db.update(PERSON_TABLE, values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();

        return rowsAffected > 0; // Return true if at least one row was affected
    }

    public List<ExerciseModel> getExercisesByCategory(String category) {
        List<ExerciseModel> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + EXERCISE_TABLE + " WHERE " + COLUMN_WORKED_MUSCLES + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{category});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(EXERCISE_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_NAME));
                String muscles = cursor.getString(cursor.getColumnIndex(COLUMN_WORKED_MUSCLES));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

                ExerciseModel exercise = new ExerciseModel(id, name, muscles, description);
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return exerciseList;
    }

    public List<ExerciseModel> showSelectedExercises(List<String> categories) {
        List<ExerciseModel> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_WORKED_MUSCLES + " IN (" + TextUtils.join(",", Collections.nCopies(categories.size(), "?")) + ")";
        String[] selectionArgs = categories.toArray(new String[0]);

        String query = "SELECT * FROM " + EXERCISE_TABLE + " WHERE " + selection;
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(EXERCISE_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_NAME));
                String muscles = cursor.getString(cursor.getColumnIndex(COLUMN_WORKED_MUSCLES));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

                ExerciseModel exercise = new ExerciseModel(id, name, muscles, description);
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return exerciseList;
    }

    public List<String> getAllMuscleGroups() {
        List<String> muscleGroupList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT DISTINCT " + COLUMN_WORKED_MUSCLES + " FROM " + EXERCISE_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String muscleGroup = cursor.getString(cursor.getColumnIndex(COLUMN_WORKED_MUSCLES));
                muscleGroupList.add(muscleGroup);
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return muscleGroupList;
    }

    public List<WorkoutPlanModel> getAllWorkoutPlans(){
        List<WorkoutPlanModel> res=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT DISTINCT " + WORKOUT_PLAN_TABLE + ".*, " + WORKOUT_TABLE + "." + COLUMN_WORKOUT_PLAN_ID +
                " FROM " + WORKOUT_PLAN_TABLE +
                " INNER JOIN " + WORKOUT_TABLE + " ON " + WORKOUT_PLAN_TABLE + "." + PLAN_ID + " = " + WORKOUT_TABLE + "." + COLUMN_WORKOUT_PLAN_ID;

        Cursor cursor= db.rawQuery(query,null);

        if (cursor!=null && cursor.moveToFirst()){
            do {
                int id= cursor.getInt(cursor.getColumnIndex(PLAN_ID));
                String name= cursor.getString(cursor.getColumnIndex(COLUMN_PLAN_NAME));
                String creationDateStr = cursor.getString(cursor.getColumnIndex(COLUMN_CREATION_DATE));

                Date date = new Date(creationDateStr);


                WorkoutPlanModel workoutPlanModel=new WorkoutPlanModel(id,name,date);
                res.add(workoutPlanModel);

            }while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return res;
    }

    public int getLastPlanID() {
        SQLiteDatabase db = this.getReadableDatabase();
        int lastInsertedId = -1;

        String query = "SELECT " + PLAN_ID + " FROM " + WORKOUT_PLAN_TABLE + " ORDER BY " + PLAN_ID + " DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            lastInsertedId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return lastInsertedId;
    }



}
