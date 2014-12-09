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
    private LevelReader theLevelReader;
    private Level currentLevel;
    private TowerPlacerAI towerPlacer;
    private HighScoreDB highScore;

    public AntiTowerDefenceGame(String level){
	currentStep = 0;
	currentLevelNumber = 1;
	theLevelReader = new LevelReader(level);
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber), 
		theLevelReader.getLevelStats(currentLevelNumber));
	towerPlacer = new TowerPlacerAI(timeStep);
	highScore = new HighScoreDB();
    }

    public void newLevel() {
	currentLevelNumber++;
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber));
    }

    public void step() {
	// Anropa level.updateUnits()
    }
    
    public void createSoldier(){
	Unit s = new SoldierUnit(currentLevel.getWalkable(), timeStep);
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
