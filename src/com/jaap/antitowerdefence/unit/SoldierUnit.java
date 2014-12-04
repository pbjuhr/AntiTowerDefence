/**
 * @author Peter Bjuhr
 * Class represents a soldier unit
 */

package com.jaap.antitowerdefence.unit;

public class SoldierUnit extends Unit{
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public SoldierUnit(){
	super();
	speed = 20;
	cost = 200;
	health = 70;
    }
    
}
