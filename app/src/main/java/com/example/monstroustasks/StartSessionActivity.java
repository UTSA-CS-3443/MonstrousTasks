package com.example.monstroustasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.Task;
import com.example.monstroustasks.model.TaskList;

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

        Button task1 = findViewById(R.id.HW1);
        Button task2 = findViewById(R.id.HW2);
        Button task3 = findViewById(R.id.HW3);
        Button task4 = findViewById(R.id.HW4);
        Button task5 = findViewById(R.id.HW5);

        final String[] hints = {task1.getHint().toString(), task2.getHint().toString(), task3.getHint().toString(), task4.getHint().toString(), task5.getHint().toString()};

        final boolean[] t = {false, false, false, false, false};

        task1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[0] = !t[0];
                if (!t[0]) {
                    task1.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.white));
                    task1.setTextColor(StartSessionActivity.this.getColorStateList(R.color.black));
                } else {
                    task1.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.green));
                    task1.setTextColor(StartSessionActivity.this.getColorStateList(R.color.white));
                }
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[1] = !t[1];
                if (!t[1]) {
                    task2.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.white));
                    task2.setTextColor(StartSessionActivity.this.getColorStateList(R.color.black));
                } else {
                    task2.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.green));
                    task2.setTextColor(StartSessionActivity.this.getColorStateList(R.color.white));
                }
            }
        });

        task3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[2] = !t[2];
                if (!t[2]) {
                    task3.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.white));
                    task3.setTextColor(StartSessionActivity.this.getColorStateList(R.color.black));
                } else {
                    task3.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.green));
                    task3.setTextColor(StartSessionActivity.this.getColorStateList(R.color.white));
                }
            }
        });

        task4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[3] = !t[3];
                if (!t[3]) {
                    task4.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.white));
                    task4.setTextColor(StartSessionActivity.this.getColorStateList(R.color.black));
                } else {
                    task4.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.green));
                    task4.setTextColor(StartSessionActivity.this.getColorStateList(R.color.white));
                }
            }
        });

        task5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[4] = !t[4];
                if (!t[4]) {
                    task5.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.white));
                    task5.setTextColor(StartSessionActivity.this.getColorStateList(R.color.black));
                } else {
                    task5.setBackgroundTintList(StartSessionActivity.this.getColorStateList(R.color.green));
                    task5.setTextColor(StartSessionActivity.this.getColorStateList(R.color.white));
                }
            }
        });

        Button continue_button = findViewById(R.id.continue_button);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean someTaskTrue = false;
                for (int i = 0; i < 5; i++) {
                    if (t[i]) {
                        someTaskTrue = true;
                        break;
                    }
                }
                if (someTaskTrue) {
                    Intent i = new Intent(StartSessionActivity.this, GameActivity.class);
                    for (int j = 0; j < 5; j++) {
                        if (t[j]) {
                            i.putExtra(String.format("task%d", j+1), hints[j]);
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