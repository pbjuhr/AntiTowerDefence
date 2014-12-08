package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Start extends Terrain {

    public Start(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    // TODO Need a interface method definer her to check if unit is on me.

}
