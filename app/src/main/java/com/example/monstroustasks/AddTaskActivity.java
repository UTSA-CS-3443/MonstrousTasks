package com.example.monstroustasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monstroustasks.model.TaskList;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_task);

        TaskList taskList = new TaskList();
        taskList.loadTasks(this);

        TextInputEditText textField = findViewById(R.id.editText);
        Slider slider = findViewById(R.id.slider);

        Button button = findViewById(R.id.confirm_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = textField.getText().toString();
                int difficulty = (int)(slider.getValue() * 2) + 1;
                if (taskList.findTask(taskName)) {
                    Toast toast = new Toast(AddTaskActivity.this);
                    toast.setText("Found identical task. Try another task!");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else if (taskName.length() == 0) {
                    Toast toast = new Toast(AddTaskActivity.this);
                    toast.setText("Text field is empty!");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    taskList.addTask(taskName, difficulty);
                    try {
                        taskList.saveTasks(AddTaskActivity.this);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Intent i = new Intent(AddTaskActivity.this, SettingsActivity.class);
                    startActivity(i);
                }
            }
        });


    }
}