/**
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.ArrayList;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelReader;
import com.jaap.antitowerdefence.unit.SoldierUnit;
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

    public void step() {
	// Towers shoot
	for(Tower t : currentLevel.getTowers()){
	    t.shoot(t.findUnitInRange());
	}
	
	// Moves the units
	for(Unit u : currentLevel.getUnits()){
	    u.action(currentStep);
	}
	currentLevel.updateUnits();
	
	if(towerPlacer.timeToChange(currentStep)){
	    currentLevel.setTowers(towerPlacer.getNewTowers());
	}
	
	currentStep++;
    }
    
    public void createSoldier(){
	Unit s = new SoldierUnit(currentLevel.getWalkableTerrain(), timeStep);
	currentLevel.addUnit(s);
    }

    public void updateStats() {

    }

    public boolean hasWon() {
	return false;
    }

    public boolean hasLost() {
	return false;
    }

    public boolean isHighScore() {
	return false;
    }

    public void setHighScore() {

    }

    public ArrayList<String> getHighScore() {
	return highScore.getScores();
    }

}
