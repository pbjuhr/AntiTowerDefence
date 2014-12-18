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

    private static Portal sp;
    private static Portal ep;

    /**
     * Initialize class Portal Create a start portal Create a end portal
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	sp = new Portal(new Position(1, 1));
	ep = new Portal(new Position(3, 3));
	sp.setReciever(ep);
    }

    /**
     * Check if buildable is false
     */
    @Test
    public void testBuildable() {
	assertFalse(sp.isBuildable());
    }

    /**
     * Check if walkable is true
     */
    @Test
    public void testWalkable() {
	assertTrue(sp.isWalkable());
    }

    /**
     * Check if the initialize position is the same as expect
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(sp.getPosition()));
    }

    /**
     * Check if the initialize position is the same as expect
     */
    @Test
    public void testRecieverPosition() {
	assertTrue(ep.equals(sp.getReciever()));
    }

    /**
     * Check if start portal has a reciver
     */
    @Test
    public void testHasReciver() {
	assertTrue(sp.hasReciever());
    }

}
