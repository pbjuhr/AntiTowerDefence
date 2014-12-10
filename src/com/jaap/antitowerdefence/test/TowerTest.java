package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
    private ArrayList<Unit> u;
    private Terrain[] walkable;

    @Before
    public void setUp() throws Exception {
	t = new Tower(new Position(2, 2));
	walkable = new Terrain[3];
	u = new ArrayList<Unit>();

	walkable[0] = new Start(new Position(1, 0));
	walkable[1] = new Road(new Position(1, 1));
	walkable[2] = new Goal(new Position(1, 2));

	u.add(new SoldierUnit(walkable, 0, 30));
	u.add(new SoldierUnit(walkable, 0, 30));
	t.setUnits(u);
    }

    @Test
    public void testShootUnit1() {
	t.shoot(u.get(0));
	assertTrue(u.get(1).getHealth() > u.get(0).getHealth());
    }

    @Test
    public void testShootUnit2() {
	t.shoot(u.get(1));
	assertTrue(u.get(1).getHealth() < u.get(0).getHealth());
    }

    @Test
    public void testPosition() {
	assertTrue(new Position(2, 2).equals(t.getPosition()));
    }

    @Test
    public void testFindUnitInRange1() {
	assertNotNull(t.findUnitInRange());
    }

    @Test
    public void testFindUnitInRange2() {
	t.setPosition(new Position(6, 6));
	assertNull(t.findUnitInRange());
    }

}
