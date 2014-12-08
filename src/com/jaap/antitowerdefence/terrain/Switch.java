package com.jaap.antitowerdefence.terrain;

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
	// TODO Auto-generated method stub
	
    }

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */
}
