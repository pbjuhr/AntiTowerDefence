package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOnInterface {

    Portal endPortal;
    // Position position
    private Position reciever;
    // boolean hasTwin
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

    // setTwinPosition(Position pos):void
    // setHasTwin(boolean hasTwin):void
//<<<<<<< Updated upstream
    public void setReciever(Position p) {
	this.reciever = p;
	this.hasReciever = true;
//=======
//    public void setPosEndPortal(Position posEndPortal) {
//	endPortal = new Portal(posEndPortal);
//	this.posEndPortal = posEndPortal;
//	hasEndPortal = true;
//>>>>>>> Stashed changes
    }

    // getTwinPosition():Position
    public Position getReciever() {
	return this.reciever;
    }


    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */
    // Test of random
    public static void main(String[] args) {
	
    }

}
