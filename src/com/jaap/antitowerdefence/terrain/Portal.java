package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOn {

    public Portal(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
    }

    public boolean isWalkable() {
	return walkable;
    }

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */

}
