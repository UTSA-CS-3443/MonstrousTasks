package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.Profile;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button homeButton = findViewById(R.id.home_button);

        Profile profile = new Profile();
        profile.loadProfile(ProfileActivity.this);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        TextView highscore = findViewById(R.id.highscore);
        TextView easy = findViewById(R.id.easy_tasks);
        TextView medium = findViewById(R.id.medium_tasks);
        TextView hard = findViewById(R.id.hard_tasks);
        TextView total = findViewById(R.id.tasks_completed);

        highscore.setText(String.format("High Score: %d", profile.getHighScore()));
        easy.setText(String.format("Easy Tasks: %d", profile.getTotalEasy()));
        medium.setText(String.format("Medium Tasks: %d", profile.getTotalMedium()));
        hard.setText(String.format("Hard Tasks: %d", profile.getTotalHard()));
        total.setText(String.format("Tasks Completed: %d", profile.getTotal()));
    }
}