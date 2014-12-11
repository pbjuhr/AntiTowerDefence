package com.jaap.antitowerdefence.level;

import java.util.ArrayList;

import com.jaap.antitowerdefence.antiTowerDefence.Direction;
import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.antiTowerDefence.Tower;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;
/**
 * Level.java
 * 
 * A class that contains all of the components of a Anti Tower Defence level. It
 * keeps track of active units, towers, terrain, level info and statistics.
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class Level {

    /*Variables*/
    private Terrain[] road;		//Terrain elements units can walk on
    private Terrain[] grass;	//Possible tower position
    private Tower[] towers;		//Towers implemented in level
    private ArrayList<Unit> units;	//Units currently active in game
    private LevelStats levelStats;	//Level info and game statistics

    /**
     * Contructor for Level saves the walkable terrain for the units, the 
     * terrain where towers can be built and the level info of the level. The 
     * unit arraylist is initiated.
     * 
     * @param walkable - The walkable terrain in the level
     * @param buildable - The terrain where towers can be built in the level
     * @param levelStats - Level info and game statistics
     */
    public Level(Terrain[] walkable, Terrain[] buildable, 
	    LevelStats levelStats) {
	this.road = walkable;
	this.grass = buildable;
	this.levelStats = levelStats;
	setSwitches();
	units = new ArrayList<Unit>();
    }

    /**
     * getWalkableTerrain provides the walkable terrain elements for the units
     * @return - Terrain array of Road
     */
    public Terrain[] getWalkableTerrain() {
	return road;
    }

    /**
     * getPossibleTowerPositions provides the terrain elements where towers 
     * can be built
     * @return - array of possible tower build positions
     */
    public Terrain[] getPossibleTowerPositions() {
	return grass;
    }

    /**
     * addUnit adds a unit to the ArrayList of units. The cost of the Unit is
     * decrease from the players credit 
     * @param unit - The unit to add to the ArrayList of active units
     */
    public void addUnit(Unit unit) {
	units.add(unit);	
	levelStats.decreaseCredits(unit.getCost());
    }

    /**
     * getUnits provides the ArrayList of active units
     * @return
     */
    public ArrayList<Unit> getUnits() {
	return units;
    }

    /**
     * setTowers replaces the tower array with a new array of towers and
     * gives the new towers access to the ArrayList of units
     * @param towers - the tower array to replace the old tower array
     */
    public void setTowers(Tower[] towers) {
	this.towers = towers;
	for(int i = 0; i < towers.length; i++){
	    towers[i].setUnits(units);
	}
    }

    /**
     * getTowers provides the towers in the level
     * @return - array of towers in level
     */
    public Tower[] getTowers() {
	return towers;
    }

    /**
     * getLevelStats provides the levelStats instance of the level
     * @return - the LevelStats object of the level
     */
    public LevelStats getLevelStats() {
	return levelStats;
    }

    /**
     * updateUnits checks if any of the units has reached goal or died. If a
     * units has died it is removed from the list of active units. If a unit
     * has reached goal a score is added to the players total score and the 
     * players credits is increased. After this the unit is removed from the 
     * list of active units.
     */
    public void updateUnits() {
	for(int i = 0; i < units.size(); i++) {
	    if(units.get(i).hasReachedGoal()){
		unitReachedGoal(units.get(i));
		units.remove(i);
	    }else if(!units.get(i).isAlive()) {
		units.remove(i);
	    } 
	}
    }

    /**
     * unitReachedGoal calculates the credits earned and adds this to the 
     * players current credits. It the player also recieves a score of 10 plus
     * 10% of the units health
     * @param unit - farmer, soldier or wizard
     */
    private void unitReachedGoal(Unit unit) {
	double healthCredits = (0.5 * unit.getHealth());
	double costCredits = (0.8 * unit.getCost());
	int totalCredits = (int)Math.round((healthCredits + costCredits));
	int score = (int)Math.round((unit.getHealth()/10) + 10);
	levelStats.addCredits(totalCredits);
	levelStats.addScore(score);
    }

    /**
     * setSwitches loops through the road array and checks for intersections.
     * If an intersection is found a road switch is created and added to the
     * Terrain array of road. The switch is informed about which directions it
     * can redirect units.
     */
    private void setSwitches() {
	int neighbours;
	ArrayList<Direction> directions;
	for(int i = 0; i < road.length; i++) {
	    directions = new ArrayList<Direction>();
	    neighbours = 0;
	    if(roadContains(road[i].getPosition().getPosToEast())) {
		directions.add(Direction.EAST);
		neighbours++;
	    }
	    if(roadContains(road[i].getPosition().getPosToSouth())) {
		directions.add(Direction.SOUTH);
		neighbours++;
	    }
	    if(roadContains(road[i].getPosition().getPosToNorth())) {
		directions.add(Direction.NORTH);
	    }
	    if(roadContains(road[i].getPosition().getPosToWest())) {
		directions.add(Direction.WEST);
	    }
	    
	    if(road[i] instanceof Start){
		Start s = (Start) road[i];
		s.setSwitch(directions);
	    }else if(neighbours > 2){
		road[i] = new Switch(road[i].getPosition(), directions);		
	    }
	}
    }

    /**
     * roadPosition check if a Terrain element of Road exists on the given
     * position
     * @param roadPosition - The position to check
     * @return true if there is road on the position, false if not.
     */
    private boolean roadContains(Position roadPosition) {

	for(Terrain t : road) {
	    if(t.getPosition().equals(roadPosition));
	    return true;
	}
	return false;
    }
}