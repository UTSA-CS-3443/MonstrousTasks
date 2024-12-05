package com.example.monstroustasks.model;

/**
 * A representation of a previous game save, storing the amount of easy, medium, and hard monsters were defeated.
 * A score can be calculated with this information, as seen in Profile.
 *
 * @author BitByBit
 */
public class GameSave {
    int easy, medium, hard;

    /**
     * Constructs a GameSave with the number of easy, medium, and hard monsters.
     * This is used primarily in Profile to collect user history of games.
     */
    public GameSave(int easy, int medium, int hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }

    /*
    Getters and setters are self explanatory.
     */
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

    public String toString() {
        return String.format("%d,%d,%d", this.easy, this.medium, this.hard);
    }

}
