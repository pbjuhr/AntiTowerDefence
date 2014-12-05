package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Road extends Terrain {

    private boolean walkable;

    public Road(Position position, boolean walkable) {
	super(position);
	this.walkable = walkable;
    }

    public boolean isWalkable() {
	return walkable;
    }
}
