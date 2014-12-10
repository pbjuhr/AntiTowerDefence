/**
 * Tests the public functions of Unit class
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Goal;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.TeleporterUnit;

public class TestTeleporterUnit {
    
    TeleporterUnit u;
    Terrain[] walkable;
    int timeStep;
    int fps;

    @Before
    public void setUp() {
	
	Position pos1 = new Position(1,0);
	Position pos2 = new Position(1,1);
	Position pos3 = new Position(1,2);
	Position pos4 = new Position(1,3);
	Position pos5 = new Position(1,4);
	
	Start start = new Start(pos1);
	Road road1 = new Road(pos2);
	Road road2 = new Road(pos3);
	Road road3 = new Road(pos4);
	Goal goal = new Goal(pos5);
	
	walkable = new Terrain[5];
	walkable[0] = start;
	walkable[1] = road1;
	walkable[2] = road2;
	walkable[3] = road3;
	walkable[4] = goal;
	
	timeStep = 0;
	fps = 30;
	
	u = new TeleporterUnit(walkable, timeStep, fps);
    }

    /**
     * Tests setPosition method
     */
    @Test
    public void testSetPosition() {
	Position pos = new Position(1,3);
	u.setPosition(pos);
	assertFalse(pos.equals(u.getPosition()));
    }
    
    /**
     * Tests takeDamage method
     */
    @Test
    public void testTakeDamage() {
	int health = u.getHealth();
	u.takeDamage();
	assertNotEquals(health, u.getHealth());
    }
    
    /**
     * Tests isDead method
     */
    @Test
    public void testIsDead() {
	boolean alive = u.isDead();
	for(int i = 0; i < 10; i++){
	    u.takeDamage();
	}
	assertNotEquals(alive, u.isDead());
    }
    
    /**
     * Tests action
     */
    @Test
    public void testAction() {
	int timeStep = 0;
	u.action(timeStep);
	assertTrue(u.getPosition().equals(new Position(1,1)));
    }
    
    /**
     * Tests setDirection
     */
    @Test
    public void testSetDirection() {
	u.setDirection(Direction.SOUTH);
	assertTrue(u.getDirection() == Direction.SOUTH);
    }
    
    /**
     * Tests placeTower
     */
    @Test
    public void testPlaceTower() {
	assertTrue(false);
    }
    
    /**
     * Tests hasReachedGoal
     */
    @Test
    public void testReachedGoal() {
	int i = 0;
	while(!u.hasReachedGoal()){
	    u.action(i);
	    i++;
	}
	assertTrue(u.hasReachedGoal());
    }
}
