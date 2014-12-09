package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.HighScoreDB;

public class HighScoreDBTest {

    private static HighScoreDB highScore;
    @BeforeClass
    public static void setUpBeforeClass(){
	highScore = new HighScoreDB();
    }

    @Test
    public void testHighScoreDB() {
	assertNotNull(highScore);
    }

}
