package com.jaap.antitowerdefence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

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
    CopyOnWriteArrayList<Terrain> walkable;
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
	
	walkable = new CopyOnWriteArrayList<Terrain>();
	walkable.add(start);
	walkable.add(road1);
	walkable.add(road2);
	walkable.add(road3);
	walkable.add(goal);
	
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
	assertTrue(u.getPosition().equals(new Position(1,4)));
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
	while(!u.hasReachedGoal()){
	    //Place portal on pos2 and pos4
	    if(u.getPosition().equals(walkable.get(1).getPosition()) || 
		    u.getPosition().equals(walkable.get(3).getPosition())){
		u.placePortal();
	    }
	    u.action();
	}
	
	System.out.println("0: " + (walkable.get(0) instanceof Portal));
	System.out.println("1: " + (walkable.get(1) instanceof Portal));
	System.out.println("2: " + (walkable.get(2) instanceof Portal));
	System.out.println("3: " + (walkable.get(3) instanceof Portal));
	System.out.println("4: " + (walkable.get(4) instanceof Portal));
	
	assertTrue(walkable.get(1) instanceof Portal);
	assertTrue(walkable.get(3) instanceof Portal);
	assertTrue(((Portal)walkable.get(1)).hasReciever());
	assertFalse(((Portal)walkable.get(3)).hasReciever());
	assertTrue(((Portal)walkable.get(1)).getReciever().
		equals(((Portal)walkable.get(3)).getPosition()));
    }
}
