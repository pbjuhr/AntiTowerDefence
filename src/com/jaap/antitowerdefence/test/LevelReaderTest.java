package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

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
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    @BeforeClass
    public static void setUpBeforeClass() throws ParserConfigurationException, 
    						SAXException, IOException {
	levelReader = new LevelReader("assets/levels/levels.xml");
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
	assertEquals(6, nrLev);
    }
    
    /**
     * Checks thar getGrass returns an array of Terrain that
     * is not null or empty
     */
    @Test
    public void testGetGrass() {
	Terrain[] grass = 
		levelReader.getGrass(level);
	assertNotNull(grass);
	assertTrue(grass.length > 0);
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
    public void testGetUnits() {
	String[] units = levelReader.getUnits(level);
	assertNotNull(units);
	assertTrue(units.length > 0);
    }

    /**
     * Checks that getXDimension the correct x-dimension value.
     */
    @Test
    public void testGetXDimension() {
	int xDim = levelReader.getXDimension();
	assertEquals(xDim, 20);
    }
    
    /**
     * Checks that getYDimension the correct y-dimension value.
     */
    @Test
    public void testGetYDimension() {
	int yDim = levelReader.getYDimension();
	assertEquals(yDim, 15);
    }
    
    /**
     * Checks thar getRoad returns an array of Terrain that
     * is not null or empty
     */
    @Test
    public void testGetRoad() {
	CopyOnWriteArrayList<Terrain> road = levelReader.getRoad(level);
	assertNotNull(road);
	assertTrue(road.size() > 0);
    }
}
