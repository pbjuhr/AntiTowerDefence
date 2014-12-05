package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.level.LevelReader;
import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Terrain;
/**
 * LevelReaderTest.java
 * 
 * Tests the functionality of the class LevelReader.java
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelReaderTest {
    static LevelReader levelReader;
    static int level;
    
    /**
     * A LevelReader that is used in all test methods is created once, before 
     * the tests are executed. Which level to test is also set.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	levelReader = new LevelReader("src/levels.xml");
	level = 1;
    }

    /**
     * Test that checks if the LevelReader was created.
     */
    @Test
    public void testLevelReader() {
	assertNotNull(levelReader);
    }

    /**
     * Test to see that the LevelReader function getLevelStats return a
     * LevelStats object that is not null.
     */
    @Test
    public void testGetLevelStats() {
	LevelStats levelstats= levelReader.getLevelStats(level);
	assertNotNull(levelstats);
    }

    /**
     * Checks that getNrOfLevels returns the correct number of levels in the
     * xml-file.
     */
    @Test
    public void testGetNrOfLevels() {
	int nrLev = levelReader.getNrOfLevels();
	assertEquals(nrLev, 2);
    }
    
    /**
     * Checks thar getPossibleTowerPositions returns an array of Position that
     * is not null or empty
     */
    @Test
    public void testGetPossibleTowerPositions() {
	Position[] possibleTowerPositions = 
		levelReader.getPossibleTowerPositions(level);
	assertNotNull(possibleTowerPositions);
	assertTrue(possibleTowerPositions.length > 0);
    }
    
    /**
     * Checks that getNrOfTowers returns an int bigger than 0.
     */
    @Test
    public void testGetNrOfTowers() {
	assertNotNull(levelReader.getNrOfTowers(level));
	assertTrue(levelReader.getNrOfTowers(level) > 0);
    }

    /**
     * Checks that getUnits returns a string-array with a length > 0.
     */
    @Test
    public void testGetUnits(){
	String[] units = levelReader.getUnits(level);
	assertNotNull(units);
	assertTrue(units.length > 0);
    }
    
    /**
     * Checks that hasUnits returns a boolean-array with a length > 0.
     */
    @Test
    public void testHasUnits(){
	boolean[] units = levelReader.hasUnits(level);
	assertNotNull(units);
	assertTrue(units.length > 0);
    }
    
    /**
     * Checks that getXDimension the correct x-dimension value.
     */
    @Test
    public void testGetXDimension(){
	int xDim = levelReader.getXDimension();
	assertEquals(xDim, 20);
    }
    
    /**
     * Checks that getYDimension the correct y-dimension value.
     */
    @Test
    public void testGetYDimension(){
	int yDim = levelReader.getYDimension();
	assertEquals(yDim, 15);
    }
    /**
     * Checks thar getRoad returns an array of Terrain that
     * is not null or empty
     */
    @Test
    public void testGetRoad(){
	Terrain[] road = levelReader.getRoad(level);
	assertNotNull(road);
	assertTrue(road.length > 0);
    }
}
