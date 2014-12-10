/**
 * 
 */
package com.jaap.antitowerdefence.terrain;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * @author andreasbolzyk
 *
 */
public class WaterTest {

private static Water w;
    
    @BeforeClass
    public static void setUpBeforeClass(){
	w = new Water(new Position(1,1));
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
	assertTrue(new Position(1,1).equals(w.getPosition()));
    }

    
}
