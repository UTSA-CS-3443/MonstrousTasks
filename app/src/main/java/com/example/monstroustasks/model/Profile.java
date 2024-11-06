package com.example.monstroustasks.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Profile {
    ArrayList<GameSave> saves;

    Profile(ArrayList<GameSave> gameSaves) {
        this.saves = gameSaves;
    }

    public void loadProfile() {

    }

    public ArrayList<GameSave> getGameSaves() {
        return this.saves;
    }

    public void setGameSaves(ArrayList<GameSave> saves) {
        this.saves = saves;
    }

    public int getSavesSize() {
        return saves.size();
    }

    public int getTotalEasy() {
        int sum = 0;
        for (int i = 0; i < saves.size(); i++) {
            sum += saves.get(i).getEasy();
        }
        return sum;
    }

    public int getTotalMedium() {
        int sum = 0;
        for (int i = 0; i < saves.size(); i++) {
            sum += saves.get(i).getMedium();
        }
        return sum;
    }

    public int getTotalHard() {
        int sum = 0;
        for (int i = 0; i < saves.size(); i++) {
            sum += saves.get(i).getHard();
        }
        return sum;
    }

    public int getTotal() {
        return getTotalEasy() + getTotalMedium() + getTotalHard();
    }

    public int getHighScore() {
        return getTotalEasy() + (2*getTotalMedium() + (3*getTotalHard()));
    }

    @NonNull
    @Override
    public String toString() {
        return "";
    }
}
