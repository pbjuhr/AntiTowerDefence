package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOnInterface {

    private Position reciever;
    private boolean hasReciever;

    public Portal(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
	this.hasReciever = false;
    }
    
    /**
     * What happens when unit lands on a Portal
     * @author Peter Bjuhr
     * @param Unit u, the Unit that have stepped on the Portal
     */
    @Override
    public void landOn(Unit u) {
	if(hasReciever) {
	    u.setPosition(getReciever());
	    u.toggleTeleported();
	}
    }
    
    /**
     * Sets the portals reciever
     * @author Peter Bjuhr
     * @param p, the reciever position
     */
    public void setReciever(Position p) {
	this.reciever = p;
	this.hasReciever = true;
    }
    
    /**
     * Gets the portals reciever
     * @author Peter Bjuhr
     * @return Position, the reciver position
     */
    public Position getReciever() {
	return this.reciever;
    }
    
    /**
     * Does the portal have a reciever
     * @author Peter Bjuhr
     * @return true if portal have reciever, otherwise false
     */
    public boolean hasReciever() {
	return hasReciever;
    }

}
