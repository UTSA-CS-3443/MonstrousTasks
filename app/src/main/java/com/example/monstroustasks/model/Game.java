package com.example.monstroustasks.model;

import java.util.ArrayList;

/**
 * A class representing an ongoing Game with an array of Monsters
 *
 * @author Bit By Bit
 */
public class Game {
    ArrayList<Monster> monsters;

    /**
     * Empty constructor for Game
     */
    public Game() {
        monsters = new ArrayList<>();
    }

    /**
     * Returns array of Monsters
     * @return              ArrayList<Monster>: monsters in the game
     */
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    /**
     * Sets a new array of Monsters
     * @param monsters      ArrayList<Monster>: new monsters set for the game
     */
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    /**
     * Adds a Monster to the array of Monsters
     * @param monster       Monster: monster to be added to monsters
     */
    public void addMonster(Monster monster) {
        this.getMonsters().add(monster);
    }
}
