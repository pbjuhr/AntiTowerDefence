package com.jaap.antitowerdefence.level;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;
/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class Level {

    private Terrain[] walkable;
    private Terrain[] buildable;
    private Tower[] towers;
    private ArrayList<Unit> units;
    private LevelStats levelStats;
    
    public Level(Terrain[] walkable, Terrain[] buildable, 
	    				LevelStats levelStats) {
	// 1. S�tter map och stats
	// 2. Initierar towers och units
	this.walkable = walkable;
	this.buildable = buildable;
	this.levelStats = levelStats;
	units = new ArrayList<Unit>();
    }
    
    public void addUnit(Unit unit) {
	
    }
    
    /*public void setTowers(Tower[] towers) {
	
    }*/
    
    //public void updateMap() {
	/* Efter units och towers har gjort sina �actions�, s� g�r den i genom 
	 * towers och units och kollar om torn eller gubbar tillkommit.
	 */
    //}
    
    //public void alterTerrain(Terrain t, Position position) {
	// L�gger till exempelvis en Portal p� en v�g
    //}
    
    /*public Unit[] getUnits() {
	return units;
    }*/
    
    //ON�DIG FUNKTION??
    public Tower[] getTowers() {
	return towers;
    }
}