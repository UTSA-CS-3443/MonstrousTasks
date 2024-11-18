package com.example.monstroustasks.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Profile {
    ArrayList<GameSave> saves;

    public Profile() {
    }

    public void loadProfile(Context context) {
        saves = new ArrayList<>();
        try {
            InputStream inputStream = context.openFileInput("profile.csv");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ((receiveString = bufferedReader.readLine()) != null) {
                    saves.add(new GameSave(Integer.parseInt(receiveString.split(",")[0]), Integer.parseInt(receiveString.split(",")[1].trim()), Integer.parseInt(receiveString.split(",")[2])));
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            }
        } catch (IOException e) {
            Log.e("ERROR: loadProfile()", "Could not find tasks.csv... creating placeholder profile.csv.");

            try {
                OutputStream outputStream = context.openFileOutput("profile.csv", Context.MODE_PRIVATE);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write("0,2,1\n" +
                        "2,4,1\n" +
                        "0,0,1\n" +
                        "1,3,2\n" +
                        "8,0,0\n" +
                        "2,0,1");

                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();

                try {
                    InputStream inputStream = context.openFileInput("profile.csv");

                    if (inputStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            saves.add(new GameSave(Integer.parseInt(receiveString.split(",")[0]), Integer.parseInt(receiveString.split(",")[1].trim()), Integer.parseInt(receiveString.split(",")[2])));
                        }

                        bufferedReader.close();
                        inputStreamReader.close();
                        inputStream.close();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException ed) {
                Log.e("loadProfile() ERROR", "Uh oh!");
            }
        }
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
        int max = 0;
        for (int i = 0; i < this.getGameSaves().size(); i++) {
            int saveScore = this.getGameSaves().get(i).getEasy() + this.getGameSaves().get(i).getMedium()*2 + this.getGameSaves().get(i).getHard()*3;
            if (saveScore > max) {
                max = saveScore;
            }
        }
        return max;
    }

    @NonNull
    @Override
    public String toString() {
        return "";
    }
}
