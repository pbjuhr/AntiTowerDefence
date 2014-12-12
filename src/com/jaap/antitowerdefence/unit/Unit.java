package com.jaap.antitowerdefence.unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.LandOnInterface;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Unit represents a troup-unit in the game.
 * @author Peter Bjuhr
 */
public abstract class Unit {

    /* Variables */
    public static int cost; 	   // How much the unit costs
    protected double speed; 	   // How often does the unit move
    protected int health; 	   // The unit's current health
    protected long coolDown; 	   // Steps until next move
    protected int stepsPerSec;     // steps per second in game
    protected Direction direction; // The units direction
    protected Position position;   // The units current position
    protected Terrain[] walkable;  // All walkable terrain objects in level
    private boolean reachedGoal;   // Has the unit reached the goal
    private boolean wasTeleported; // Has the unit been teleported

    /**
     * Constructor creates the pathHistory ArrayList and sets reachGoal to
     * false.
     */
    public Unit(Terrain[] walkable, int stepsPerSec) {
	this.walkable = walkable;
	this.stepsPerSec = stepsPerSec;
	position = new Position(0,0);
	wasTeleported = false;
	reachedGoal = false;
	setStartPosition();
    }
    
    /**
     * Sets the position and direction to the start position
     */
    private void setStartPosition(){
	for(Terrain t : walkable) {
	    if(t instanceof Start) {
		this.setPosition(t.getPosition());
		this.setDirection(((Start)t).getDirection());
	    }
	}
    }
    
    /**
     * Resets the coolDown
     */
    protected void resetCoolDown() {
	coolDown = Math.round(stepsPerSec/speed);
    }
    
    /**
     * Runs the Unit thread
     * @param currentStep , to determine if its time to walk
     */
    public void action() {
	if(coolDown > 0){
	    coolDown--;
	} else{
	    wasTeleported = false;
	    if(isAlive() && !reachedGoal){
		runLandOn(getTerrainIndex(position));
		if(!reachedGoal && !wasTeleported){
		    move();
		    resetCoolDown();
		}
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
	// Get the index of next terrain object, depending on current direction
	if (direction == Direction.NORTH) {
	    nextPositionIndex = getTerrainIndex(position.getPosToNorth());
	    if(nextPositionIndex == -1) {
		nextPositionIndex = findNeighbourIndexOfNorth();
	    }
	} else if (direction == Direction.SOUTH) {
	    nextPositionIndex = getTerrainIndex(position.getPosToSouth());
	    if(nextPositionIndex == -1) {
		nextPositionIndex = findNeighbourIndexOfSouth();
	    }
	} else if (direction == Direction.EAST) {
	    nextPositionIndex = getTerrainIndex(position.getPosToEast());
	    if(nextPositionIndex == -1) {
		nextPositionIndex = findNeighbourIndexOfEast();
	    }
	} else if (direction == Direction.WEST) {
	    nextPositionIndex = getTerrainIndex(position.getPosToWest());
	    if(nextPositionIndex == -1) {
		nextPositionIndex = findNeighbourIndexOfWest();
	    }
	}
	setPosition(walkable[nextPositionIndex].getPosition());	
    }
    
    /**
     * Looks in the direction in order west, east, south and changes the units
     * direction according to where a walkable terrain exists.
     * @return the index in the walkable array of the terrain object
     */
    private int findNeighbourIndexOfNorth() {
	int neighbourIndex = -1;
	neighbourIndex = getTerrainIndex(position.getPosToWest());
	if(neighbourIndex != -1) {
	    setDirection(Direction.WEST);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToEast());
	if(neighbourIndex != -1) {
	    setDirection(Direction.EAST);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToSouth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.SOUTH);
	    return neighbourIndex;
	}
	return neighbourIndex;
    }
    
    /**
     * Looks in the direction in order east, west, north and changes the units
     * direction according to where a walkable terrain exists.
     * @return the index in the walkable array of the terrain object
     */
    private int findNeighbourIndexOfSouth() {
	int neighbourIndex = -1;
	neighbourIndex = getTerrainIndex(position.getPosToEast());
	if(neighbourIndex != -1) {
	    setDirection(Direction.EAST);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToWest());
	if(neighbourIndex != -1) {
	    setDirection(Direction.WEST);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToNorth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.NORTH);
	    return neighbourIndex;
	}
	return neighbourIndex;
    }
    
    /**
     * Looks in the direction in order north, south, west and changes the units
     * direction according to where a walkable terrain exists.
     * @return the index in the walkable array of the terrain object
     */
    private int findNeighbourIndexOfEast() {
	int neighbourIndex = -1;
	neighbourIndex = getTerrainIndex(position.getPosToNorth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.NORTH);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToSouth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.SOUTH);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToWest());
	if(neighbourIndex != -1) {
	    setDirection(Direction.WEST);
	    return neighbourIndex;
	}
	return neighbourIndex;
    }
    
    /**
     * Looks in the direction in order south, north, east and changes the units
     * direction according to where a walkable terrain exists.
     * @return the index in the walkable array of the terrain object
     */
    private int findNeighbourIndexOfWest() {
	int neighbourIndex = -1;
	neighbourIndex = getTerrainIndex(position.getPosToSouth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.SOUTH);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToNorth());
	if(neighbourIndex != -1) {
	    setDirection(Direction.NORTH);
	    return neighbourIndex;
	}
	neighbourIndex = getTerrainIndex(position.getPosToEast());
	if(neighbourIndex != -1) {
	    setDirection(Direction.EAST);
	    return neighbourIndex;
	}
	return neighbourIndex;
    }
    
    /**
     * Runs the LandOn method(if it exists) on a given Terrain object
     * @param currentPositionIndex, the terrain objects index in walkable
     */
    private void runLandOn(int currentPositionIndex) {
	if(walkable[currentPositionIndex] instanceof LandOnInterface) {
	    
	    // Get the landOn method
	    Method landOnMethod = null;
	    try {
		landOnMethod = walkable[currentPositionIndex].getClass().
			getDeclaredMethod("landOn", Unit.class);
	    } catch (NoSuchMethodException | SecurityException e1) {
		e1.printStackTrace();
		return;
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
     * Gets the index of a terrain-object (with a given position) from walkable
     * @param p, the position we're looking at
     * @return i, the terrain objects index, or -1 if no object was found on 
     * that position
     */
    protected int getTerrainIndex(Position p) {
	for (int i = 0; i < walkable.length; i++) {
	    if (walkable[i].getPosition().equals(p)) {
		return i;
	    }
	}
	return -1;
    }
    
    /**
     * The unit gets damaged and the health gets reduced by 20.
     * It also resets the coolDown counter because of falling :(
     */
    public void takeDamage() {
	this.health -= 20;
	resetCoolDown();
    }
    
    /**
     * Check the units health and returns whether its alive or not
     * @return true if the unit is alive, otherwise false
     */
    public boolean isAlive() {
	return (this.health > 0);
    }

    /**
     * Gets the units current position
     * @return Position, the current position
     */
    public Position getPosition() {
	return this.position;
    }

    /**
     * Sets the units position
     * @param p, the new position
     */
    public void setPosition(Position p) {
	position = p;
    }

    /**
     * Checks if the unit has reached the maps goal
     * @param goalPos, the goals position
     * @return true, if the unit has reached the goal, otherwise false
     */
    public boolean hasReachedGoal() {
	return reachedGoal;
    }

    /**
     * Sets the reached goal to true or false
     * @param reachedGoal, the new reached goal value
     */
    public void setReachedGoal(boolean reachedGoal) {
	this.reachedGoal = reachedGoal;
    }

    /**
     * Sets the direction string
     * @param newDirection, string with new direction. Must be "north", "south",
     * "east"or "west"
     */
    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

    /**
     * Gets the current direction
     * @return direction, String
     */
    public Direction getDirection() {
	return this.direction;
    }
    
    /**
     * Gets the cost of the unit
     * @return cost, the cost of the Unit
     */
    public static int getCost() {
	return cost;
    }
    
    /**
     * Toggles wasTeleported variable between true and false
     */
    public void toggleTeleported(){
	if(wasTeleported){
	    wasTeleported = false;
	} else{
	    wasTeleported = true;
	}
    }
    
    /**
     * Gets the wasTeleported
     * @return true if the unit has been teleported, otherwise false
     */
    public boolean hasBeenTeleported(){
	return this.wasTeleported;
    }
    
    /**
     * Gets the health of the unit
     * @return health, the health of the Unit
     */
    public int getHealth() {
	return this.health;
    }
    
    /**
     * Gets the speed of the unit
     * @return speed
     */
    public double getSpeed() {
	return speed;
    }
    
    /**
     * Gets the coolDown. (number of steps until next move)
     * @return coolDown (int)
     */
    public int getCoolDown() {
	return (int) coolDown;
    }
}
