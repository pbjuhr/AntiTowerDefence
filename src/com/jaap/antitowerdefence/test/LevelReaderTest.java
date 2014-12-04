package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.level.LevelReader;
import com.jaap.antitowerdefence.level.LevelStats;

public class LevelReaderTest {
    static LevelReader levelReader;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	levelReader = new LevelReader("src/levels.xml");
	
    }

    @Test
    public void testLevelReader() {
	assertNotNull(levelReader);
    }

    @Test
    public void testGetLevelStats() {
	LevelStats levelstats= levelReader.getLevelStats(1);
	System.out.println("credits = "+levelstats.getCredits());
	System.out.println("score = "+levelstats.getScore());
	System.out.println("winScore = "+levelstats.getWinScore());
	assertNotNull(levelstats);
    }

    @Test
    public void testGetNrOfLevels() {
	int nrLev = levelReader.getNrOfLevels();
	System.out.println(nrLev);
	assertEquals(nrLev, 2);
    }

}
