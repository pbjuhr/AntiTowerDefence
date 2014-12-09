/**
 * @author Peter Bjuhr
 * Class represents a farmer unit
 */
package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Terrain;

public class FarmerUnit extends Unit {
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public FarmerUnit(Terrain[] walkable, int timeStep) {
	super(walkable);
	int speed = 1;
	updateInterval = timeStep / speed;
	cost = 50;
	health = 70;
	start();
    }
}
