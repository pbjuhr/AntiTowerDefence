package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Switch extends Terrain implements LandOn{

    public Switch(Position position, boolean buildable, boolean walkable) {
	super(position, buildable, walkable);
    }

}
