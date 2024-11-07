package com.example.monstroustasks.model;

import java.util.Random;

public class Monster extends Task {
    boolean alive;
    int id;

    Monster(Task task) {
        super(task.getTaskName(), task.getDifficulty());
        this.alive = true;
        switch (task.getDifficulty()) {
            case 1:
                this.id = (int)(Math.random() * 6 + 1);
                break;
            default:
                this.id = (int)(Math.random() * 5 + 1);
                break;
        }
    }

    public void complete() {
        alive = false;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
