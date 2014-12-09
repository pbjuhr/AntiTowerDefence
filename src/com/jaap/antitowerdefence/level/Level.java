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
     * 
     * @param walkable
     * @param buildable
     * @param levelStats
     */
    public Level(Terrain[] walkable, Terrain[] buildable, 
	    				LevelStats levelStats) {
	this.walkable = walkable;
	this.buildable = buildable;
	this.levelStats = levelStats;
	units = new ArrayList<Unit>();
    }
    
    /**
     * 
     * @return
     */
    public Terrain[] getWalkableTerrain() {
	return walkable;
    }
    
    /**
     * 
     * @return
     */
    public Terrain[] getPossibleTowerPositions() {
	return buildable;
    }
    
    /*Håller koll på units - lägger till en färdiskapad unit på order från game
     * addUnit(): lägger till en unit i unitarrayen och justerar credits*/
    /**
     * 
     * @param unit
     */
    public void addUnit(Unit unit) {
	units.add(unit);	
	levelStats.decreaseCredits(unit.getCost());
    }
    
    /*get(): skickar tillbaka alla units*/
    /**
     * 
     * @return
     */
    public ArrayList<Unit> getUnits() {
	return units;
    }
    
    /*Håller koll på torn
    	setTowers()
    		setUnits (Skicka in unitsarrayen)
    	tar in en tornarray
    	spara över den gamla tornarrayen*/
    /**
     * 
     * @param towers
     */
    public void setTowers(Tower[] towers) {
	this.towers = towers;
	for(int i = 0; i < towers.length; i++){
	    towers[i].setUnits(units);
	}
    }
    
    /*getTowers()*/
    /**
     * 
     * @return
     */
    public Tower[] getTowers() {
	return towers;
    }
    
    /*getLevelStats*/
    /**
     * 
     * @return
     */
    public LevelStats getLevelStats() {
	return levelStats;
    }
    
    /*
    Update
    	Död
    		Ta bort ur lista
    	Mål
    		Uppdatera score/credits*/
    /**
     * 
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
    
    /**
     * 
     * @param unit
     */
    private void unitReachedGoal(Unit unit) {
	double healthCredits = (0.5 * unit.getHealth());
	double costCredits = (0.8 * unit.getCost());
	int totalCredits = (int)Math.round((healthCredits + costCredits));
	levelStats.addCredits(totalCredits);
    }
}