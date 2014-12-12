package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOnInterface {

    private Direction direction;
    private Portal reciever;
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
	    u.setPosition(getReciever().getPosition());
	    u.setDirection(getReciever().getDirection());
	    u.toggleTeleported();
	}
    }
    
    /**
     * Sets the portals reciever
     * @author Peter Bjuhr
     * @param p, the reciever position
     */
    public void setReciever(Portal p) {
	this.reciever = p;
	this.hasReciever = true;
    }
    
    /**
     * Gets the portals reciever
     * @author Peter Bjuhr
     * @return Portal, the portal object
     */
    public Portal getReciever() {
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
    
    /**
     * Sets the direction of recieving portal
     * @return void
     */
    public void setDirection(Direction dir) {
	this.direction = dir;
    }
    
    /**
     * Returns the direction of the recieving portal
     */
    public Direction getDirection() {
	return this.direction;
    }

}
