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
    public FarmerUnit(Terrain[] walkable) {
	super(walkable);
	speed = 20;
	cost = 200;
	health = 70;
	start();
    }
}
