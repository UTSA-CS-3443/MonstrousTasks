package com.example.monstroustasks.model;

import android.content.Context;
import android.util.Log;

import com.example.monstroustasks.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public void loadTasks(Context context) {
        taskList = new ArrayList<Task>();
        try {
            InputStream inputStream = context.openFileInput("tasks.csv");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ((receiveString = bufferedReader.readLine()) != null) {
                    taskList.add(new Task(receiveString.split(",")[0].trim(), Integer.parseInt(receiveString.split(",")[1].trim())));
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            }
        } catch (IOException e) {
            Log.e("ERROR: loadTasks()", "Could not find tasks.csv... creating tasks.csv.");

            try {
                OutputStream outputStream = context.openFileOutput("tasks.csv", Context.MODE_APPEND);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write("Homework,3\n" +
                        "Get Groceries,1\n" +
                        "Study,1\n" +
                        "Work Out,3\n" +
                        "Clean Room,2\n" +
                        "Take Out Trash,1");

                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();

                try {
                    InputStream inputStream = context.openFileInput("tasks.csv");

                    if (inputStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            taskList.add(new Task(receiveString.split(",")[0].trim(), Integer.parseInt(receiveString.split(",")[1].trim())));
                        }

                        bufferedReader.close();
                        inputStreamReader.close();
                        inputStream.close();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException ed) {
                Log.e("loadTasks() ERROR", "Uh oh!");
            }
        }
    }

    public void addTask(String taskName, int difficulty) {
        taskList.add(new Task(taskName, difficulty));
    }

    public void removeTask(String taskName) {
        for (int i = 0; i < this.getSize(); i++) {
            if (taskList.get(i).getTaskName().equals(taskName)) {
                taskList.remove(i);
                return;
            }
        }
    }

    public void saveTasks() {

    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }
}
