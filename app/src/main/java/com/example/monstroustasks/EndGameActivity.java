package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstroustasks.model.GameSave;
import com.example.monstroustasks.model.Profile;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        int easy = (int) getIntent().getExtras().get("easy");
        int medium = (int) getIntent().getExtras().get("medium");
        int hard = (int) getIntent().getExtras().get("hard");
        int total = easy + medium + hard;
        int score = easy + 2*medium + 3*hard;

        // Initialize the Home button
        Button homeButton = findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Profile profile = new Profile();
        profile.loadProfile(EndGameActivity.this);
        profile.getGameSaves().add(new GameSave(easy, medium, hard));
        profile.saveProfile(EndGameActivity.this);


        TextView totalMonsters = findViewById(R.id.total_monsters);
        TextView easyMonsters = findViewById(R.id.easy_monsters);
        TextView mediumMonsters = findViewById(R.id.medium_monsters);
        TextView hardMonsters = findViewById(R.id.hard_monsters);
        TextView scoreView = findViewById(R.id.score);



        easyMonsters.setText("Easy: " + easy);
        mediumMonsters.setText("Medium: " + medium);
        hardMonsters.setText("Hard: " + hard);

        totalMonsters.setText("Total Monsters: " + total);
        scoreView.setText("Score: " + score);
    }
}
