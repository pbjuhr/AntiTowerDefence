package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.antiTowerDefence.TowerPlacerAI;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * 
 * @author id10abk
 *
 */
public class TowerPlacerAITest {

    private Tower t;
    private TowerPlacerAI tpai;
    private Terrain[] possiblePositions;

    @Before
    public void setUp() throws Exception {

	possiblePositions = new Terrain[4];

	possiblePositions[0] = new Grass(new Position(1, 1));
	possiblePositions[1] = new Grass(new Position(1, 2));
	possiblePositions[2] = new Grass(new Position(1, 3));
	possiblePositions[3] = new Grass(new Position(1, 4));

	tpai = new TowerPlacerAI(possiblePositions, 2, 30);

    }

    @Test
    public void testGetNewTowers() {
	assertNotNull(tpai.getNewTowers());
	assertNotNull(tpai.getNewTowers());
    }

}
