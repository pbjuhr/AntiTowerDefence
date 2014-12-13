package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.HighScoreDB;
/**
 * HighScoreDBTest.java
 * 
 * Tests the functionality of the class HighScoreDB.java
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class HighScoreDBTest {

    private static HighScoreDB highScore;
    private static boolean success;
    
    /**
     * Initiates a HighScoreDB before running the tests
     */
    @BeforeClass
    public static void setUpBeforeClass(){
	highScore = new HighScoreDB();
	success = highScore.connectToDB();
    }

    /**
     * Tests if highScore was initiated
     */
    @Test
    public void testHighScoreDB() {
	assertTrue(success);
	assertNotNull(highScore);
    }
    
    /**
     * Tests the addScore method
     */
    @Test
    public void testAddScore() {
	String result;
	result = highScore.addScore("Krister", 6);
	System.out.println(result);
	assertEquals("success", result);
	
    }
    
    /**
     * Tests the getHighScoreTopTen method
     */
    @Test 
    public void testGetHighScoreTopTen() {
	String[][] result;
	result = highScore.getHighScoreTopTen();
	assertNotNull(result);
    }
    
    /**
     * Tests the isHighScore method
     */
    @Test
    public void testIsHighScore() {
	String result = highScore.isHighScore(6);
	assertTrue(result != "fail");
    }
}
