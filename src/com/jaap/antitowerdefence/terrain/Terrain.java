package com.jaap.antitowerdefence.terrain;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
import com.jaap.antitowerdefence.antiTowerDefence.Position;

public abstract class Terrain {
    private Position position;
    private boolean buildable;
    private boolean walkable;

    public Terrain(Position position, boolean buildable, boolean walkable) {
	this.position = position;
	this.buildable = buildable;
	this.walkable = walkable;
    }

    public Position getPosition() {
	return position;
    }

    public boolean isBuildable() {
	return buildable;
    }

    public boolean isWalkable() {
	return walkable;
    }

}


