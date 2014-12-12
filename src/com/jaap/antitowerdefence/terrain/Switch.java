package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Andreas Bolzyk id10abk
 */
public class Switch extends Terrain implements LandOnInterface {

    protected Direction direction;
    protected ArrayList<Direction> possibleDirections;

    public Switch(Position position, ArrayList<Direction> possibleDirections) {
	super(position);
	walkable = true;
	buildable = false;
	if(possibleDirections != null) {
	    direction = possibleDirections.get(0);
	}
	this.possibleDirections = possibleDirections;
	// findNewDirection(dir);
	// Direction.getRandomDirection();
    }

    @Override
    public void landOn(Unit u) {
	u.setDirection(this.direction);
    }

    public Direction findNewDirection(ArrayList<Direction> dir) {

	for (int i = 0; i < dir.size(); i++) {
	    dir.get(i).compareTo(direction);
	}

	return direction;

    }
    
    /**
     * Changes the current direction of the switch
     */
    public void changeDirection() {
	for (int i = 0; i < possibleDirections.size(); i++) {
	    if(direction == possibleDirections.get(i)) {
		direction = possibleDirections.get((i+1) % possibleDirections.size());
		break;
	    }
	}
    }

    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

    public Direction getDirection() {
	return direction;
    }
    
    public String getDirectionToString() {
	return direction.toString();
    }

}
