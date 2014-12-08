package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Goal extends Terrain implements LandOn {
    private boolean walkable;

    public Goal(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */
}
