package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * Represent a start terrain
 * @author Andreas Bolzyk id10abk
 * @author Peter Bjuhr id10pbn
 *
 */
public class Start extends Switch implements LandOnInterface{
    
    /**
     * Creates an instance of a start class
     * @param position - the start position
     * @param possibleDirections - An array of possible switch directions
     */
    public Start(Position position, ArrayList<Direction> possibleDirections) {
	super(position, possibleDirections);
	walkable = true;
	buildable = false;
    }
    
    /**
     * Sets the possible directions arrayList
     * @param possibleDirection - an arraylist of directions
     */
    public void setSwitch(ArrayList<Direction> possibleDirections) {
	direction = possibleDirections.get(0);
	this.possibleDirections = possibleDirections;
    }

    /**
     * Calls the switchs landOn method
     * @author Peter Bjuhr
     * @param Unit u - the unit to manipulate
     */
    public void landOn(Unit u) {
	u.setDirection(this.direction);
    }
    
    /**
     * Sets the direction of the Start
     * @param newDirection - the new direction
     */
    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

    /**
     * Gets the direction of the Start
     * @return direction - the direction of the Start
     */
    public Direction getDirection() {
	return direction;
    }
    
}
