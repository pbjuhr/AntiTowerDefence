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

    /**
     * Constructor for grass initialize buildable = true walkable = false
     * 
     * @param position
     *            where the terrain is place
     */
    public Grass(Position position) {
	super(position);
	buildable = true;
	walkable = false;
    }

}