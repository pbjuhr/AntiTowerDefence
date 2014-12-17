package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * Create a define number of tower and place them on define position.
 * 
 * @author Peter Bjuhr (id10pbn)
 * @author Andreas Bolzyk id10abk
 * @author Joakim Sandman (tm08jsn)
 *
 */
public class TowerPlacerAI {

    private int nrOfTowers;
    private int updateTime;
    private long lastChangeTimeStep;
    private boolean first;
    private int stepsPerSecond;
    private Terrain[] possiblePositions;
    private Random rand;

    /**
     * This class get a map of buildable terrain and how many tower are allowed
     * to build. Setup an updateTime.
     * 
     * @param possiblePositions
     *            - A array of all buildable terrain.
     * @param nrOfTowers
     *            - How many tower the AI can place.
     * @param fps
     *            - How often does the game update
     */
    public TowerPlacerAI(Terrain[] possiblePositions, int nrOfTowers,
	    int stepsPerSecond) {
	// Should update every 10-20 sec
	updateTime = stepsPerSecond * (10 + new Random().nextInt(11));
	lastChangeTimeStep = 0;
	first = true;
	rand = new Random();
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

	int index;

	for (int i = 0; i < nrOfTowers; i++) {
	    index = rand.nextInt((possiblePositions.length - 1));

	    if(!towers.isEmpty()) {
		while (containsPosition(towers,
			possiblePositions[index].getPosition())) {
		    index = rand.nextInt((possiblePositions.length - 1));
		}
	    }
	    towers.add(new Tower(possiblePositions[index].getPosition(),
			stepsPerSecond));
	}
	return towers;
    }

    /**
     * Check if a Tower array contains a tower with a certain position
     * 
     * @param towers
     *            - The tower array.
     * @param position
     *            - The position.
     * @return true if the array doesn't contain the position, otherwise false.
     */
    private boolean containsPosition(CopyOnWriteArrayList<Tower> towers,
	    Position p) {

	for (Tower t: towers) {
	    if (p.equals(t.getPosition())) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Determines if it is time to change the towers
     * 
     * @param timeStep
     *            - what timestep we are on
     * @return true if it's time to change, otherwise false
     */
    public boolean timeToChange(long timeStep) {
	if (timeStep == 0) { // Prevents errors on loop-around of timeStep.
	    if (!first) {
		long timePassedSoFar = Long.MAX_VALUE - lastChangeTimeStep + 1;
		if (timePassedSoFar != Long.MIN_VALUE) {
		    updateTime -= (int) timePassedSoFar;
		    lastChangeTimeStep = - timePassedSoFar;
		}
	    } else {
		first = false;
	    }
	}
	if ((timeStep - lastChangeTimeStep) == updateTime) {
	    updateTime = stepsPerSecond * (10 + rand.nextInt(11));
	    lastChangeTimeStep = timeStep;
	    return true;
	} else {
	    return false;
	}
    }

}
