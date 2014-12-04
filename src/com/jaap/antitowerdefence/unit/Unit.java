/**
 * A Unit represents a human in the game.
 * Every Unit is running its own Thread
 * @author Peter Bjuhr
 */
package com.jaap.antitowerdefence.unit;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Unit extends Thread{
    
    /* Variables */
    protected int speed; // The speed the unit walks in
    protected int health; // The unit's current health
    protected int cost; // How much the unit costs
    protected String direction; // The units direction
    protected Position position; // The units current position
    private ArrayList<Position> pathHistory; // the units visited positions
    private boolean reachedGoal; // Has the unit reached the goal
    
    /**
     * Constructor creates the pathHistory ArrayList and sets reachGoal to 
     * false.
     */
    public Unit() {
	pathHistory = new ArrayList<Position>();
	reachedGoal = false;
    }
    
    /**
     * Runs the Unit thread
     * TODO: ALGORITHM
     */
    public void run(){
	while(true) {
	    //this.move();
	}
    }
    
    /**
     * Moves the unit to it's next position
     * @param neighbours, an array of the units neighbours
     */
    public void move(Position[] neighbours) {
	
    }
    
    /**
     * The unit gets damaged and the health gets reduced by 20.
     */
    public void takeDamage() {
	this.health -= 20;
    }

    /**
     * Check the units health and returns whether its dead or not
     * @return true if the unit is dead, otherwise false
     */
    public boolean isDead() {
	return (health <= 0);
    }
    
    /**
     * Gets the units current position
     * @return Position, the current position
     */
    public Position getPosition() {
	return position;
    }
    
    /**
     * Checks if the unit has reached the maps goal
     * @param goalPos, the goals position
     * @return true, if the unit has reached the goal, otherwise false
     */
    public boolean hasReachedGoal(Position goalPos) {
	return true;
    }

}
