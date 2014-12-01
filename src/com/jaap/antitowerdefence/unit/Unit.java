package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Unit {

    private int speed;
    private int health;
    private int cost;
    private String direction;
    private Position position;
    private Position[] pathHistory; // För att gubben inte skall gå bakåt
    private boolean reachedGoal;

    public Unit() {

    }

    public void move() {

    }

    public void takeDamage() {

    }

    public boolean isAlive() {
	return true;
    }

    // Return true if health <= 0
    public Position getPosition() {
	return position;

    }

    public boolean hasReachedGoal() {
	return true;
    }

}
