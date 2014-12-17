package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * Terrain element that allow to build tower on.
 * 
 * @author Andreas Bolzyk id10abk
 * 
 * 
 */
public class Grass extends Terrain {

    public Grass(Position position) {
	super(position);
	buildable = true;
	walkable = false;
    }

}