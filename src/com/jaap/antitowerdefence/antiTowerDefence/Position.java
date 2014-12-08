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

    private int x;
    private int y;

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
}
