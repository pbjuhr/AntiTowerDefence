package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Road;

/**
 * Tests the functionality of the class Road.java
 * 
 * @author id10abk
 *
 */
public class RoadTest {

    private static Road r;

    /**
     * Initialize class Road
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	r = new Road(new Position(1, 1));
    }

    /**
     * Check if buildable is false
     */
    @Test
    public void testBuildable() {
	assertFalse(r.isBuildable());
    }

    /**
     * Check if walkable is true
     */
    @Test
    public void testWalkable() {
	assertTrue(r.isWalkable());
    }

    /**
     * Check if the initialize position is the same as expect
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(r.getPosition()));
    }

}
