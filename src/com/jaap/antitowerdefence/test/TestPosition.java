/**
 * Example test class to test Position
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
/**
 * TestPosition.java
 * 
 * Creates an instance of Position and tests the functionallity
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class TestPosition {
    Position position;
    int x;
    int y;

    /**
     * setUp() initiates x and y and creates an instance of Position
     */
    @Before
    public void setUp() {
	x = 4;
	y = 6;
	position = new Position(x, y);
    }

    /**
     * testSetX() checks if it is possible to set the value of 
     * x in the Position
     */
    @Test
    public void testSetX() {
	int x;
	this.x = 5;
	position.setX(this.x);
	x = position.getX();
	assertEquals(this.x, x);
    }

    /**
     * testSetY() checks if it is possible to set the value of 
     * y in the Position
     */
    @Test
    public void testSetY() {
	int y;
	this.y = 5;
	position.setY(this.y);
	y = position.getY();
	assertEquals(this.y, y);
    }

    /**
     * testGetX() checks if Position returns the correct value of x
     */
    @Test
    public void testGetX() {
	int x = position.getX();
	assertEquals(this.x, x);
    }

    /**
     * testGetY() checks if Position returns the correct value of y
     */
    @Test
    public void testGetY() {
	int y = position.getY();
	assertEquals(this.y, y);
    }

}
