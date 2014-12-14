package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.AntiTowerDefenceGame;

/**
 * 
 * @author andreasbolzyk id10abk
 *
 */
public class AntiTowerDefenceGameTest {

    private AntiTowerDefenceGame game;
    private String path = "assets/levels/levels.xml";

    @Before
    public void setUp() throws Exception {
	game = new AntiTowerDefenceGame(path, 10);
    }

    @Test
    public void testGetLevel() {
	assertNotNull(game.getLevel());
    }

    @Test
    public void testGetLevelgetWalkableTerrain() {
	assertNotNull(game.getLevel().getWalkableTerrain());
	System.out.println("The Array length "
		+ game.getLevel().getWalkableTerrain().length);
    }

    @Test
    public void testGetPossibleTowerPositions() {
	assertNotNull(game.getLevel().getPossibleTowerPositions());
	System.out.println("The Array length "
		+ game.getLevel().getPossibleTowerPositions().length);
    }

    // Do not understand how to test highScore
    @Test
    public void testGetHighScorel() {
	// assertNull(game.getHighScore(20).addScore("Alex", 300));
	// System.out.println(game.getHighScore(20).getHighScoreTopTen()[0][0]);
    }

    @Test
    public void testGetPossibleUnits() {
	assertNull(game.getPossibleUnits()[0]);
    }

    // Why 1040 and not 40
    @Test
    public void testGetCredits() {
	game.getLevel().getLevelStats().addCredits(40);
	System.out.println(game.getLevel().getLevelStats().getCredits());
    }

    @Test
    public void testCreateFarmer() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    @Test
    public void testCreateSoldier() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    @Test
    public void testCreateTeleporter() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    @Test
    public void testNewLevel() {
	game.createFarmer();
	assertFalse(game.getLevel().getUnits().isEmpty());
	game.newLevel();
	assertTrue(game.getLevel().getUnits().isEmpty());
    }

//    @Test
//    public void testGetTower() {
//	assertNotNull(game.getTowerArray());
//    }
//
//    // Tower array in level is to big have empty spaces
//    // Added if null condition
//    @Test
//    public void testStep() {
//	game.createFarmer();
//	assertNotNull(game.getLevel().getUnits().get(0));
//	game.getLevel().setTowers(game.getTowerArray());
//	assertNotNull(game.getLevel().getTowers()[0]);
//	game.step();
//    }

}
