package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.Random;

public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    public static Direction getRandomDirection() {
	Random random = new Random();
	return values()[random.nextInt(values().length)];
    }
}
