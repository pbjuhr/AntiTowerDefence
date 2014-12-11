package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.AntiTowerDefenceGame;

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
    
    //
    @Test
    public void testGetHighScorel() {
	assertNotNull(game.getHighScore(10));
    }
    

    @Test
    public void testGetPossibleUnits() {
	assertNull(game.getPossibleUnits()[0]);
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

}
