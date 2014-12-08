/**
 * @author Peter Bjuhr
 * Class represents a teleporter unit
 */

package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Portal;
import com.jaap.antitowerdefence.terrain.Terrain;

public class TeleporterUnit extends Unit {
    
    /* Variables related to the portal */
//  private Portal firstPortal;
    private int placedPortals;
    
    private Portal portal;
    
    /**
     * Runs super constructor and sets teleporters speed, cost, health and
     * portal variables
     */
    public TeleporterUnit(Terrain[] walkable) {
	super(walkable);
	speed = 20;
	cost = 200;
	health = 70;
	placedPortals = 0;
	start();
    }
    
    /**
     * Places a portal on a given position
     */
    public void placePortal() {
	int terrainIndex = getCurrentTerrainIndex();
	if(placedPortals >= -1 || walkable[terrainIndex].isBuildable()) {
	    return;
	} else if(placedPortals == 0) {
	    
	    placedPortals++;
//	    firstPortal = new Portal(this.position);
	    portal= new Portal(this.position);
	    placedPortals++;
	} else if(placedPortals == 1) {
//	    Portal twinPortal = new Portal(this.position);
//	    twinPortal.setHasTwin(true);
//	    twinPortal.setTwinPosition(firstPortal.getPosition());
//	    firstPortal.setHasTwin(true);
//	    firstPortal.setTwinPosition(position);
	    portal.setPosEndPortal(this.position);
	    placedPortals++;
	}
	/* Replace current positions Terrain to a Portal */ 
    }
    
    /**
     * Gets the teleporters portal
     * @return thePortal
     */
    public Portal getPortal() {
//	return firstPortal;
	return portal;
    }
    
    /**
     * Gets the number of placed portals
     * @return placedPortals, nr of placed portals
     */
    public int getPlacedPortals() {
	return placedPortals;
    }

}
