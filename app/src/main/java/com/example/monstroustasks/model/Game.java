package com.example.monstroustasks.model;

import android.util.Pair;

import java.util.ArrayList;

public class Game {
    ArrayList<Pair<Task, Boolean>> tasks;
    int health;
    int sessionID;

    public Game(ArrayList<Pair<Task, Boolean>> tasks, int health, int sessionID) {
        this.tasks = tasks;
        this.health = health;
        this.sessionID = sessionID;
    }

    public ArrayList<Pair<Task, Boolean>> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Pair<Task, Boolean>> tasks) {
        this.tasks = tasks;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}
