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

public class LevelReaderTest {
    static LevelReader levelReader;
    static int level;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	levelReader = new LevelReader("src/levels.xml");
	level = 1;
    }

    @Test
    public void testLevelReader() {
	assertNotNull(levelReader);
    }

    @Test
    public void testGetLevelStats() {
	LevelStats levelstats= levelReader.getLevelStats(level);
	assertNotNull(levelstats);
    }

    @Test
    public void testGetNrOfLevels() {
	int nrLev = levelReader.getNrOfLevels();
	assertEquals(nrLev, 2);
    }
    
    @Test
    public void testGetPossibleTowerPositions() {
	Position[] possibleTowerPositions = 
		levelReader.getPossibleTowerPositions(level);
	assertNotNull(possibleTowerPositions);
	assertTrue(possibleTowerPositions.length > 0);
    }
    
    @Test
    public void testGetNrOfTowers() {
	assertNotNull(levelReader.getNrOfTowers(level));
	assertTrue(levelReader.getNrOfTowers(level) > 0);
    }

    @Test
    public void testGetUnits(){
	String[] units = levelReader.getUnits(level);
	assertNotNull(units);
	assertTrue(units.length > 0);
    }
    
    @Test
    public void testHasUnits(){
	boolean[] units = levelReader.hasUnits(level);
	assertNotNull(units);
	assertTrue(units.length > 0);
    }
    
    @Test
    public void testGetXDimension(){
	int xDim = levelReader.getXDimension();
	assertEquals(xDim, 20);
    }
    
    @Test
    public void testGetYDimension(){
	int yDim = levelReader.getYDimension();
	assertEquals(yDim, 15);
    }
    
    @Test
    public void testGetRoad(){
	Terrain[] road = levelReader.getRoad(level);
	assertNotNull(road);
    }
}
