package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Andreas Bolzyk id10abk
 */
public class Switch extends Terrain implements LandOnInterface {
    
    Direction direction;
   
    public Switch(Position position) {
	super(position);
	walkable = true;
	buildable = false;
	direction = Direction.getRandomDirection();
    }

    @Override
    public void landOn(Unit u) {
	u.setDirection(this.direction);
    }
    
    /**
     * Sets the switch direction
     * @author Peter Bjuhr
     * @param newDirection, the new direction
     */
    public void setDirection(Direction newDirection) {
	direction = newDirection;
    }

}
