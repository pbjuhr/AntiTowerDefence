package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Water;

/**
 * Tests the functionality of the class Water.java
 * 
 * @author id10abk
 *
 */
public class WaterTest {

    private static Water w;

    @BeforeClass
    public static void setUpBeforeClass() {
	w = new Water(new Position(1, 1));
    }

    @Test
    public void testBuildable() {
	assertFalse(w.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertFalse(w.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(w.getPosition()));
    }

}
