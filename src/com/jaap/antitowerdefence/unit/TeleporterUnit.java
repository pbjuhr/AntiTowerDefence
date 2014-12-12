package com.jaap.antitowerdefence.unit;
import com.jaap.antitowerdefence.terrain.Portal;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Teleporter Unit represents a sort of wizard who can place portals in the 
 * map. 
 * @author Peter Bjuhr
 */
public class TeleporterUnit extends Unit {
    
    /* Variables related to the portal */
    private Portal firstPortal;
    private int placedPortals;
    public static int cost = 200;
    
    /**
     * Runs super constructor and sets teleporters speed, cost, health and
     * portal variables
     */
    public TeleporterUnit(Terrain[] walkable, int stepsPerSec) {
	super(walkable, stepsPerSec);
	health = 100;
	speed = 3;
	placedPortals = 0;
	resetCoolDown();
    }
    
    /**
     * Places a portal on a given position
     */
    public void placePortal() {
	int terrainIndex = getTerrainIndex(position);
	if(!(walkable[terrainIndex] instanceof Road)) {
	    return;
	} else if(placedPortals == 0) {
	    placedPortals++;
	    firstPortal = new Portal(this.position);
	    walkable[terrainIndex] = firstPortal;
	    placedPortals = 1;
	} else if(placedPortals == 1 && 
		!firstPortal.getPosition().equals(this.position)) {
	    Portal receiver = new Portal(this.position);
	    receiver.setDirection(direction);
	    firstPortal.setReciever(receiver);
	    walkable[terrainIndex] = receiver;
	    placedPortals++;
	}
    }
    
    /**
     * Gets the teleporters portal
     * @return thePortal
     */
    public Portal getPortal() {
	return firstPortal;
    }
    
    /**
     * Gets the number of placed portals
     * @return placedPortals, nr of placed portals
     */
    public int getPlacedPortals() {
	return placedPortals;
    }
    
    /**
     * The Teleporters unit needs to remove a portal without receiver if it die
     */
    public void takeDamage() {
	super.takeDamage();
	// Needs to reset the portal to a road object
	if(health <= 0 && placedPortals == 1) {
	    resetFirstPortal();
	}
    }
    
    /**
     * Sets the reached goal to true or false.
     * If the unit has placed a portal, it needs to remove the first portal
     * @param reachedGoal, the new reached goal value
     */
    public void setReachedGoal(boolean reachedGoal) {
	super.setReachedGoal(reachedGoal);
	if(placedPortals == 1 && reachedGoal) {
	    resetFirstPortal();
	}
    }
    
    /**
     * Replaces the first portal object with a new road object
     */
    public void resetFirstPortal() {
	Road r = new Road(firstPortal.getPosition());
	int terrainIndex = getTerrainIndex(firstPortal.getPosition());
	walkable[terrainIndex] = r;
    }

}
