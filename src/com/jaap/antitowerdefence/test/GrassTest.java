package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Grass;

/**
 * Tests the functionality of the class Grass.java
 * 
 * @author id10abk
 *
 */
public class GrassTest {

    private static Grass g;

    /**
     * Initialize class Grass 
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	g = new Grass(new Position(1, 1));
    }

    /**
     * Check if buildable is true
     */
    @Test
    public void testBuildable() {
	assertTrue(g.isBuildable());
    }

    /**
     * Check if walkable is false
     */
    @Test
    public void testWalkable() {
	assertFalse(g.isWalkable());
    }

    /**
     * Check if the initialize position is the same as expect 
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(g.getPosition()));
    }

}
