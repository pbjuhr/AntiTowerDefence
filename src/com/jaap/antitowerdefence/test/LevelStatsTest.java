package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.level.LevelStats;
/**
 * 
 * @author Anna Österlund, id10aod
 *
 */
public class LevelStatsTest {

    private LevelStats levelstats;
    @Before
    public void setUp() throws Exception {
	levelstats = new LevelStats(60, 500);
    }

    @Test
    public void testLevelStats() {
	assertNotNull(levelstats);
    }

    @Test
    public void testAddScore() {
	int oldScore;
	int newScore;
	oldScore = levelstats.getScore();
	levelstats.addScore(30);
	newScore = levelstats.getScore();
	assertTrue((newScore - oldScore) == 30);
    }

    @Test
    public void testGetScore() {
	levelstats.addScore(30);
	assertTrue((levelstats.getScore()) == 30);
    }

    @Test
    public void testAddCredits() {
	int oldCredits;
	int newCredits;
	oldCredits = levelstats.getCredits();
	levelstats.addCredits(20);
	newCredits = levelstats.getCredits();
	System.out.println("Old = "+oldCredits);
	System.out.println("New = "+newCredits);
	assertTrue(newCredits > oldCredits);
    }

    @Test
    public void testGetCredits() {
	assertTrue(levelstats.getCredits() == 500);
    }

    @Test
    public void testGetWinScore() {
	assertTrue(levelstats.getWinScore() == 60);
    }

}
