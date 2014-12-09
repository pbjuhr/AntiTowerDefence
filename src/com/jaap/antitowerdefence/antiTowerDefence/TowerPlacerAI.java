package com.jaap.antitowerdefence.antiTowerDefence;

public class TowerPlacerAI {
    
    private int timeStep;
    
    public TowerPlacerAI(int timeStep) {
	int updateInterval = 20;
	this.timeStep = timeStep * updateInterval;
    }
}
