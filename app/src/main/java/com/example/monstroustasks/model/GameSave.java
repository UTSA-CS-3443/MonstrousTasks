package com.example.monstroustasks.model;

public class GameSave {
    int easy, medium, hard;

    GameSave(int easy, int medium, int hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }

    public int getEasy() {
        return this.easy;
    }

    public int getMedium() {
        return this.medium;
    }

    public int getHard() {
        return this.hard;
    }

    public void setEasy(int easy) {
        this.easy = easy;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }

}
