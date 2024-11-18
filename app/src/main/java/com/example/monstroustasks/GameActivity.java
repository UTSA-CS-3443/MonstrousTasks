package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.Monster;
import com.example.monstroustasks.model.Task;
import com.example.monstroustasks.model.TaskList;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ArrayList<Monster> monsters = new ArrayList<Monster>();
        TaskList taskList = new TaskList();
        taskList.loadTasks(this);
        int totalHealth = 0;

        for (int i = 0; i < taskList.getSize(); i++) {
            if (getIntent().getExtras().containsKey(String.format("task_%d", i+1))) {
                String taskName = getIntent().getExtras().getString(String.format("task_%d", i+1)).split(",")[0];
                int difficulty = Integer.parseInt(getIntent().getExtras().getString(String.format("task_%d", i+1)).split(",")[1]);
                monsters.add(new Monster(new Task(taskName, difficulty)));
                totalHealth += difficulty;
            }
        }

        final int[] health = {totalHealth};
        int[] monsterCount = {0, 0, 0};

        LinearLayout monsterContainer = findViewById(R.id.monster_container);
        LinearLayout taskContainer = findViewById(R.id.task_container);
        ArrayList<Button> taskButtons = new ArrayList<Button>();

        for (int i = 0; i < monsters.size(); i++) {
            ImageView monster = new ImageView(this);
            Button button = new Button(this);
            button.setBackgroundTintList(getColorStateList(R.color.white));
            button.setText(monsters.get(i).getTaskName());
            button.setTextColor(getColorStateList(R.color.black));
            final boolean[] completed = {false};
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    completed[0] = !completed[0];
                    if (completed[0]) {
                        button.setBackgroundTintList(getColorStateList(R.color.green));
                        button.setTextColor(getColorStateList(R.color.white));
                        monster.setVisibility(View.INVISIBLE);
                        health[0] -= monsters.get(finalI).getDifficulty();
                        monsterCount[monsters.get(finalI).getDifficulty() - 1]++;
                    } else {
                        button.setBackgroundTintList(getColorStateList(R.color.white));
                        button.setTextColor(getColorStateList(R.color.black));
                        monster.setVisibility(View.VISIBLE);
                        health[0] += monsters.get(finalI).getDifficulty();
                        monsterCount[monsters.get(finalI).getDifficulty() - 1]--;
                    }
                }
            });
            taskButtons.add(button);
            String difficulty = monsters.get(i).getDifficultyString();
            monster.setImageResource(getResources().getIdentifier(String.format("%s_%d", difficulty, monsters.get(i).getId()), "drawable", getPackageName()));

            monster.setAdjustViewBounds(true);
            monster.setMaxWidth(200);
            monster.setMaxHeight(200);

            monsterContainer.addView(monster);
            taskContainer.addView(button);
        }

        Button endGame = findViewById(R.id.end_game_button);

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameActivity.this, EndGameActivity.class);
                i.putExtra("easy", monsterCount[0]);
                i.putExtra("medium", monsterCount[1]);
                i.putExtra("hard", monsterCount[2]);
                startActivity(i);
            }
        });
    }
}