package com.jaap.antitowerdefence.antiTowerDefence;
/**
 * Position.java
 * 
 * A class that contains x and y coordinates for a position in a 2-dim matrix
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class Position {

    private int x;	//The x-coorinate of the position
    private int y;	//The y-coorinate of the position

    /**
     * Constructor that is used when a Position is initiated. Sets the values
     * of the coordinates
     * @param x - the x-coordinate
     * @param y - the y-coordinate
     */
    public Position(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * getX provides the x-coordinate of the position
     * @return the x-coordinate
     */
    public int getX() {
	return x;
    }

    /**
     * getY provides the y-coordinate of the position
     * @return the y-coordinate
     */
    public int getY() {
	return y;
    }

    /**
     * setX sets the x-coordinate of the position
     * @param x - is set as x-coordinate of the position
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     * setY sets the y-coordinate of the position
     * @param y - is set as y-coordinate of the position
     */
    public void setY(int y) {
	this.y = y;
    }

    /**
     * distanceTo calculates the Manhattan/taxi cab distance between two
     * positions
     * @param position - the position to mesure the distance to.
     * @return the distance to the position
     */
    public int distanceTo(Position position) {
	// (avstånd = |x1 - x2| + |y1 - y2|)
	int distance = ((Math.abs(position.getX() - x) + 
		(Math.abs(position.getY() - y))));
	return distance;
    }

    /**
     * getPosToNorth provides the neighbour position north of this position
     * @return - Position to north
     */
    public Position getPosToNorth() {
	Position north = new Position(x, (y - 1));
	return north;
    }

    /**
     * getPosToSouth provides the neighbour position south of this position
     * @return - Position to south
     */
    public Position getPosToSouth() {
	Position south = new Position(x, (y + 1));
	return south;
    }

    /**
     * getPosToWest provides the neighbour position west of this position
     * @return - Position to west
     */
    public Position getPosToWest() {
	Position west = new Position((x - 1), y);
	return west;
    }

    /**
     * getPosToEast provides the neighbour position east of this position
     * @return - Position to east
     */
    public Position getPosToEast() {
	Position east = new Position((x + 1), y);
	return east;
    }
}
