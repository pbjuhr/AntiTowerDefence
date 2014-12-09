/**
 * @author Peter Bjuhr
 * Class represents a soldier unit
 */

package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Terrain;

public class SoldierUnit extends Unit{
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public SoldierUnit(Terrain[] walkable, int timeStep){
	super(walkable);
	double speed = 1.6;
	updateInterval = Math.round(timeStep / speed);
	cost = 100;
	health = 200;
	start();
    }
    
}
