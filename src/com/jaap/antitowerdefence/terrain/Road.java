package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * Road.java
 * Class represents a road terrain
 * @author Andreas Bolzyk id10abk
 */
public class Road extends Terrain {
    
    /**
     * Creates an instance of a Road
     * @param position - the position of the road
     */
    public Road(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = false;
    }

}
