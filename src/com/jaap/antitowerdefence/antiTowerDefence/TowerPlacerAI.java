package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * 
 * @author Peter Bjuhr & id10abk
 *
 */
public class TowerPlacerAI {

    private int nrOfTowers;
    private int updateInterval;
    private int stepsPerSecond;
    private Terrain[] possiblePositions;

    /**
     * This class get a map of buildable terrain and how many tower are allowed
     * to build. Setup a updateInterval.
     * 
     * @param possiblePositions - A array of all buildable terrain.
     * @param nrOfTowers - How many tower the AI can place.
     * @param fps - How often does the game update
     */
    public TowerPlacerAI(Terrain[] possiblePositions, int nrOfTowers, int stepsPerSecond) {
	updateInterval = 20 * stepsPerSecond; // Should update every 20 sec
	this.stepsPerSecond = stepsPerSecond;
	this.possiblePositions = possiblePositions;
	this.nrOfTowers = nrOfTowers;
    }

    /**
     * Creates a new tower array with positions within possible tower positions
     * @return newTowers, an array of towers
     */
    public CopyOnWriteArrayList<Tower> getNewTowers() {
	CopyOnWriteArrayList<Tower> towers = new CopyOnWriteArrayList<Tower>();

	Random rand = new Random();

	for (int i = 0; i < nrOfTowers; i++) {
	    int index = rand.nextInt((possiblePositions.length - 1));

	    if (towers.isEmpty()) {
		towers.add(new Tower(possiblePositions[index].getPosition(), stepsPerSecond));

	    } else if (!towers.isEmpty()) {
		while (containsPosition(towers,
			possiblePositions[index].getPosition())) {
		    index = rand.nextInt((possiblePositions.length - 1));
		}
		towers.add(new Tower(possiblePositions[index].getPosition(), stepsPerSecond));
	    }
	}
	return towers;
    }

    /**
     * Check if a Tower array contains a tower with a certain position
     * 
     * @param towers - The tower array.
     * @param position - The position.
     * @return true if the array doesn't contain the position, otherwise false.
     */
    private boolean containsPosition(CopyOnWriteArrayList<Tower> towers, 
	    Position p) {

	for (int i = 0; i < towers.size(); i++) {
	    if (p.equals(towers.get(i).getPosition())) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Determines if it is time to change the towers
     * 
     * @param timeStep - what timestep we are on
     * @return true if it's time to change, otherwise false
     */
    public boolean timeToChange(int timeStep) {
	return (timeStep % updateInterval == 0);
    }

    /**
     * 
     * @param Towers - ArreyList of towers place in on map
     * @return Towers array
     */
    private Tower[] writeToArray(ArrayList<Tower> towers) {
	
	Tower[] newTowers = new Tower[towers.size()];
	for (int i = 0; i < towers.size(); i++) {
	    newTowers[i] = towers.get(i);
	    System.out.println("x: " + newTowers[i].getPosition().getX() + ", y: " + newTowers[i].getPosition().getY()); 
	}
	return newTowers;
    }
}
