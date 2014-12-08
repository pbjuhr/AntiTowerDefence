/**
 * A Unit represents a human in the game.
 * Every Unit is running its own Thread
 * @author Peter Bjuhr
 */
package com.jaap.antitowerdefence.unit;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Terrain;

public abstract class Unit extends Thread {

    /* Variables */
    protected int speed; // The speed the unit walks in
    protected int health; // The unit's current health
    protected int cost; // How much the unit costs
    protected int coolDown;
    protected boolean hasWalked;
    protected String direction; // The units direction
    protected Position position; // The units current position
    protected Position endPortalPosition;
    protected Terrain[] walkable;
    private ArrayList<Position> pathHistory; // the units visited positions
    private boolean reachedGoal; // Has the unit reached the goal

    /**
     * Constructor creates the pathHistory ArrayList and sets reachGoal to
     * false.
     */
    public Unit(Terrain[] walkable) {
	this.walkable = walkable;
	pathHistory = new ArrayList<Position>();
	reachedGoal = false;
	hasWalked = false;
    }

    /**
     * Runs the Unit thread
     */
    public void run() {
	while (true) {
	    hasWalked = false;
	    //sleep(coolDown);
	    if (!isDead()) {
		move();
	    } else {
		break;
	    }
	}
    }

    /**
     * Moves the unit to it's next position, and adds latest pos to pathhistory
     * @param neighbours, an array of the units neighbours
     */
    public void move() {
	pathHistory.add(position);
	for(Terrain t : walkable){
	    
	}
	hasWalked = true;
    }

    /**
     * The unit gets damaged and the health gets reduced by 20.
     */
    public void takeDamage() {
	this.health -= 20;
    }

    /**
     * Check the units health and returns whether its dead or not
     * 
     * @return true if the unit is dead, otherwise false
     */
    public boolean isDead() {
	return (health <= 0);
    }
    
    /**
     * Gets a terrain object (with same position as the unit) index 
     * @return i, the terrain objects index, or -1 if no object was found
     */
    protected int getCurrentTerrainIndex() {
	for(int i = 0; i < walkable.length; i++){
	    if(walkable[i].getPosition().equals(position)) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * Gets the units current position
     * 
     * @return Position, the current position
     */
    public Position getPosition() {
	return position;
    }

    
    /**
     * Gets the end portal position
     * 
     */
    public void setEndPortalPosition(Position p) {
	endPortalPosition = p;
    }
    
    /**
     * Checks if the unit has reached the maps goal
     * 
     * @param goalPos, the goals position
     * @return true, if the unit has reached the goal, otherwise false
     */
    public boolean hasReachedGoal(Position goalPos) {
	return goalPos.equals(position);
    }

    /**
     * Sets the reached goal to true or false
     * 
     * @param reachedGoal, the new reached goal value
     */
    public void setReachedGoal(boolean reachedGoal) {
	this.reachedGoal = reachedGoal;
    }

    /**
     * Sets the direction string
     * 
     * @param newDirection, string with new direction. Must be "north", "south", "east"
     * or "west"
     */
    public void setDirection(String newDirection) {
	if (newDirection != "north" || newDirection != "south"
		|| newDirection != "east" || newDirection != "west") {
	    return;
	}
	this.direction = newDirection;
    }

    /**
     * Gets the current direction
     * 
     * @return direction, String
     */
    public String getDirection() {
	return this.direction;
    }
}
