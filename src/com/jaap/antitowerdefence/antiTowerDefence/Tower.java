package com.jaap.antitowerdefence.antiTowerDefence;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.unit.Unit;

/**
 * Tower that can shoot units within a shooting distance
 * @author Peter Bjuhr
 */
public class Tower{

    private Position position;		// position of the tower
    private Position shootPosition;	// last shooting position
    private int range = 2; 		// how far away the tower can see
    private int shootInterval = 2;	// time between ever shot (seconds)
    private int coolDown;		// how many steps until we can shoot
    private int stepsPerSecond;		// steps per second in the game
    CopyOnWriteArrayList<Unit> units;		// the units to shoot at

    /**
     * Creates a Tower object
     * @param position, The position of the tower
     * @param stepsPerSecond, steps per second in the game
     */
    public Tower(Position position, int stepsPerSecond) {
	this.position = position;
	this.stepsPerSecond = stepsPerSecond;
	shootPosition = null;
	resetCoolDown();
    }
    
    private void resetCoolDown() {
	this.coolDown = shootInterval * stepsPerSecond;
    }
    
    /**
     * Sets the units that 
     * @param units
     */
    public void setUnits(CopyOnWriteArrayList<Unit> units){
	this.units = units;
    }
    
    /**
     * Shoots a unit if the canon is loaded (cooldown = 0) and if there's any
     * Units in range. 
     */
    public void action() {
	// Determine if it is time to shoot
	if(coolDown > 0) {
	    if(coolDown < (stepsPerSecond * shootInterval) - ((1/2) * stepsPerSecond)){
		shootPosition = null;
	    }
	    coolDown--;
	} else {
	    Unit u = findUnitInRange();
	    if(u != null) {
		//FIRE!!
		shootPosition = u.getPosition();
		u.takeDamage(); 
		resetCoolDown();
	    }
	}
    }

    /**
     * Finds the first unit in range of the Tower
     * @return unit, the Unit object that was found, or null
     */
    private Unit findUnitInRange(){
	if(units == null){
	    return null; //No unit within sight
	}
	for(Unit u : units) {
	    if(u.getPosition().distanceTo(this.position) <= range) {
		return u;
	    }
	}
	return null;
    }
    
    /**
     * Gets the position of the latest shot
     * @return Position the postion, or null
     */
    public Position getShootPosition() {
	return shootPosition;
    }
    
    /**
     * Gets the position of the tower
     * @return position, the Position object
     */
    public Position getPosition(){
	return position;
    }
    
    /**
     * Sets the tower position coordinates
     * @param p, the new position
     */
    public void setPosition(Position p) {
	this.position.setX(p.getX());
	this.position.setY(p.getY());
    }
}
