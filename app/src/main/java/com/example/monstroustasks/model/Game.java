package com.example.monstroustasks.model;

import java.util.ArrayList;

public class Game {
    ArrayList<Monster> monsters;
    int health;
    // totalHealth will be loaded in with loadMonsters() and should not be changed during the game.
    // Health should be changed with updateHealth() after a Monster has been defeated.
    int totalHealth;

    public Game(ArrayList<Monster> monsters, int totalHealth) {
        this.monsters = monsters;
        this.totalHealth = totalHealth;
    }

    public void loadMonsters(ArrayList<Task> tasks) {
        this.monsters = new ArrayList<Monster>();
        for (int i = 0; i < tasks.size(); i++) {
            this.addMonster(new Monster(tasks.get(i)));
        }
        this.updateHealth();
        // total health is set to the initial amount of health.
        this.setTotalHealth(this.getHealth());
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public void addMonster(Monster monster) {
        this.getMonsters().add(monster);
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

    public void setTotalHealth(int health) {
        this.totalHealth = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void updateHealth() {
        int health = 0;
        for (int i = 0; i < this.getMonsters().size(); i++) {
            if (this.getMonsters().get(i).getAlive())
                health += this.getMonsters().get(i).getDifficulty();
        }
        this.setHealth(health);
    }
}
