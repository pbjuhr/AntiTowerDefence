package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Goal;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.SoldierUnit;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * Tests the functionality of the class Tower.java
 * 
 * @author id10abk
 *
 */
public class TowerTest {

    private Tower t;
    private CopyOnWriteArrayList<Unit> u;

    /**
     * Initialize class Tower and TowerPlacerAI Create a CopyOnWriteArrayList
     * for Terrain that contains start, road and goal terrain Create a
     * CopyOnWriteArrayList for Units that contains soldierUnit
     */
    @Before
    public void setUp() throws Exception {
	t = new Tower(new Position(2, 2), 60);
	CopyOnWriteArrayList<Terrain> walkable;
	walkable = new CopyOnWriteArrayList<Terrain>();
	u = new CopyOnWriteArrayList<Unit>();

	ArrayList<Direction> dir = new ArrayList<Direction>();
	dir.add(Direction.EAST);
	dir.add(Direction.NORTH);

	walkable.add(new Start(new Position(1, 1), dir));
	walkable.add(new Road(new Position(1, 1)));
	walkable.add(new Goal(new Position(1, 2)));

	u.add(new SoldierUnit(walkable, 10));
	u.add(new SoldierUnit(walkable, 20));
	t.setUnits(u);
    }

    /**
     * Check if SoldierUnit has get shoot
     */
    @Test
    public void testShootUnit() {
	t.getUnitShot();
	assertTrue(u.get(1).getHealth() > u.get(0).getHealth());
    }

    /**
     * Check if the initialize position is the same as expect
     */
    @Test
    public void testPosition() {
	assertTrue(new Position(2, 2).equals(t.getPosition()));
    }

}
