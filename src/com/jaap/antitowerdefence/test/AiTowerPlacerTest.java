package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.AiTowerPlacer;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;

public class AiTowerPlacerTest {

    private Tower t;
    private AiTowerPlacer aitp;
    private Terrain[] possiblePositions;

    @Before
    public void setUp() throws Exception {

	possiblePositions = new Terrain[4];

	possiblePositions[0] = new Grass(new Position(1, 1));
	possiblePositions[1] = new Grass(new Position(2, 1));
	possiblePositions[2] = new Grass(new Position(3, 1));
	possiblePositions[3] = new Grass(new Position(4, 1));

	aitp = new AiTowerPlacer(possiblePositions, 2, 30);

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
}
