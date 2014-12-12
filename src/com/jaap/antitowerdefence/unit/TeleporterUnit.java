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
    
    /**
     * Runs super constructor and sets teleporters speed, cost, health and
     * portal variables
     */
    public TeleporterUnit(Terrain[] walkable, int stepsPerSec) {
	super(walkable, stepsPerSec);
	cost = 200;
	health = 100;
	placedPortals = 0;
	resetCoolDown();
    }
    
    /**
     * Places a portal on a given position
     */
    public void placePortal() {
	System.out.println("Places portal on posX: " + getPosition().getX() + ", Y: " + getPosition().getY());
	int terrainIndex = getTerrainIndex(position);
	if(!(walkable[terrainIndex] instanceof Road)) {
	    System.out.println("Not road");
	    return;
	} else if(placedPortals == 0) {
	    placedPortals++;
	    firstPortal = new Portal(this.position);
	    walkable[terrainIndex] = firstPortal;
	    placedPortals = 1;
	} else if(placedPortals == 1 && 
		!firstPortal.getPosition().equals(this.position)) {
	    Portal reciever = new Portal(this.position);
	    firstPortal.setReciever(reciever.getPosition(), direction);
	    walkable[terrainIndex] = reciever;
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

}
