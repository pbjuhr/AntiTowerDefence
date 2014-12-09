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

    @Override
    public void landOn(Unit u) {
	if(hasReciever) {
	    u.setPosition(getReciever());
	    u.toggleTeleported();
	}
    }

    public void setReciever(Position p) {
	this.reciever = p;
	this.hasReciever = true;
    }

    public Position getReciever() {
	return this.reciever;
    }
    
    public boolean hasReciever() {
	return hasReciever;
    }

}
