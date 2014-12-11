package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Terrain {

    protected Position position;
    protected boolean walkable;
    protected boolean buildable;

    public Terrain(Position position) {
	this.position = position;
    }

    public Position getPosition() {
	return position;
    }

    public boolean isWalkable() {
	return walkable;
    }

    public boolean isBuildable() {
	return buildable;
    }

    public void setSwitch(ArrayList<Direction> directions) {
    }

}
