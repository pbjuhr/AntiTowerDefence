package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Portal extends Terrain implements LandOn {

    public Portal(Position position, boolean buildable, boolean walkable) {
	super(position, buildable, walkable);
    }

}
