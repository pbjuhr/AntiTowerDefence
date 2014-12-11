package com.jaap.antitowerdefence.antiTowerDefence;
import java.util.ArrayList;

import com.jaap.antitowerdefence.unit.Unit;


public class Tower{

    private Position position;
    private Position shootPosition;
    private int range;
    private int coolDown;
    private int sps;
    ArrayList<Unit> units;

    public Tower(Position position, int sps) {
	this.range = 3;
	this.coolDown = 5 * sps;
	this.position = position;
	this.sps = sps;
	shootPosition = null;
    }
    
    /**
     * Sets the units that 
     * @param units
     */
    public void setUnits(ArrayList<Unit> units){
	this.units = units;
    }
    
    /**
     * Shoots a unit if the canon is loaded (cooldown = 0) and if there's any
     * Units in range. 
     */
    public void action() {
	if(coolDown > 0) {
	    coolDown--;
	} else {
	    Unit u = findUnitInRange();
	    if(u != null) {
		shootPosition = u.getPosition();
		coolDown = sps * 5;
		u.takeDamage(); //FIRE!!
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
