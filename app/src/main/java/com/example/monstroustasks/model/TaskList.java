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

/**
 * A class representation for a list of tasks.
 * These are gathered from tasks.csv.
 * Some tasks are generated as placeholders to let users get started with generic tasks,
 * but these can be deleted at anytime.
 *
 * @author BitByBit
 */
public class TaskList {
    ArrayList<Task> taskList;

    public void loadTasks(Context context) {
        taskList = new ArrayList<Task>();
        try {
            // Attempt to open tasks.csv
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
            // tasks.csv did not exist, therefore we must create it!
            Log.e("ERROR: loadTasks()", "Could not find tasks.csv... creating tasks.csv.");

            try {
                OutputStream outputStream = context.openFileOutput("tasks.csv", Context.MODE_PRIVATE);
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

                // we can do a single recursive call back to loadTasks() now that we created tasks.csv.
                loadTasks(context);
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

    /**
     * Saves the tasks to tasks.csv
     * @param context           Context: context for the currently open activity instance
     * @throws FileNotFoundException
     */
    public void saveTasks(Context context) throws FileNotFoundException {
        try {
            OutputStream outputStream = context.openFileOutput("tasks.csv", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i = 0; i < this.getSize(); i++) {
                bufferedWriter.write(String.format("%s\n", this.getTaskList().get(i).toString()));
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
        } catch (IOException e) {
            // this happens if tasks.csv no longer exists. saveTasks() is not called before loadTasks(),
            // so if this happens, something wrong must've happened!
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a task in the taskList
     * @param taskName      String: task name
     * @return              boolean: whether the task was found or not.
     */
    public boolean findTask(String taskName) {
        for (int i = 0; i < this.getSize(); i++) {
            if (taskName.toLowerCase().equals(this.getTaskList().get(i).getTaskName().toLowerCase())) {
                return true;
            }
        }
        return false;
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
