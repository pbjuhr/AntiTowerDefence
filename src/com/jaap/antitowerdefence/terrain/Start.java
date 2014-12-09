package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Start extends Switch {

    public Start(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }
    
    /**
    * Starts setDirection does not change the switch direction
    * @author Peter Bjuhr 
    */
    public void setDirection(String newDirection) {
	// Do nothing on purpose!
    }

}
