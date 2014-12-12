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
public class Start extends Terrain implements LandOnInterface{

   
    protected Direction direction;
    protected ArrayList<Direction> newdir;
    
    public Start(Position position) {
	super(position);
	
	walkable = true;
	buildable = false;
	
    }


    public void setSwitch(ArrayList<Direction> dir) {
	direction = dir.get(0);
	newdir = dir;
    }

    /**
     * Calls the switchs landOn method
     * 
     * @author Peter Bjuhr
     */
    public void landOn(Unit u) {
	u.setDirection(this.direction);
    }

    
    public Direction findNewDirection(ArrayList<Direction> dir) {

	for (int i = 0; i < dir.size(); i++) {
	    dir.get(i).compareTo(direction);
	}

	return direction;

    }

    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

    public Direction getDirection() {
	return direction;
    }
    
}
