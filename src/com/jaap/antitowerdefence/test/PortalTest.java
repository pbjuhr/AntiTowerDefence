package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Portal;

/**
 * Tests the functionality of the class Portal.java
 * 
 * @author id10abk
 *
 */
public class PortalTest {

    private static Portal p;

    @BeforeClass
    public static void setUpBeforeClass() {
	p = new Portal(new Position(1, 1));
//	p.setReciever(new Position(2, 1));
    }

    @Test
    public void testBuildable() {
	assertFalse(p.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertTrue(p.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(p.getPosition()));
    }

    @Test
    public void testRecieverPosition() {
	assertTrue(new Position(2, 1).equals(p.getReciever()));
    }

    @Test
    public void testHasReciver() {
	assertTrue(p.hasReciever());
    }

}
