package com.jaap.antitowerdefence.level;

import java.io.File;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelReader {

    private File levelFile;
    private int nrOfLevels; // nr of maps in file
    
    public LevelReader(String levelFile) {
	// 1. Skapar xml-lasaren med level-strangen
	// 2. Kollar hur manga banor det finns och sätter nrOfLevels
    }
    
    public Terrain[][] getMap(int currentLevel) {
	return null;
    }
    
    public Position[] getPossibleTowerPositions(int currenLevel) {
	//L�ser av alla gr�spositioner och l�gger till
	return null;
    }
    
    public LevelStats getLevelStats(int currentLevel) {
	return null;
    }
    
    public int getNrOfTowers(int currentLevel) {
	return 1;
    }
   
    public boolean[] hasUnits(int currentLevel){
	boolean[] array = {true, false};
	return array;
    }
    
    public int getNrOfLevels(){
	return nrOfLevels;
    }
}
