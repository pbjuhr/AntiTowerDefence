package com.jaap.antitowerdefence.level;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;
/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class Level {

    /*Variables*/
    private Terrain[] walkable;		//Terrain elements units can walk on
    private Terrain[] buildable;	//Possible tower position
    private Tower[] towers;		//Towers implemented in level
    private ArrayList<Unit> units;	//Units currently active in game
    private LevelStats levelStats;	//Level info and game statistics
    
    /**
     * Contructor for Level saves the walkable terrain for the units, the 
     * terrain where towers can be built and the level info of the level. The 
     * unit arraylist is initiated.
     * 
     * @param walkable - The walkable terrain in the level
     * @param buildable - The terrain where towers can be built in the level
     * @param levelStats - Level info and game statistics
     */
    public Level(Terrain[] walkable, Terrain[] buildable, 
	    				LevelStats levelStats) {
	this.walkable = walkable;
	this.buildable = buildable;
	this.levelStats = levelStats;
	units = new ArrayList<Unit>();
    }
    
    /**
     * getWalkableTerrain provides the walkable terrain elements for the units
     * @return - Terrain array of Grass
     */
    public Terrain[] getWalkableTerrain() {
	return walkable;
    }
    
    /**
     * getPossibleTowerPositions provides the terrain elements where towers 
     * can be built
     * @return - array of possible tower build positions
     */
    public Terrain[] getPossibleTowerPositions() {
	return buildable;
    }
    
    /**
     * addUnit adds a unit to the ArrayList of units. The cost of the Unit is
     * decrease from the players credit 
     * @param unit - The unit to add to the ArrayList of active units
     */
    public void addUnit(Unit unit) {
	units.add(unit);	
	levelStats.decreaseCredits(unit.getCost());
    }
    
    /**
     * getUnits provides the ArrayList of active units
     * @return
     */
    public ArrayList<Unit> getUnits() {
	return units;
    }
    
    /**
     * setTowers replaces the tower array with a new array of towers and
     * gives the new towers access to the ArrayList of units
     * @param towers - the tower array to replace the old tower array
     */
    public void setTowers(Tower[] towers) {
	this.towers = towers;
	for(int i = 0; i < towers.length; i++){
	    towers[i].setUnits(units);
	}
    }
    
    /**
     * getTowers provides the towers in the level
     * @return - array of towers in level
     */
    public Tower[] getTowers() {
	return towers;
    }
    
    /**
     * getLevelStats provides the levelStats instance of the level
     * @return - the LevelStats object of the level
     */
    public LevelStats getLevelStats() {
	return levelStats;
    }
    
    /**
     * updateUnits checks if any of the units has reached goal or died. If a
     * units has died it is removed from the list of active units. If a unit
     * has reached goal a score is added to the players total score and the 
     * players credits is increased. After this the unit is removed from the 
     * list of active units.
     */
    public void updateUnits() {
	for(int i = 0; i < units.size(); i++) {
	    if(units.get(i).hasReachedGoal()){
		unitReachedGoal(units.get(i));
		units.remove(i);
	    }else if(units.get(i).isDead()) {
		units.remove(i);
	    } 
	}
    }
    //Kommentera vidare här!!
    /**
     * unitReachedGoal calculates the
     * @param unit
     */
    private void unitReachedGoal(Unit unit) {
	double healthCredits = (0.5 * unit.getHealth());
	double costCredits = (0.8 * unit.getCost());
	int totalCredits = (int)Math.round((healthCredits + costCredits));
	//TODO Score? How do we calculate this?
	levelStats.addCredits(totalCredits);
    }
}