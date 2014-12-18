package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.AntiTowerDefenceGame;

/**
 * Test a back end of the game.
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class AntiTowerDefenceGameTest {

    private AntiTowerDefenceGame game;
    private String path = "assets/levels/levels.xml";

    /**
     * 
     * Initialize class AntiTowerDefenceGame
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
	game = new AntiTowerDefenceGame(path, 10);
    }

    /**
     * Check if can get next level
     */
    @Test
    public void testGetLevel() {
	assertNotNull(game.getLevel());
    }

    /**
     * Check if can get next level and contains walkable terrain
     */
    @Test
    public void testGetLevelgetWalkableTerrain() {
	assertNotNull(game.getLevel().getWalkableTerrain());
	System.out.println("The Array length "
		+ game.getLevel().getWalkableTerrain().size());
    }

    /**
     * Check if can get next level and contains possible tower positions
     */
    @Test
    public void testGetPossibleTowerPositions() {
	assertNotNull(game.getLevel().getPossibleTowerPositions());
	System.out.println("The Array length "
		+ game.getLevel().getPossibleTowerPositions().length);
    }

    /**
     * Check if can get possible units
     */
    @Test
    public void testGetPossibleUnits() {
	assertNull(game.getPossibleUnits()[0]);
    }

    /**
     * Check if can create farmer unit
     */
    @Test
    public void testCreateFarmer() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    /**
     * Check if can create soldier unit
     */
    @Test
    public void testCreateSoldier() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    /**
     * Check if can create teleporte unit
     */
    @Test
    public void testCreateTeleporter() {
	game.createFarmer();
	assertNotNull(game.getLevel().getUnits().get(0));
    }

    /**
     * Check if can get next level two times
     */
    @Test
    public void testNewLevel() {
	game.createFarmer();
	assertFalse(game.getLevel().getUnits().isEmpty());
	game.newLevel();
	assertTrue(game.getLevel().getUnits().isEmpty());
    }

}
