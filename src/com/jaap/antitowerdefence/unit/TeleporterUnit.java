/**
 * @author Peter Bjuhr
 * Class represents a teleporter unit
 */

package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Portal;

public class TeleporterUnit extends Unit {
    
    /* Variables related to the portal */
    private Portal thePortal;
    private int placedPortals;
    
    /**
     * Runs super constructor and sets teleporters speed, cost, health and
     * portal variables
     */
    public TeleporterUnit() {
	super();
	speed = 20;
	cost = 200;
	health = 70;
	thePortal = new Portal();
	placedPortals = 0;
    }
    
    /**
     * Places a portal on a given position
     */
    public void placePortal() {
	placedPortals++;
    }
    
    /**
     * Gets the teleporters portal
     * @return thePortal
     */
    public Portal getPortal() {
	return thePortal;
    }
    
    /**
     * Gets the number of placed portals
     * @return placedPortals, nr of placed portals
     */
    public int getPlacedPortals() {
	return placedPortals;
    }

}
