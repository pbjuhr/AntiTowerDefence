package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public class Road extends Terrain {

    public Road(Position position, boolean buildable, boolean walkable) {
	super(position, buildable, walkable);
    }

    @Override
    public boolean isWalkable() {
	// TODO Auto-generated method stub
	return super.isWalkable();
    }

    @Override
    public boolean isBuildable() {
	// TODO Auto-generated method stub
	return super.isBuildable();
    }
}
