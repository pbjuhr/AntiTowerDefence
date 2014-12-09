package com.jaap.antitowerdefence.terrain;

import java.util.Random;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Switch extends Terrain implements LandOnInterface {
    
    String direction; // Ändra från sträng till enum-grejen / Peter
   
    public Switch(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    @Override
    public void landOn(Unit u) {
	//Direction.EAST.compareTo(Direction.EAST);
	
	/* Jag ändrade inparametern till this.direction, landOn behöver inte
	 * göra något mer. /Peter */ 
	u.setDirection(this.direction);
    }
    
    /**
     * Sets the switch direction
     * @author Peter
     * @param newDirection, the new direction
     */
    public void setDirection(String newDirection) {
	if (newDirection.equals("north") || newDirection.equals("south")
		|| newDirection.equals("east") || newDirection.equals("west")) {
	    this.direction = newDirection;
	}
    }

    // Test of random
    public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
	    System.out.println(Direction.getRandomDirection());
	}
    }
}

//enum Direction {
//    NORTH, SOUTH, WEST, EAST;
//
//    public static Direction getRandomDirection() {
//	Random random = new Random();
//	return values()[random.nextInt(values().length)];
//    }
//
//}
