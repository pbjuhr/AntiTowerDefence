package com.jaap.antitowerdefence.level;

import java.io.File;

import antiTowerDefence.Position;
import terrain.Terrain;

public class LevelReader {

    private File levelFile;
    private int nrOfLevels; // Hur m�nga banor finns det i filen?
    
    public LevelReader(String levelFile) {
	// 1. Skapar xml-l�saren med level-str�ngen
	// 2. Kollar hur m�nga banor det finns och s�tter nrOfLevels
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