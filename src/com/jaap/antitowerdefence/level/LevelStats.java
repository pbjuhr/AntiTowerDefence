package com.jaap.antitowerdefence.level;

public class LevelStats {

    private int score;
    private int winScore;
    private int credits;
    private int level;

    LevelStats(int winScore, int credits) {
	// Sätter score & credits
	// Sätter score = 0
    }

    // Eventuella variabler för antal döda units, köpta units och units som nått
    // målet
    public void addScore(int score) {

    }

    public int getScore() {
	return score;
    }

    public void addCredits(int score) {

    }

    public int getCredits() {
	return credits;
    }

    public int getWinScore() {
	return winScore;
    }

}
