package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Road extends Terrain {

    public Road(Position position) {
	super(position);
	this.walkable = true;
	this.buildable = true;
    }
    
    public void landOn(Unit u){
	//Do nothing
    }

}
