package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Soldier unit is a unit that runs fast and have good health
 * @author Peter Bjuhr
 */
public class SoldierUnit extends Unit{
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public SoldierUnit(Terrain[] walkable, int timeStep, int fps){
	super(walkable);
	double speed = 1.6;
	updateInterval = Math.round(fps/speed);
	cost = 100;
	health = 200;
    }
    
}
