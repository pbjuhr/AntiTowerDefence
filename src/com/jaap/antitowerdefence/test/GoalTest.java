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

    @BeforeClass
    public static void setUpBeforeClass() {
	g = new Goal(new Position(1, 1));
    }

    @Test
    public void testBuildable() {
	assertFalse(g.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertTrue(g.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(g.getPosition()));
    }

}
