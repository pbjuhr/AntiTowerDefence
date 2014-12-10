package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Switch;

public class SwitchTest {

    private static Switch s;

    @BeforeClass
    public static void setUpBeforeClass() {
	s = new Switch(new Position(1, 1));
	s.setDirection(Direction.EAST);
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

    @Test
    public void testDirection() {
	assertTrue(s.getdDirection().equals(Direction.EAST));
    }

}
