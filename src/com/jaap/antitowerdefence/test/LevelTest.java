package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.unit.FarmerUnit;
/**
 * LevelTest.java
 * 
 * TODO: Beskrivning, kommentarer
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelTest {

    static Level level;
    static LevelStats lvlStats;
    
    @BeforeClass
    public static void setUpBeforeClass() {
	lvlStats = new LevelStats(30, 200);
	level = new Level(new Grass[10], new Road[20], 
			lvlStats);
    }

    @Test
    public void testLevel() {
	assertNotNull(level);
    }

    @Test
    public void testGetWalkableTerrain() {
	assertNotNull(level.getWalkableTerrain());
    }

    @Test
    public void testGetPossibleTowerPositions() {
	assertNotNull(level.getPossibleTowerPositions());
    }

    @Test
    public void testAddUnit() {
	int units = level.getUnits().size();
	level.addUnit(new FarmerUnit(new Road[10], 2, 4));
	assertTrue(units < level.getUnits().size());
    }

    @Test
    public void testGetUnits() {
	assertNotNull(level.getUnits());
    }

    @Test
    public void testSetTowers() {
	Tower[] towers = new Tower[2];
	Position position = new Position(1,2);
	towers[0] = new Tower(position);
	towers[1] = new Tower(position);
	level.setTowers(towers);
	assertNotNull(level.getTowers());
    }

    @Test
    public void testGetTowers() {
	assertNotNull(level.getTowers());
    }

    @Test
    public void testGetLevelStats() {
	LevelStats stats = level.getLevelStats();
	assertNotNull(stats);
	assertEquals(30, stats.getWinScore());
    }
}
