package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.level.LevelStats;
/**
 * LevelStatsTest.java
 * 
 * Tests the functionality of the class LevelStats.java
 * 
 * @author Anna Österlund, id10aod
 *
 */
public class LevelStatsTest {

    private LevelStats levelstats;
    
    /**
     * A new Object of LevelStats is initiated before each test method
     */
    @Before
    public void setUp() {
	levelstats = new LevelStats(60, 500);
    }

    /**
     * Test that checks if the LevelStats object was created.
     */
    @Test
    public void testLevelStats() {
	assertNotNull(levelstats);
    }

    /**
     * testAddScore adds some points to the score and checks if the score
     * has increased
     */
    @Test
    public void testAddScore() {
	int oldScore;
	int newScore;
	oldScore = levelstats.getScore();
	levelstats.addScore(30);
	newScore = levelstats.getScore();
	assertTrue((newScore - oldScore) == 30);
    }

    /**
     * Checks if getScore provides the correct score
     */
    @Test
    public void testGetScore() {
	levelstats.addScore(30);
	assertTrue((levelstats.getScore()) == 30);
    }

    /**
     * testAddCredits adds a value to the credits and checks if the credits
     * has increased
     */
    @Test
    public void testAddCredits() {
	int oldCredits;
	int newCredits;
	oldCredits = levelstats.getCredits();
	levelstats.addCredits(20);
	newCredits = levelstats.getCredits();
	assertTrue(newCredits > oldCredits);
    }

    /**
     * Checks if getCredits provides the correct credit value
     */
    @Test
    public void testGetCredits() {
	assertTrue(levelstats.getCredits() == 500);
    }

    /**
     * Checks if getWinScore provides the correct winscore
     */
    @Test
    public void testGetWinScore() {
	assertTrue(levelstats.getWinScore() == 60);
    }

}
