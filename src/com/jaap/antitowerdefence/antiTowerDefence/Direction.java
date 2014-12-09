package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.Random;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction getRandomDirection() {
	Random random = new Random();
	return values()[random.nextInt(values().length)];
    }
    
    /**
     * Change the direction 90 degrees clockwise
     * @author Peter Bjuhr
     */
    public Direction rotateClockWise() {
        return values()[(ordinal() + 1) % 4];
    }
}
