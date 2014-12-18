package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.TowerPlacerAI;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * Tests the functionality of the class AiTowerPlacer.java
 * 
 * @author id10abk
 *
 */
public class TowerPlacerAITest {

    private TowerPlacerAI aitp;
    private Terrain[] possiblePositions;

    /**
     * Initialize class Tower and TowerPlacerAI Create a position array for
     * TowerPlacerAI that contains grass terrain
     */
    @Before
    public void setUp() throws Exception {

	possiblePositions = new Terrain[4];

	possiblePositions[0] = new Grass(new Position(1, 1));
	possiblePositions[1] = new Grass(new Position(2, 1));
	possiblePositions[2] = new Grass(new Position(3, 1));
	possiblePositions[3] = new Grass(new Position(4, 1));

	aitp = new TowerPlacerAI(possiblePositions, 2, 30);

    }

    /**
     * Check if can place new a tower
     */
    @Test
    public void testgetNewTowers() {
	assertNotNull(aitp.getNewTowers());
	assertTrue(aitp.getNewTowers().size() != 2);
    }

    /**
     * Check if can place new more that one tower
     */
    @Test
    public void testgetNewTowersTwoTimes() {
	assertNotNull(aitp.getNewTowers());
	assertNotNull(aitp.getNewTowers());
    }

    /**
     * Check if can place new more that one tower and positions are different
     */
    @Test
    public void testCheckTowerPos() {
	Position p1 = aitp.getNewTowers().get(0).getPosition();
	Position p2 = aitp.getNewTowers().get(0).getPosition();
	assertFalse(p1.equals(p2));
	assertFalse(p1.getX() == (p2).getX());
    }

}
