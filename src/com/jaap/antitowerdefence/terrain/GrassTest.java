package com.jaap.antitowerdefence.terrain;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

public class GrassTest {

    private static Grass g;

    @BeforeClass
    public static void setUpBeforeClass() {
	g = new Grass(new Position(1, 1));
    }

    @Test
    public void testBuildable() {
	assertTrue(g.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertFalse(g.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(g.getPosition()));
    }

}
