package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Goal extends Terrain implements LandOnInterface {
   

    public Goal(Position position) {
	super(position);
	walkable = true;
	buildable = false;
    }

    @Override
    public void landOn(Unit u){
	u.setReachedGoal(true);
    }



    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */
}
