package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.TaskList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DeleteTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tasks);

        TaskList taskList = new TaskList();
        taskList.loadTasks(DeleteTasksActivity.this);

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
                        button.setBackgroundTintList(getColorStateList(R.color.red));
                        button.setTextColor(getColorStateList(R.color.white));
                    }
                }
            });
            taskLayout.addView(button);
        }

        Button confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = t.size() - 1; i >= 0; i--) {
                    if (t.get(i)) {
                        taskList.removeTask(taskList.getTaskList().get(i).getTaskName());
                    }
                }
                try {
                    taskList.saveTasks(DeleteTasksActivity.this);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                Intent i = new Intent(DeleteTasksActivity.this, SettingsActivity.class);
                startActivity(i);

            }
        });

    }
}