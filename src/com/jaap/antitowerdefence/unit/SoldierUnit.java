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
    public SoldierUnit(Terrain[] walkable){
	super(walkable);
	speed = 20;
	cost = 200;
	health = 70;
	start();
    }
    
}
