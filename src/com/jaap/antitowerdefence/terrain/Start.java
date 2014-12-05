package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Start extends Terrain {

    private boolean walkable;
    
    public Start(Position position, boolean walkable) {
	super(position);
	this.walkable = walkable;
    }

    public boolean isWalkable() {
	return walkable;
    }

    //TODO Need a interface method definer her to check if unit is on me.
    
}
