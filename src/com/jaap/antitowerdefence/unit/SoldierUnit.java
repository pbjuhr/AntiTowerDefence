package com.jaap.antitowerdefence.unit;

import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * A Soldier unit is a unit that runs fast and have good health
 * @author Peter Bjuhr
 */
public class SoldierUnit extends Unit{
    
    public static int cost = 100; // How much the unit costs
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public SoldierUnit(CopyOnWriteArrayList<Terrain> walkable, int stepsPerSec){
	super(walkable, stepsPerSec);
	health = 80;
	speed = 1.2;
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
