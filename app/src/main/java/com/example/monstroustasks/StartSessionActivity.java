package com.example.monstroustasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.Task;
import com.example.monstroustasks.model.TaskList;

import java.util.ArrayList;

public class StartSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);

        TaskList taskList = new TaskList();
        taskList.loadTasks(this);

        Button home_button = findViewById(R.id.ss_home_button);

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartSessionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        final ArrayList<Boolean> t = new ArrayList<>();
        LinearLayout taskLayout = findViewById(R.id.task_container);
        for (int i = 0; i < taskList.getSize(); i++) {
            t.add(false);
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);
            layoutParams.gravity = Gravity.CENTER;
            button.setLayoutParams(layoutParams);
            button.setGravity(View.TEXT_ALIGNMENT_CENTER);
            button.setBackgroundTintList(getColorStateList(R.color.white));
            button.setText(String.format("%s         Difficulty: %d", taskList.getTaskList().get(i).getTaskName(), taskList.getTaskList().get(i).getDifficulty()));
            button.setTextColor(getColorStateList(R.color.black));
            button.setHint(String.format("%d", i));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (t.get(Integer.parseInt(button.getHint().toString()))) {
                        t.set(Integer.parseInt(button.getHint().toString()), false);
                        button.setBackgroundTintList(getColorStateList(R.color.white));
                        button.setTextColor(getColorStateList(R.color.black));
                    } else {
                        t.set(Integer.parseInt(button.getHint().toString()), true);
                        button.setBackgroundTintList(getColorStateList(R.color.green));
                        button.setTextColor(getColorStateList(R.color.white));
                    }
                }
            });
            taskLayout.addView(button);
        }

        Button continue_button = findViewById(R.id.continue_button);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean someTaskTrue = false;
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i)) {
                        someTaskTrue = true;
                        break;
                    }
                }
                if (someTaskTrue) {
                    Intent i = new Intent(StartSessionActivity.this, GameActivity.class);
                    for (int j = 0; j < t.size(); j++) {
                        if (t.get(j)) {
                            i.putExtra(String.format("task_%d", j+1), taskList.getTaskList().get(j).toString());
                        }
                    }
                    startActivity(i);
                } else {
                    Toast toast = new Toast(StartSessionActivity.this);
                    toast.setText("Select at least one task.");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}