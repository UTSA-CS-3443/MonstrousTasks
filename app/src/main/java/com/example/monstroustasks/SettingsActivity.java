package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button home_button = findViewById(R.id.home_button);
        Button delete_tasks = findViewById(R.id.delete_custom_tasks);
        Button add_task = findViewById(R.id.add_custom_tasks);

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        delete_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, DeleteTasksActivity.class);
                startActivity(i);
            }
        });

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, AddTaskActivity.class);
                startActivity(i);
            }
        });
    }
}



