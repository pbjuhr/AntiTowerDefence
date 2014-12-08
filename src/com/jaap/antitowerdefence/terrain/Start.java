package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Start extends Terrain {

    private boolean walkable;
    
    public Start(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
    }

    //TODO Need a interface method definer her to check if unit is on me.
    
}
