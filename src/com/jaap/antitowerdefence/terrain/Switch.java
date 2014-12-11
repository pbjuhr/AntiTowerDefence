package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Andreas Bolzyk id10abk
 */
public class Switch extends Terrain implements LandOnInterface {

    private Direction direction;
    protected ArrayList<Direction> dir;

    public Switch(Position position,ArrayList<Direction> dir) {
	super(position);
	walkable = true;
	buildable = false;
	 direction = dir.get(0);
	 this.dir = dir;
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

    public void setSwitch(ArrayList<Direction> dir) {
	direction = dir.get(0);
	this.dir = dir;
    }

    /**
     * Sets the switch direction
     * 
     * @author Peter Bjuhr
     * @param newDirection
     *            , the new direction
     */
    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

    public Direction getdDirection() {
	return direction;
    }

}
