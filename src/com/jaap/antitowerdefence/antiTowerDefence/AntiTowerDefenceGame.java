/**
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.antiTowerDefence;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelReader;
import com.jaap.antitowerdefence.unit.FarmerUnit;
import com.jaap.antitowerdefence.unit.SoldierUnit;
import com.jaap.antitowerdefence.unit.TeleporterUnit;
import com.jaap.antitowerdefence.unit.Unit;

public class AntiTowerDefenceGame {

    private int timeStep = 1000; // 1 second
    private int currentStep;
    private int currentLevelNumber;
    private int fps;
    private LevelReader theLevelReader;
    private Level currentLevel;
    private TowerPlacerAI towerPlacer;
    private HighScoreDB highScore;

    public AntiTowerDefenceGame(String level){
	fps = 30;
	currentStep = 0;
	currentLevelNumber = 1;
	theLevelReader = new LevelReader(level);
	System.out.println(level);
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(currentLevel.getPossibleTowerPositions(), 
		fps, theLevelReader.getNrOfTowers(currentLevelNumber));
	highScore = new HighScoreDB();
    }

    public void newLevel() {
	currentLevelNumber++;
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(currentLevel.getPossibleTowerPositions(), 
		fps, theLevelReader.getNrOfTowers(currentLevelNumber));
    }

    /**
     * One timestep in the game
     */
    public void step() {
	// Moves the units
	for(Unit u : currentLevel.getUnits()){
	    u.action(currentStep);
	}
	currentLevel.updateUnits();

	// Towers shoot
	for(Tower t : currentLevel.getTowers()){
	    t.shoot(t.findUnitInRange());
	}

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
	Unit u = new FarmerUnit(currentLevel.getWalkableTerrain(), timeStep, 
		fps);
	currentLevel.addUnit(u);
    }
    
    /**
     * Creates a soldier unit and adds it to the level
     */
    public void createSoldier(){
   	Unit u = new SoldierUnit(currentLevel.getWalkableTerrain(), timeStep, 
   		fps);
   	currentLevel.addUnit(u);
    }
    
    /**
     * Creates a teleporter unit and adds it to the level
     */
    public void createTeleporter(){
   	Unit u = new TeleporterUnit(currentLevel.getWalkableTerrain(), 
   		timeStep, fps);
   	currentLevel.addUnit(u);
    }


    public void updateStats() {

    }

    public boolean hasWon() {
	return false;
    }

    public boolean hasLost() {
	return false;
    }

    public boolean isHighScore(score) {
	highScore.isHighScore(score);
    }

    public void setHighScore(String name, int score) {
	highScore.addScore(name, score);
    }

    public String[][] getHighScore() {
	return highScore.getHighScoreTopTen();
    }

}
