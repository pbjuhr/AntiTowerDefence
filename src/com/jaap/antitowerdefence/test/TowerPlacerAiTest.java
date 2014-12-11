package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.TowerPlacerAi;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * Tests the functionality of the class AiTowerPlacer.java
 * 
 * @author id10abk
 *
 */
public class TowerPlacerAiTest {

    private TowerPlacerAi aitp;
    private Terrain[] possiblePositions;

    @Before
    public void setUp() throws Exception {

	possiblePositions = new Terrain[4];

	possiblePositions[0] = new Grass(new Position(1, 1));
	possiblePositions[1] = new Grass(new Position(2, 1));
	possiblePositions[2] = new Grass(new Position(3, 1));
	possiblePositions[3] = new Grass(new Position(4, 1));

	aitp = new TowerPlacerAi(possiblePositions, 2, 30);

    }

    @Test
    public void testgetNewTowers() {
	assertNotNull(aitp.getNewTowers());
    }

    @Test
    public void testgetNewTowersTwoTimes() {
	assertNotNull(aitp.getNewTowers());
	assertNotNull(aitp.getNewTowers());
    }

    @Test
    public void testCheckTowerPos() {
	Position p1 = aitp.getNewTowers()[0].getPosition();
	Position p2 = aitp.getNewTowers()[0].getPosition();
	assertFalse(p1.equals(p2));
	assertFalse(p1.getX() == (p2).getX());
    }

}
