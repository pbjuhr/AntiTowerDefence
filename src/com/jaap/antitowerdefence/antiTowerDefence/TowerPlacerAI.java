package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.Random;

import com.jaap.antitowerdefence.terrain.Terrain;

public class TowerPlacerAI {
    
    private int timeStep;
    private int nrOfTowers;
    private int updateInterval;
    private Terrain[] possiblePositions;
    
    public TowerPlacerAI(Terrain[] possiblePositions, int nrOfTowers, int fps) {
	updateInterval = 20 * fps; //Should update every 20 sec
	this.timeStep = timeStep * updateInterval;
	this.possiblePositions = possiblePositions;
    }
    
    /**
     * Creats a new tower array with positions within possible tower positions
     * @return newTowers, an array of towers
     */
    public Tower[] getNewTowers() {
	Tower[] newTowers = new Tower[nrOfTowers];
	Random rnd = new Random();
	for(int i = 0; i < nrOfTowers; i++) {
	    int index = rnd.nextInt(possiblePositions.length);
	    
	    newTowers[i] = new Tower();
	}
	return newTowers;
    }
    
    /**
     * Check if a Tower array contains a tower with a certain position
     * @param towers, the tower array
     * @param position, thw position
     * @return true if the array doesnt contain the position, otherwise false
     */
    public boolean containsPosition(Tower[] towers) {
	for(Tower t : towers) {
	    if(t.getPosition().equals(p)) {
		return false;
	    }
	}
	return true;
    }
    
    /**
     * determines if it is time to change the towers
     * @param timeStep, what timestep we are on
     * @return true if it's time to change, otherwise false
     */
    public boolean timeToChange(int timeStep) {
	return (timeStep % updateInterval == 0);
    }
}
