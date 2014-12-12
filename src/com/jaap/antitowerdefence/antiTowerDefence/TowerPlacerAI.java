package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.ArrayList;
import java.util.Random;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * 
 * @author Peter Bjuhr & id10abk
 *
 */
public class TowerPlacerAI {

    private int nrOfTowers;
    private int updateInterval;
    private Terrain[] possiblePositions;

    /**
     * This class get a map of buildable terrain and how many tower are allowed
     * to build. Setup a updateInterval.
     * 
     * @param possiblePositions
     *            A array of all buildable terrain.
     * @param nrOfTowers
     *            How many tower the AI can place.
     * @param fps
     *            How often does the game update
     */
    public TowerPlacerAI(Terrain[] possiblePositions, int nrOfTowers, int fps) {
	updateInterval = 20 * fps; // Should update every 20 sec
	this.possiblePositions = possiblePositions;
	this.nrOfTowers = nrOfTowers;
//	System.out.println(nrOfTowers);
    }

    /**
     * Creates a new tower array with positions within possible tower positions
     * 
     * @return newTowers, an array of towers
     */
    public Tower[] getNewTowers() {
	ArrayList<Tower> towers = new ArrayList<Tower>();

	Random rand = new Random();

	for (int i = 0; i < nrOfTowers; i++) {
	    int index = rand.nextInt(possiblePositions.length);

	    if (towers.isEmpty()) {
		towers.add(new Tower(possiblePositions[index].getPosition(),updateInterval ));

	    } else if (!towers.isEmpty()) {
		while (containsPosition(towers,
			possiblePositions[index].getPosition())) {
		    index = rand.nextInt(possiblePositions.length);
		}
		towers.add(new Tower(possiblePositions[index].getPosition(),updateInterval));
	    }
	}
	return writeToArray(towers);
    }

    /**
     * Check if a Tower array contains a tower with a certain position
     * 
     * @param towers
     *            , The tower array.
     * @param position
     *            , The position.
     * @return true if the array doesn't contain the position, otherwise false.
     */
    private boolean containsPosition(ArrayList<Tower> towers, Position p) {

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
     * @param timeStep
     *            , what timestep we are on
     * @return true if it's time to change, otherwise false
     */
    public boolean timeToChange(int timeStep) {
	return (timeStep % updateInterval == 0);
    }

    /**
     * 
     * @param Towers
     *            ArreyList of towers place in on map
     * @return Towers array
     */
    private Tower[] writeToArray(ArrayList<Tower> towers) {
	Tower[] newTowers = new Tower[towers.size() - 1];
	for (int i = 0; i < towers.size(); i++) {
	    newTowers[i] = towers.remove(i);
	}
	return newTowers;
    }
}
