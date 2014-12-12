package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Soldier unit is a unit that runs fast and have good health
 * @author Peter Bjuhr
 */
public class SoldierUnit extends Unit{
    
    static double speed = 3;
    static int cost = 100;
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public SoldierUnit(Terrain[] walkable, int stepsPerSec){
	super(walkable, stepsPerSec);
	health = 200;
	resetCoolDown();
    }
    
}
