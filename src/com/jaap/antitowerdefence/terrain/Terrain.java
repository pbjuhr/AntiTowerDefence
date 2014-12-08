package com.jaap.antitowerdefence.terrain;

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
    
    public boolean isBuildable(){
	return buildable;
    }
    
    public boolean isWalkable(){
	return buildable;
    }
    
}


