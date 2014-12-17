package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.Random;

/**
 * Can use for setting direction.
 * 
 * @author id10abk Andreas Bolzyk
 *
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Randomize one of four enum.
     * 
     * @author id10abk Andreas Bolzyk
     * @return Direction direction
     */
    public static Direction getRandomDirection() {
	Random random = new Random();
	return values()[random.nextInt(values().length)];
    }

    /**
     * Change the direction 90 degrees clockwise
     * 
     * @author Peter Bjuhr
     */
    public Direction rotateClockWise() {
	return values()[(ordinal() + 1) % 4];
    }
}
