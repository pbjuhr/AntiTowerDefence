package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 * @author Peter Bjuhr id10pbn
 *
 */
public class Portal extends Terrain implements LandOnInterface {

    private Direction direction;
    private Portal receiver;
    private boolean hasReceiver;
    
    /**
     * Creates an instance of a Portal
     * @param position - the position of the portal
     */
    public Portal(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
	this.hasReceiver = false;
    }
    
    /**
     * What happens when unit lands on a Portal
     * @author Peter Bjuhr (id10pbn)
     * @param Unit u, the Unit that have stepped on the Portal
     */
    @Override
    public void landOn(Unit u) {
	if(hasReceiver) {
	    u.setPosition(getReciever().getPosition());
	    u.setDirection(getReciever().getDirection());
	}
    }
    
    /**
     * Sets the portals receiver
     * @author Peter Bjuhr (id10pbn)
     * @param p, the receiver position
     */
    public void setReciever(Portal p) {
	this.receiver = p;
	this.hasReceiver = true;
    }
    
    /**
     * Gets the portals receiver
     * @author Peter Bjuhr (id10pbn)
     * @return Portal, the portal object
     */
    public Portal getReciever() {
	return this.receiver;
    }
    
    /**
     * Does the portal have a receiver
     * @author Peter Bjuhr (id10pbn)
     * @return true if portal have receiver, otherwise false
     */
    public boolean hasReciever() {
	return hasReceiver;
    }
    
    /**
     * Sets the direction of receiving portal
     * @return void
     */
    public void setDirection(Direction dir) {
	this.direction = dir;
    }
    
    /**
     * Returns the direction of the receiving portal
     * @return direction - the direction of the receiving portal
     */
    public Direction getDirection() {
	return this.direction;
    }

}
