package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.FarmerUnit;
/**
 * LevelTest.java
 * 
 * Tests the functionality of the class Level.java
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelTest {

    static Level level;
    static LevelStats lvlStats;
    
    /**
     * Before the tests are run an instance of Level is created.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
	lvlStats = new LevelStats(30, 200);
	CopyOnWriteArrayList<Terrain> path = 
		new CopyOnWriteArrayList<Terrain>();
	level = new Level(path, new Grass[20], lvlStats);
    }

    /**
     * Checks that level was initiated
     */
    @Test
    public void testLevel() {
	assertNotNull(level);
    }

    /**
     * Tests that the function getWalkableTerrain is not returning null
     */
    @Test
    public void testGetWalkableTerrain() {
	assertNotNull(level.getWalkableTerrain());
    }

    /**
     * Tests that the function getPossibleTowerPosition is not returning null
     */
    @Test
    public void testGetPossibleTowerPositions() {
	assertNotNull(level.getPossibleTowerPositions());
    }

    /**
     * Tests that the function addUnit is adding a unit to the Unit ArrayList
     */
    @Test
    public void testAddUnit() {
	int units = level.getUnits().size();
	level.addUnit(new FarmerUnit(new CopyOnWriteArrayList<Terrain>(), 2));
	assertTrue(units < level.getUnits().size());
    }

    /**
     * Tests that the function getUnits is not returning null
     */
    @Test
    public void testGetUnits() {
	assertNotNull(level.getUnits());
    }

    /**
     * Tests that the function setTowers sets new towers and passes the active 
     * units to the towers
     */
    @Test
    public void testSetTowers() {
	CopyOnWriteArrayList<Tower> towers = new CopyOnWriteArrayList<Tower>();
	Position position = new Position(1,2);
	towers.add(new Tower(position, 20));
	towers.add(new Tower(position, 20));
	level.setTowers(towers);
	assertNotNull(level.getTowers());
    }

    /**
     * Tests that the function getTowers is not returning null
     */
    @Test
    public void testGetTowers() {
	assertNotNull(level.getTowers());
    }

    /**
     * Tests that the function getLevelStats is not returning null and that
     * the winScore correspond to the score set when it was constructed.
     */
    @Test
    public void testGetLevelStats() {
	LevelStats stats = level.getLevelStats();
	assertNotNull(stats);
	assertEquals(30, stats.getWinScore());
    }
    
    /**
     * Tests the method setSwitches
     */
    @Test
    public void testSetSwitches() {
	assertNotNull(level.getSwitches());
    }
}
