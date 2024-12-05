package com.example.monstroustasks.model;

import androidx.annotation.NonNull;

/**
 * A class representation of a Task, detailing the task's name and difficulty scaled to an integer.
 *
 * @author BitByBit
 */
public class Task {
    private String taskName;
    private int difficulty;

    public Task(String taskName, int difficulty) {
        this.taskName = taskName;
        this.difficulty = difficulty;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyString() {
        switch (this.difficulty) {
            case 1:
                return "easy";
            case 2:
                return "medium";
            case 3:
                return "hard";
        }
        return "";
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s,%d", this.getTaskName(), this.getDifficulty());
    }

}
