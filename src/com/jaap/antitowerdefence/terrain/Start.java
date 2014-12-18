package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 * @author Peter Bjuhr id10pbn
 *
 */
public class Start extends Switch implements LandOnInterface{
    
    public Start(Position position, ArrayList<Direction> possibleDirections) {
	super(position, possibleDirections);
	walkable = true;
	buildable = false;
    }

    public void setSwitch(ArrayList<Direction> possibleDirections) {
	direction = possibleDirections.get(0);
	this.possibleDirections = possibleDirections;
    }

    /**
     * Calls the switchs landOn method
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
