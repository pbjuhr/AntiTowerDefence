package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOn {

    private boolean walkable;

    public Portal(Position position, boolean walkable) {
	super(position);
	this.walkable = walkable;
    }

    public boolean isWalkable() {
	return walkable;
    }

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */

}
