package com.jaap.antitowerdefence.antiTowerDefence;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.unit.Unit;

/**
 * Tower that can shoot units within a shooting distance
 * @author Peter Bjuhr
 */
public class Tower{

    private Position position;		// position of the tower
    private Unit unitShot;		// last unit shot
    private int range = 2; 		// how far away the tower can see
    private int shootInterval = 2;	// time between ever shot (seconds)
    private int coolDown;		// how many steps until we can shoot
    private int stepsPerSecond;		// steps per second in the game
    CopyOnWriteArrayList<Unit> units;	// the units to shoot at

    /**
     * Creates a Tower object
     * @param position, The position of the tower
     * @param stepsPerSecond, steps per second in the game
     */
    public Tower(Position position, int stepsPerSecond) {
	this.position = new Position(position.getX(), position.getY());
	this.stepsPerSecond = stepsPerSecond;
	unitShot = null;
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
	    if(coolDown < 
		    ((stepsPerSecond * shootInterval) - 
			    ((1.0/4) * stepsPerSecond))) {
		unitShot = null;
	    }
	    coolDown--;
	} else {
	    Unit u = findUnitInRange();
	    if(u != null) {
		//FIRE!!
		unitShot = u;
		u.takeDamage(); 
		resetCoolDown();
	    }
	}
    }

    /**
     * Finds a random unit in range of the Tower
     * @return unit, the Unit object that was found, or null
     */
    private Unit findUnitInRange(){
	ArrayList<Unit> unitsInRange = new ArrayList<Unit>();
	for(Unit u : units) {
	    if(u.getPosition().distanceTo(this.position) <= range) {
		unitsInRange.add(u);
	    }
	}
	if (!unitsInRange.isEmpty()) {
	    return unitsInRange.get(new Random().nextInt(unitsInRange.size()));
	} else {
	    return null;
	}
    }
    
    /**
     * Gets the unit hit by the latest shot
     * @return Unit, the unit, or null
     */
    public Unit getUnitShot() {
	return unitShot;
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
