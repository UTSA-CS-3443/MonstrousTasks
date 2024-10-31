package com.example.monstroustasks.model;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public void loadTasks() {

    }

    public void addTask(String taskName, int difficulty) {
        taskList.add(new Task(taskName, difficulty));
    }

    public void removeTask(String taskName) {

    }

    public void saveTasks() {

    }

    public ArrayList<Task> getTaskList() {

        return new ArrayList<Task>();
    }

    public void setTaskList(ArrayList<Task> taskList) {

    }

    public int getSize() {

        return 0;
    }
}
