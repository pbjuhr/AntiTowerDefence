package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * Terrain element that check if unit has land on.
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Goal extends Terrain implements LandOnInterface {

    /**
     * Constructor for grass initialize buildable = true walkable = false
     * 
     * @param position
     *            where the terrain is place
     */
    public Goal(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    /**
     * When unit call this method. Unit can check if unit is on goal
     */
    @Override
    public void landOn(Unit u) {
	u.setReachedGoal(true);
    }
}
