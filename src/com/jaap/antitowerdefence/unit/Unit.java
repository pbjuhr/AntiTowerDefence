package com.jaap.antitowerdefence.unit;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Unit {

    protected int speed;
    protected int health;
    protected int cost;
    protected String direction;
    protected Position position;
    private ArrayList<Position> pathHistory; // För att gubben inte skall gå bakåt
    private boolean reachedGoal;

    public Unit() {
	pathHistory = new ArrayList<Position>();
	reachedGoal = false;
    }
    
    protected void setSpeed(int speed){
	this.speed = speed;
    }
    
    protected void setCost(int cost){
	this.cost = cost;
    }
    
    protected void setHealth(int health){
	this.health = health;
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
