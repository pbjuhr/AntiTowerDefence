package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Start;

/**
 * Tests the functionality of the class Start.java
 * 
 * @author id10abk
 *
 */
public class StartTest {

    private static Start s;

    @BeforeClass
    public static void setUpBeforeClass() {
	ArrayList<Direction> dir = new ArrayList<Direction>();
	dir.add(Direction.EAST);
	dir.add(Direction.NORTH);
	s = new Start(new Position(1, 1), dir);
    }

    @Test
    public void testBuildable() {
	assertFalse(s.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertTrue(s.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(s.getPosition()));
    }

}
