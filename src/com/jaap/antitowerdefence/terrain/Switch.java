package com.jaap.antitowerdefence.terrain;

import java.util.Random;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Switch extends Terrain implements LandOnInterface {

    public Switch(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    @Override
    public void landOn(Unit u) {
	Direction.EAST.compareTo(Direction.EAST);
	u.getDirection();
	u.setDirection(Direction.getRandomDirection().toString());
	// TODO Auto-generated method stub

    }

    // Test of random
    public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
	    System.out.println(Direction.getRandomDirection());
	}
    }
}

enum Direction {
    NORTH, SOUTH, WEST, EAST;

    public static Direction getRandomDirection() {
	Random random = new Random();
	return values()[random.nextInt(values().length)];
    }

}
