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

/**
 * A class representing a Profile, storing all of the user's history of game sessions.
 * A Profile is initialized with loadProfile(), which loads all of the GameSaves into the class.
 *
 * @author BitByBit
 */
public class Profile {
    ArrayList<GameSave> saves;

    public Profile() {
    }

    public void loadProfile(Context context) {
        saves = new ArrayList<>();
        try {
            // We initially try to open a profile.csv.
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
            // We catch an exception opening the file, likely meaning the file does not exist.
            // Therefore, we create a file!
            Log.e("ERROR: loadProfile()", "Could not find profile.csv... creating placeholder profile.csv.");

            try {
                OutputStream outputStream = context.openFileOutput("profile.csv", Context.MODE_PRIVATE);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write("0,0,0\n");

                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();

                // we can then do a single recursive call to loadProfile() since our file has been generated.
                loadProfile(context);
            } catch (IOException ed) {
                Log.e("loadProfile() ERROR", "Uh oh!");
            }
        }
    }

    public void saveProfile(Context context) throws RuntimeException {
        try {
            OutputStream outputStream = context.openFileOutput("profile.csv", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i = 0; i < this.getSavesSize(); i++) {
                bufferedWriter.write(String.format("%s\n", this.getGameSaves().get(i).toString()));
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
        } catch (IOException e) {
            // we had an issue opening profile.csv. This means it was deleted after creating the file in loadProfile().
            // not cool!
            throw new RuntimeException(e);
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

    /*
    getTotalEasy(), getTotalMedium(), and getTotalHard() generate the total amount of
    easy, medium, and hard monsters the user has defeated over the entirety of their
    game sessions.
     */
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
