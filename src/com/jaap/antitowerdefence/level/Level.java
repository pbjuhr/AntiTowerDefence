package com.jaap.antitowerdefence.level;

import antiTowerDefence.Position;
import antiTowerDefence.Tower;
import terrain.Terrain;
import unit.Unit;

public class Level {

    private Terrain[][] map;
    private Tower[] towers;
    private Unit[] units;
    private LevelStats levelStats;
    private Position startPosition;
    
    public Level(Terrain[][] map, LevelStats levelStats) {
	// 1. Sätter map och stats
	// 2. Initierar towers och units
    }
    
    public void addUnit(Unit unit) {
	
    }
    
    public void setTowers(Tower[] towers) {
	
    }
    
    public void updateMap() {
	/* Efter units och towers har gjort sina “actions”, så går den i genom 
	 * towers och units och kollar om torn eller gubbar tillkommit.
	 */
    }
    
    public void alterTerrain(Terrain t, Position position) {
	// Lägger till exempelvis en Portal på en väg
    }
    
    public Unit[] getUnits() {
	return units;
    }
    
    //ONÖDIG FUNKTION??
    public Tower[] getTowers() {
	return towers;
    }
}