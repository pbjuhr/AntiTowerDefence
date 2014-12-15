package com.jaap.antitowerdefence.unit;

import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Farmer unit are weak and slow, but costs little money
 * @author Peter Bjuhr
 */
public class FarmerUnit extends Unit {
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public FarmerUnit(CopyOnWriteArrayList<Terrain> walkable, int stepsPerSec) {
	super(walkable, stepsPerSec);
	health = 70;
	speed = 1;
	resetCoolDown();
	cost = 50;
    }
}
