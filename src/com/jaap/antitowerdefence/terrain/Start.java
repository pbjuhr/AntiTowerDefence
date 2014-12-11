package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Start extends Switch {

    public Start(Position position,ArrayList<Direction> dir) {
	super(position, dir);
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
    
    /**
     * Calls the switchs landOn method
     * @author Peter Bjuhr 
     */
    public void landOn(Unit u) {
	super.landOn(u);
    }

}
