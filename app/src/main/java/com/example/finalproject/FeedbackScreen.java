package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackScreen extends AppCompatActivity {

    Button submit;
    EditText subject, description;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_screen);

        dataBaseHelper = new DataBaseHelper(this);

        PersonModel currentUser = DataBaseHelper.getCurrentUser();

        submit = findViewById(R.id.submitFeedback);
        subject = findViewById(R.id.subject);
        description = findViewById(R.id.description);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FeedbackModel feedbackModel;

                String subjectFB = subject.getText().toString();
                String descriptionFB = description.getText().toString();

                if(subjectFB.equals("")){
                    Toast.makeText(FeedbackScreen.this, "The subject is necessary", Toast.LENGTH_SHORT).show();
                } else {

                    feedbackModel = new FeedbackModel(-1, currentUser.getId(), subjectFB, descriptionFB);
                    dataBaseHelper.addFeedback(feedbackModel);

                    Toast.makeText(FeedbackScreen.this, "Sent with success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FeedbackScreen.this, SettingsScreen.class);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseHelper.close(); // Close the database when the activity is destroyed
    }

}