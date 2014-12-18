package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Switch;

/**
 * Tests the functionality of the class Switch.java
 * 
 * @author id10abk
 *
 */
public class SwitchTest {

    private static Switch s;
    static ArrayList<Direction> dir;

    /**
     * Initialize class Switch
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	dir = new ArrayList<Direction>();
	dir.add(Direction.EAST);
	dir.add(Direction.NORTH);
	s = new Switch(new Position(1, 1), dir);
	s.setDirection(Direction.EAST);
    }

    /**
     * Check if buildable is false
     */
    @Test
    public void testBuildable() {
	assertFalse(s.isBuildable());
    }

    /**
     * Check if walkable is true
     */
    @Test
    public void testWalkable() {
	assertTrue(s.isWalkable());
    }

    /**
     * Check if the initialize position is the same as expect
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(s.getPosition()));
    }

    /**
     * Check if the initialize direction is the same as expect
     */
    @Test
    public void testDirection() {
	assertTrue(s.getDirection().equals(Direction.EAST));
    }

}
