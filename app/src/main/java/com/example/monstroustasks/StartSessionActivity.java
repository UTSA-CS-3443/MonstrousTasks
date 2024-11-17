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

public class StartSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);

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

        final boolean[] t = {false, false, false, false, false};

        task1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[0] = !t[0];
                if (!t[0]) {
                    task1.setBackgroundColor(R.color.green);
                } else {
                    task1.setBackgroundColor(R.color.white);
                }
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[1] = !t[1];
                if (!t[1]) {
                    task2.setBackgroundColor(R.color.green);
                } else {
                    task2.setBackgroundColor(R.color.white);
                }
            }
        });

        task3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[2] = !t[2];
                if (!t[2]) {
                    task3.setBackgroundColor(R.color.green);
                } else {
                    task3.setBackgroundColor(R.color.white);
                }
            }
        });

        task4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[3] = !t[3];
                if (!t[3]) {
                    task4.setBackgroundColor(R.color.green);
                } else {
                    task4.setBackgroundColor(R.color.white);
                }
            }
        });

        task5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                t[4] = !t[4];
                if (!t[4]) {
                    task5.setBackgroundColor(R.color.green);
                } else {
                    task5.setBackgroundColor(R.color.white);
                }
            }
        });

        Button continue_button = findViewById(R.id.continue_button);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean someTaskTrue = false;
                for (int i = 0; i < 4; i++) {
                    if (t[i]) someTaskTrue = true;
                }
                if (someTaskTrue) {
                    Intent i = new Intent(StartSessionActivity.this, GameActivity.class);
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