package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * A switch is a terrain object that tells units where to go in an intersection
 * @author Andreas Bolzyk id10abk
 * @author Peter Bjuhr id10pbn
 */
public class Switch extends Terrain implements LandOnInterface {

    protected Direction direction;
    protected ArrayList<Direction> possibleDirections;
    
    /**
     * Creates instance of a Swith
     * @param position - the position of the switch
     * @param possibleDirections - An array of possible switch directions
     */
    public Switch(Position position, ArrayList<Direction> possibleDirections) {
	super(position);
	walkable = true;
	buildable = false;
	if(possibleDirections != null) {
	    direction = possibleDirections.get(0);
	}
	this.possibleDirections = possibleDirections;
    }
    
    /**
     * Sets the direction of the unit
     * @author Peter Bjuhr id10pbn
     * @param u - the unit to change
     */
    public void landOn(Unit u) {
	u.setDirection(this.direction);
    }
    
    /**
     * Changes the current direction of the switch
     */
    public void changeDirection() {
	for (int i = 0; i < possibleDirections.size(); i++) {
	    if(direction == possibleDirections.get(i)) {
		direction = possibleDirections.get((i+1) % 
			possibleDirections.size());
		break;
	    }
	}
    }
    
    /**
     * Sets the direction of the Switch
     * @param newDirection
     */
    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }
    
    /**
     * Gets the direction of the Switch
     * @return direction - the direction of the Switch
     */
    public Direction getDirection() {
	return direction;
    }
    
}
