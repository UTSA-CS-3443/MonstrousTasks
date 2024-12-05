package com.example.monstroustasks.model;

import java.util.Random;

/**
 * A class representing a Monster.
 * A monster is defeated when their assigned task is completed. This is decided in the GameActivity.
 * The id is merely for visual purposes, selecting a random integer in a given range to select a random image.
 * It's stored so in case the monster is defeated then tapped back into battle, its picture doesn't change.
 *
 * @author BitByBit
 */
public class Monster extends Task {
    int id;

    public Monster(Task task) {
        super(task.getTaskName(), task.getDifficulty());
        switch (task.getDifficulty()) {
            case 1:
                this.id = (int)(Math.random() * 6 + 1);
                break;
            default:
                this.id = (int)(Math.random() * 5 + 1);
                break;
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
