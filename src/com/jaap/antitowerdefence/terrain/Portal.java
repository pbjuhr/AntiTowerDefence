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
    private Portal receiver;
    private boolean hasReceiver;

    public Portal(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
	this.hasReceiver = false;
    }
    
    /**
     * What happens when unit lands on a Portal
     * @author Peter Bjuhr
     * @param Unit u, the Unit that have stepped on the Portal
     */
    @Override
    public void landOn(Unit u) {
	if(hasReceiver) {
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
	this.receiver = p;
	this.hasReceiver = true;
    }
    
    /**
     * Gets the portals reciever
     * @author Peter Bjuhr
     * @return Portal, the portal object
     */
    public Portal getReciever() {
	return this.receiver;
    }
    
    /**
     * Does the portal have a reciever
     * @author Peter Bjuhr
     * @return true if portal have reciever, otherwise false
     */
    public boolean hasReciever() {
	return hasReceiver;
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
