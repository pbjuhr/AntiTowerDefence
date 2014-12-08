package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 * 
 * 
 */
public class Grass extends Terrain {

    private boolean buildable;

    public Grass(Position position) {
	super(position);
	buildable = true;
	walkable = false;
    }
}