package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Farmer unit are weak and slow, but costs little money
 * @author Peter Bjuhr
 */
public class FarmerUnit extends Unit {
    
    protected static double speed = 1;
    protected static int cost = 50;
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public FarmerUnit(Terrain[] walkable, int stepsPerSec) {
	super(walkable, stepsPerSec);
	speed = 1;
	cost = 50;
	health = 70;
	resetCoolDown();
    }
}
