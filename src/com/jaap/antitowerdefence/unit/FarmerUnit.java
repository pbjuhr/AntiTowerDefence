package com.jaap.antitowerdefence.unit;

import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Farmer unit are weak and slow, but cheap
 * @author Peter Bjuhr
 */
public class FarmerUnit extends Unit {
    
    public static int cost = 50; // How much the unit costs
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public FarmerUnit(CopyOnWriteArrayList<Terrain> walkable, int stepsPerSec) {
	super(walkable, stepsPerSec);
	health = 40;
	speed = 2;
	resetCoolDown();
    }
    
    /**
     * Gets the cost of the unit
     * @return cost, the cost of the Unit
     */
    public int getCost() {
	return cost;
    }
   
}
