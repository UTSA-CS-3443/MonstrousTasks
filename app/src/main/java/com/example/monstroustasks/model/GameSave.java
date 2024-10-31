package com.example.monstroustasks.model;

public class GameSave {
    int sessionID;
    int easy, medium, hard;

    GameSave(int sessionID, int easy, int medium, int hard) {
        this.sessionID = sessionID;
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
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
