/**
 * Tests the public functions of Unit class
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Goal;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.TeleporterUnit;
import com.jaap.antitowerdefence.unit.Unit;

public class TestUnit {
    
    Unit u;
    Terrain[] walkable;
    int timeStep;

    @Before
    public void setUp() {
	
	Position pos1 = new Position(1,0);
	Position pos2 = new Position(1,1);
	Position pos3 = new Position(1,2);
	Position pos4 = new Position(1,3);
	Position pos5 = new Position(1,4);
	
	Start start = new Start(pos1);
	Goal goal = new Goal(pos5);
	Road road1 = new Road(pos2);
	Road road2 = new Road(pos3);
	Road road3 = new Road(pos4);
	
	walkable = new Terrain[5];
	walkable[0] = start;
	walkable[1] = goal;
	walkable[2] = road1;
	walkable[3] = road2;
	walkable[4] = road3;
	
	timeStep = 9999;
	
	u = new TeleporterUnit(walkable, timeStep);
    }

    /**
     * Tests setPosition method
     */
    @Test
    public void testSetPosition() {
	Position pos = new Position(1,3);
	u.setPosition(pos);
	assertTrue(pos.equals(u.getPosition()));
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
     * Tests hasReachedGoal
     */
    @Test
    public void testReachedGoal() {
	timeStep = 10;
	u = new TeleporterUnit(walkable, timeStep);
	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("x: " + u.getPosition().getX() + ", y: " + u.getPosition().getY());
	assertTrue(u.hasReachedGoal());
    }
}
