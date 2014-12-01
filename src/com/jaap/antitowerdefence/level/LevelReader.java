package com.jaap.antitowerdefence.level;

import java.io.File;

import antiTowerDefence.Position;
import terrain.Terrain;

public class LevelReader {

    private File levelFile;
    private int nrOfLevels; // Hur många banor finns det i filen?
    
    public LevelReader(String levelFile) {
	// 1. Skapar xml-läsaren med level-strängen
	// 2. Kollar hur många banor det finns och sätter nrOfLevels
    }
    
    public Terrain[][] getMap(int currentLevel) {
	
    }
    
    public Position[] getPossibleTowerPositions(int currenLevel) {
	
    }
    
    public LevelStats getLevelStats(int currentLevel) {
	
    }
    
    public int getNrOfTowers(int currentLevel) {

    }
    
    public int getNrOfLevels(){
	return nrOfLevels;
    }
}

/*
getNrOfTowers():int

*/