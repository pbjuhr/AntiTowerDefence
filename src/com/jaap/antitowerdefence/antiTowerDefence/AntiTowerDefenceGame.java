package com.jaap.antitowerdefence.antiTowerDefence;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelReader;
import com.jaap.antitowerdefence.unit.FarmerUnit;
import com.jaap.antitowerdefence.unit.SoldierUnit;
import com.jaap.antitowerdefence.unit.TeleporterUnit;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * Game model for Anti tower defence game
 * @author Peter Bjuhr, id10pbn
 */
public class AntiTowerDefenceGame {

    private int currentStep;	 	// How many steps we've taken
    private int currentLevelNumber;	// The current level number
    private int stepsPerSecond;		// Steps per second
    private LevelReader theLevelReader;	// The XML reader
    private Level currentLevel;		// The current level object
    private TowerPlacerAI towerPlacer;	// The towerplacerAI
    private HighScoreDB highScore;	// Highscore object
    
    /**
     * Creates an instance of the AntiTowerDefenceGame model.
     * @param level, the path to the level xml.
     */
    public AntiTowerDefenceGame(String level, int stepsPerSecond){
	this.stepsPerSecond = stepsPerSecond;
	currentStep = 0;
	currentLevelNumber = 1;
	theLevelReader = new LevelReader(level);
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(currentLevel.getPossibleTowerPositions(), 
		stepsPerSecond, theLevelReader.getNrOfTowers(currentLevelNumber));
	highScore = new HighScoreDB();
    }
    
    /**
     * Resets necessary variables and sets new level & towerplacer objects
     */
    public void newLevel() {
	currentLevelNumber++;
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(currentLevel.getPossibleTowerPositions(), 
		stepsPerSecond, theLevelReader.getNrOfTowers(currentLevelNumber));
    }

    /**
     * Runs one timestep in the game
     */
    public void step() {
	
	// Towers shoot
	for(Tower t : currentLevel.getTowers()){
	    t.action();
	}
		
	// Moves the units
	for(Unit u : currentLevel.getUnits()){
	    u.action(currentStep);
	}
	currentLevel.updateUnits();

	// Moves the towers
	if(towerPlacer.timeToChange(currentStep)){
	    currentLevel.setTowers(towerPlacer.getNewTowers());
	}

	currentStep++;
    }
    
    /**
     * Creates a farmer unit and adds it to the level
     */
    public void createFarmer(){
	Unit u = new FarmerUnit(currentLevel.getWalkableTerrain(), currentStep, 
		stepsPerSecond);
	currentLevel.addUnit(u);
    }
    
    /**
     * Creates a soldier unit and adds it to the level
     */
    public void createSoldier(){
   	Unit u = new SoldierUnit(currentLevel.getWalkableTerrain(), currentStep, 
   		stepsPerSecond);
   	currentLevel.addUnit(u);
    }
    
    /**
     * Creates a teleporter unit and adds it to the level
     */
    public void createTeleporter(){
   	Unit u = new TeleporterUnit(currentLevel.getWalkableTerrain(), 
   		currentStep, stepsPerSecond);
   	currentLevel.addUnit(u);
    }

    /**
     * Determines if the player has won the current level
     * @return true if the player has won, otherwise false
     */
    public boolean hasWon() {
	int currentScore =  currentLevel.getLevelStats().getScore();
	int winScore = currentLevel.getLevelStats().getWinScore();
	return currentScore >= winScore;
    }
    
    /**
     * Determines if the player has lost the current level
     * @return true if the player has lost, otherwise false
     */
    public boolean hasLost() {
	int currentScore =  currentLevel.getLevelStats().getScore();
	int winScore = currentLevel.getLevelStats().getWinScore();
	int credits = currentLevel.getLevelStats().getCredits();
	int nrOfUnits = currentLevel.getUnits().size();
	int farmerCost = 50;
	if(nrOfUnits == 0 && credits < farmerCost && currentScore < winScore) {
	    return true;
	}
	return false;
    }
    
    /**
     * Gets the HighScoreDB object
     * @return highScore
     */
    public HighScoreDB getHighScore(int score) {
	return highScore;
    }
    
    /**
     * Gets the current Level object
     * @return currentLevel, the level object
     */
    public Level getLevel(){
	return currentLevel;
    }
    
    /**
     * Gets a string array of possible units for the current level
     * @return String[], the array with units
     */
    public String[] getPossibleUnits(){
	return theLevelReader.getUnits(currentLevelNumber);
    }

}
