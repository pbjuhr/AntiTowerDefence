/**
 * @author Peter Bjuhr
 */

package com.jaap.antitowerdefence.antiTowerDefence;

import java.util.ArrayList;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.level.LevelReader;

public class AntiTowerDefenceGame {

    private int minTimeStep = 1000; // 1 second
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
		theLevelReader.getGrass(currentLevelNumber));
	towerPlacer = new TowerPlacerAI();
	highScore = new HighScoreDB();
    }

    public void newLevel() {
	currentLevelNumber++;
	currentLevel = new Level(theLevelReader.getRoad(currentLevelNumber), 
		theLevelReader.getGrass(currentLevelNumber));
    }

    public void step() {
	/* Kör tidssteg!
	0. Om x tidssteg: Flytta torn (och sätt cooldown)
	1. Om ej cooldown: Tornen ska skjuta
	2. Gubbarna som blir skjutna tar skada
	3. Gubbar som inte har hälsa kvar dör
	4. Gubbarna ska gå (anropa också LandOn)
	5. Kontrollera och uppdatera status i gubbar 
	6. Statistik ska uppdateras
	 */
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
