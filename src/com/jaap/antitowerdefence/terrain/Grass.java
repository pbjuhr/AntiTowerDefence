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

    public Grass(Position position, boolean buildable) {
	super(position);
	this.buildable = buildable;
    }

    public boolean isBuildable() {
	return buildable;
    }
}