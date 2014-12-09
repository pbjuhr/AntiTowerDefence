package com.jaap.antitowerdefence.antiTowerDefence;
import java.util.ArrayList;

import com.jaap.antitowerdefence.unit.Unit;


public class Tower extends Thread{

    private Position position;
    private int range;
    private int coolDown;
    ArrayList<Unit> units;
	    
    public Tower() {
	units = null;
	this.range = 3;
	this.coolDown = 5;
	start();
    }
    
    /**
     * Sets the units that 
     * @param units
     */
    public void setUnits(ArrayList<Unit> units){
	this.units = units;
    }
    
    /**
     * Runs the Thread
     */
    public void run() {
	while(true) {
	    shoot(findUnitInRange());
	}
    }
    
    /**
     * Finds the first unit in range of the Tower
     * @return unit, the Unit object that was found, or null
     */
    public Unit findUnitInRange(){
	if(units == null){
	    return null;
	}
	for(Unit u : units) {
	    if(u.getPosition().distanceTo(this.position) <= range) {
		return u;
	    }
	}
	return null;
    }
    
    /**
     * Shoots a unit
     * @param u, the Unit to get shot
     */
    public void shoot(Unit u){
	u.takeDamage();
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
