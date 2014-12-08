/**
 * A Unit represents a human in the game.
 * Every Unit is running its own Thread
 * @author Peter Bjuhr
 */
package com.jaap.antitowerdefence.unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.LandOnInterface;
import com.jaap.antitowerdefence.terrain.Terrain;

public abstract class Unit extends Thread {

    /* Variables */
    protected int speed; // The speed the unit walks in
    protected int health; // The unit's current health
    protected int cost; // How much the unit costs
    protected int animationSpeed;
    protected boolean hasMoved;
    protected String direction; // The units direction
    protected Position position; // The units current position
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
	hasMoved = false;
    }

    /**
     * Runs the Unit thread
     */
    public void run() {
	while (true) {
	    hasMoved = false;
	    if (!isDead()) {
		move();
		try {
		    sleep(animationSpeed);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		runLandOn(getTerrainIndex(this.position));
	    } else {
		break;
	    }
	}
    }

    /**
     * Moves the unit to it's next position, and adds latest pos to pathhistory
     * @param neighbours, an array of the units neighbours
     */
    private void move() {
	
	//What index in the walkable-array we should move to
	int nextPositionIndex = -1; 

	while (nextPositionIndex == -1) {
	    if (direction.equals("north")) {
		nextPositionIndex = getTerrainIndex(position.getPosToNorth());
	    } else if (direction.equals("south")) {
		nextPositionIndex = getTerrainIndex(position.getPosToSouth());
	    } else if (direction.equals("east")) {
		nextPositionIndex = getTerrainIndex(position.getPosToEast());
	    } else if (direction.equals("west")) {
		nextPositionIndex = getTerrainIndex(position.getPosToWest());
	    }
	    if(nextPositionIndex == -1) {
		spin();
	    }
	}
	pathHistory.add(position);
	setPosition(walkable[nextPositionIndex].getPosition());
	hasMoved = true;
    }
    
    /**
     * Rotates the unit (changes it direction) clockwise 90 degrees
     */
    private void spin(){
	if(direction.equals("north")){
	    setDirection("east");
	} else if(direction.equals("south")){
	    setDirection("west");
	} else if(direction.equals("east")){
	    setDirection("south");
	} else if(direction.equals("west")){
	    setDirection("north");
	}
    }
    
    /**
     * Runs the LandOn method(if exists) on a given Terrain object
     * @param currentPositionIndex, the terrain objects index in walkable
     */
    private void runLandOn(int currentPositionIndex) {
	if(walkable[currentPositionIndex] instanceof LandOnInterface) {
	    
	    // Get the class LandOnInterface
	    Class<?> landOnInterface = null;
	    try {
		landOnInterface = Class.forName("LandOnInterface");
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }
	    
	    // Get the landOn method
	    Method landOnMethod = null;
	    try {
		landOnMethod = landOnInterface.getMethod("landOn");
	    } catch (NoSuchMethodException | SecurityException e1) {
		e1.printStackTrace();
	    }
	    
	    // Run the landOn method with the current Terrain object
	    try {
		landOnMethod.invoke(walkable[currentPositionIndex], this);
	    } catch (IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException e2) {
		e2.printStackTrace();
	    }
	}
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
	return (this.health <= 0);
    }

    /**
     * Gets the index of a terrain-object (with a given position) from walkable
     * @param p, the position we're looking at
     * @return i, the terrain objects index, or -1 if no object was found on 
     * that position
     */
    protected int getTerrainIndex(Position p) {
	for (int i = 0; i < walkable.length; i++) {
	    if (walkable[i].getPosition().getY() == p.getY()
		    && walkable[i].getPosition().getX() == p.getX()) {
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
	return this.position;
    }

    /**
     * sets the units position
     * 
     * @param p, the new position
     */
    public void setPosition(Position p) {
	this.position.setX(p.getX());
	this.position.setY(p.getY());
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
     * @param newDirection, string with new direction. Must be "north", "south",
     * "east"or "west"
     */
    public void setDirection(String newDirection) {
	if (newDirection.equals("north") || newDirection.equals("south")
		|| newDirection.equals("east") || newDirection.equals("west")) {
	    this.direction = newDirection;
	}
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
