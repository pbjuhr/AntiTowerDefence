package com.jaap.antitowerdefence.antiTowerDefence;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
    
    private boolean hasError;		// Has the game any errors?
    private String errorMessage;	// Error message
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
	hasError = false;
	currentStep = 0;
	currentLevelNumber = 0;
	try {
	    theLevelReader = new LevelReader(level);
	} catch (ParserConfigurationException e) {
	    setError(e.toString());
	} catch (SAXException e) {
	    setError(e.toString());
	} catch (IOException e) {
	    setError(e.toString());
	}
	highScore = new HighScoreDB();
	newLevel();
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
		theLevelReader.getNrOfTowers(currentLevelNumber), stepsPerSecond);
	currentLevel.setTowers(towerPlacer.getNewTowers());
    }
    
    /**
     * Resets necessary variables and sets new level & towerplacer objects
     */
    public void restartLevel() {
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(currentLevel.getPossibleTowerPositions(), 
		stepsPerSecond, theLevelReader.getNrOfTowers(currentLevelNumber));
	currentLevel.setTowers(towerPlacer.getNewTowers());
    }

    /**
     * Runs one timestep in the game
     */
    public void step() {
	
	// Moves the towers
	if(towerPlacer.timeToChange(currentStep)){
	    currentLevel.setTowers(towerPlacer.getNewTowers());
	}
	
	// Towers shoot
	for(Tower t : currentLevel.getTowers()){
	    t.action();
	}
		
	// Moves the units
	for(Unit u : currentLevel.getUnits()){
	    if(u!=null){
		u.action();
	    }
	}
	currentLevel.updateUnits();

	currentStep++;
    }
    
    /**
     * Creates a farmer unit and adds it to the level
     */
    public void createFarmer(){
	if(canAfford(FarmerUnit.cost)) {
	    Unit u = new FarmerUnit(currentLevel.getWalkableTerrain(), 
		    stepsPerSecond);
	    currentLevel.addUnit(u);
	    currentLevel.getLevelStats().decreaseCredits(FarmerUnit.cost);
	}
    }

    /**
     * Creates a soldier unit and adds it to the level
     */
    public void createSoldier(){
	if(canAfford(SoldierUnit.cost)) {
	    Unit u = new SoldierUnit(currentLevel.getWalkableTerrain(), 
		    stepsPerSecond);
	    currentLevel.addUnit(u);
	    currentLevel.getLevelStats().decreaseCredits(SoldierUnit.cost);
	}
    }

    /**
     * Creates a teleporter unit and adds it to the level
     */
    public TeleporterUnit createTeleporter(){
	if(canAfford(TeleporterUnit.cost) && !containsTeleporter()) {
	    Unit u = new TeleporterUnit(currentLevel.getWalkableTerrain(), 
		    stepsPerSecond);
	    currentLevel.addUnit(u);
	    currentLevel.getLevelStats().decreaseCredits(TeleporterUnit.cost);
	    return (TeleporterUnit)u;
	}
	return null;
    }
    
    /**
     * Check if a Teleporter unit is active in the game
     * @return true or false
     */
    private boolean containsTeleporter() {
	for(Unit u : currentLevel.getUnits()) {
	    if(u instanceof TeleporterUnit) {
		return true;
	    }
	}
	return false;
    }
    
    /**
     * Check if the
     * @param price
     * @return
     */
    public boolean canAfford(int price) {
	return (price <= currentLevel.getLevelStats().getCredits());
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
    public Level getLevel() {
	return currentLevel;
    }
    
    /**
     * Gets a string array of possible units for the current level
     * @return String[], the array with units
     */
    public String[] getPossibleUnits() {
	return theLevelReader.getUnits(currentLevelNumber);
    }
    
    /**
     * 
     */
    public void setError(String message) {
	hasError = true;
	errorMessage = message;
    }
    
    /**
     * Return if the game has generated any errors
     * @return hasError, true if an error has been generated, otherwise false
     */
    private boolean hasErrors() {
	return hasError;
    }
    
    /**
     * Gets the error message
     * @return errorMessage, a string message
     */
    public String getErrorMessage() {
	hasError = false;
	return errorMessage;
    }

}
