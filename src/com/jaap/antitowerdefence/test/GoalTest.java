package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Goal;

/**
 * Tests the functionality of the class Goal.java
 * 
 * @author id10abk
 *
 */
public class GoalTest {

    private static Goal g;

    /**
     * Initialize class Goal 
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	g = new Goal(new Position(1, 1));
    }

    /**
     * Check if buildable is false
     */
    @Test
    public void testBuildable() {
	assertFalse(g.isBuildable());
    }

    /**
     * Check if walkable is true
     */
    @Test
    public void testWalkable() {
	assertTrue(g.isWalkable());
    }

    /**
     * Check if the initialize position is the same as expect 
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(g.getPosition()));
    }

}
