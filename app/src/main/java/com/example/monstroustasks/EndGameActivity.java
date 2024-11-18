package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        // Initialize the Home button
        Button homeButton = findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        TextView totalMonsters = findViewById(R.id.total_monsters);
        TextView easyMonsters = findViewById(R.id.easy_monsters);
        TextView mediumMonsters = findViewById(R.id.medium_monsters);
        TextView hardMonsters = findViewById(R.id.hard_monsters);



        easyMonsters.setText("Easy: " + easy);
        mediumMonsters.setText("Medium: " + medium);
        hardMonsters.setText("Hard: " + hard);

        int total = easy + medium + hard;
        totalMonsters.setText("Total Monsters: " + total);
    }
}
