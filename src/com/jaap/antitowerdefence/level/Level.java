package com.jaap.antitowerdefence.level;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;

public class Level {

    private Terrain[][] map;
    private Tower[] towers;
    private Unit[] units;
    private LevelStats levelStats;
    private Position startPosition;
    
    public Level(Terrain[][] map, LevelStats levelStats) {
	// 1. S�tter map och stats
	// 2. Initierar towers och units
    }
    
    public void addUnit(Unit unit) {
	
    }
    
    public void setTowers(Tower[] towers) {
	
    }
    
    public void updateMap() {
	/* Efter units och towers har gjort sina �actions�, s� g�r den i genom 
	 * towers och units och kollar om torn eller gubbar tillkommit.
	 */
    }
    
    public void alterTerrain(Terrain t, Position position) {
	// L�gger till exempelvis en Portal p� en v�g
    }
    
    public Unit[] getUnits() {
	return units;
    }
    
    //ON�DIG FUNKTION??
    public Tower[] getTowers() {
	return towers;
    }
}