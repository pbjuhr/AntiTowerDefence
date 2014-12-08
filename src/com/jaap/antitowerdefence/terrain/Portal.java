package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOnInterface {

    // Position position
    private Position posEndPortal;
    // boolean hasTwin
    private boolean hasEndPortal;

    public Portal(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
	hasEndPortal = false;
    }

    @Override
    public void landOn(Unit u) {

	if(hasEndPortal){
	    u.setEndPortalPosition(getPosEndPortal());
	}
	// TODO Auto-generated method stub

    }

    // setTwinPosition(Position pos):void
    // setHasTwin(boolean hasTwin):void
    public void setPosEndPortal(Position posEndPortal) {
	this.posEndPortal = posEndPortal;
	hasEndPortal = true;
    }

    // getTwinPosition():Position
    public Position getPosEndPortal() {
	return posEndPortal;
    }

    // hasTwin():boolean
    public boolean hasEndPortal() {
	return hasEndPortal;

    }

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */

}
