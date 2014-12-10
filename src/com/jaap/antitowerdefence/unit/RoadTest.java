/**
 * 
 */
package com.jaap.antitowerdefence.unit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Road;

/**
 * @author andreasbolzyk
 *
 */
public class RoadTest {

    private static Road r;

    @BeforeClass
    public static void setUpBeforeClass() {
	r = new Road(new Position(1, 1));
    }

    @Test
    public void testBuildable() {
	assertFalse(r.isBuildable());
    }

    @Test
    public void testWalkable() {
	assertTrue(r.isWalkable());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(1, 1).equals(r.getPosition()));
    }

}
