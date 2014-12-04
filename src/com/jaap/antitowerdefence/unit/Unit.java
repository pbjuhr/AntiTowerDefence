package com.jaap.antitowerdefence.unit;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Unit extends Thread{

    protected int speed;
    protected int health;
    protected int cost;
    protected String direction;
    protected Position position;
    private ArrayList<Position> pathHistory;
    private boolean reachedGoal;

    public Unit() {
	pathHistory = new ArrayList<Position>();
	reachedGoal = false;
    }
    
    public void run(){
	while(true) {
	    //this.move();
	}
    }
    
    public void move(Position[] neighbours) {
	
    }

    public void takeDamage() {
	this.health -= 20;
    }

    public boolean isDead() {
	return (health <= 0);
    }

    public Position getPosition() {
	return position;
    }

    public boolean hasReachedGoal(Position goalPos) {
	return true;
    }

}
