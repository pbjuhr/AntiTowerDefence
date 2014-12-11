package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Goal;
import com.jaap.antitowerdefence.terrain.Portal;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.TeleporterUnit;

/**
 * Tests the public methods of TeleporterUnit class
 * @author Peter Bjuhr
 */
public class TestTeleporterUnit {
    
    TeleporterUnit u;
    Terrain[] walkable;
    int timeStep;
    int stepsPerSec;

    @Before
    public void setUp() {
	
	Position pos1 = new Position(1,0);
	Position pos2 = new Position(1,1);
	Position pos3 = new Position(1,2);
	Position pos4 = new Position(1,3);
	Position pos5 = new Position(1,4);
	
	ArrayList<Direction> dirs = new ArrayList<Direction>();
	dirs.add(Direction.NORTH);
	dirs.add(Direction.SOUTH);
	dirs.add(Direction.EAST);
	dirs.add(Direction.WEST);
	Switch start = new Switch(pos1, dirs);
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
	stepsPerSec = 30;
	
	u = new TeleporterUnit(walkable, stepsPerSec);
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
     * Tests isAlive method
     */
    @Test
    public void testIsAlive() {
	boolean alive = true;
	for(int i = 0; i < 10; i++){
	    u.takeDamage();
	}
	assertNotEquals(alive, u.isAlive());
    }
    
    /**
     * Tests action
     */
    @Test
    public void testAction() {
	int timeStep = 0;
	// Moves 3 times
	while(timeStep < 4 * stepsPerSec){
	    u.action();
	    timeStep++;
	}
	assertTrue(u.getPosition().equals(new Position(1,3)));
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
	int timeStep = 0;
	while(timeStep < 5 * stepsPerSec){
	    if(timeStep == (stepsPerSec + 1) || timeStep == 4*stepsPerSec){
		System.out.println("Time: " + timeStep + ", places portal on posX: " + u.getPosition().getX() + ", Y: " +u.getPosition().getY());
		u.placePortal();
	    }
	    u.action();
	    timeStep++;
	}
	
	for(int i = 0; i < walkable.length; i++) {
	    System.out.println("i: " + i + ", portal: " + (walkable[i] instanceof Portal) + ", pos: x:" + walkable[i].getPosition().getX() + ", pos: y:" + walkable[i].getPosition().getY());
	}
	
    }
    
    /**
     * Tests hasReachedGoal
     */
    @Test
    public void testReachedGoal() {
	while(!u.hasReachedGoal()){
	    u.action();
	}
	assertTrue(u.hasReachedGoal());
    }
}
