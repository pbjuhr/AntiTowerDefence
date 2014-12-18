package com.jaap.antitowerdefence.terrain;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
import com.jaap.antitowerdefence.antiTowerDefence.Position;

/**
 * This is a abstract class for all terrain element Contains all method other
 * terrain use
 * 
 * @author id10abk Andreas Bolzyk
 *
 */
public abstract class Terrain {

    protected Position position;
    protected boolean walkable;
    protected boolean buildable;

    /**
     * Constructor for Terrain initialize position
     * 
     * @param position
     *            where the terrain is place
     */
    public Terrain(Position position) {
	this.position = position;
    }

    /**
     * 
     * @return the position of the object
     */
    public Position getPosition() {
	return position;
    }

    /**
     * 
     * @return the objects variable is it is walkable
     */
    public boolean isWalkable() {
	return walkable;
    }

    /**
     * 
     * @return the objects variable is it is buildable
     */
    public boolean isBuildable() {
	return buildable;
    }

    /**
     * Abstract method for switch
     * 
     * @param directions
     *            ArrayList of direction that the switcher can point at
     */
    public void setSwitch(ArrayList<Direction> directions) {
    }

}
