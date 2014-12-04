/**
 * @author Peter Bjuhr
 * Class represents a farmer unit
 */
package com.jaap.antitowerdefence.unit;

public class FarmerUnit extends Unit {
    
    /**
     * Runs super constructor and sets speed, cost and health
     */
    public FarmerUnit() {
	super();
	speed = 20;
	cost = 200;
	health = 70;
    }
}
