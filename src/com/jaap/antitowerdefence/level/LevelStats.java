package com.jaap.antitowerdefence.level;
/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelStats {

    private int score;
    private int winScore;
    private int credits;

    LevelStats(int winScore, int credits) {
	this.winScore = winScore;
	this.credits = credits;
	score = 0;
    }

    // Eventuella variabler för antal döda units, köpta units och units som nått
    // målet
    public void addScore(int score) {
	this.score += score;
    }

    public int getScore() {
	return score;
    }

    public void addCredits(int score) {
	//Hur r�knas credits?
	//Det h�r �r bara tillf�lligt f�r test.
	credits += (score*10);
    }

    public int getCredits() {
	return credits;
    }

    public int getWinScore() {
	return winScore;
    }

}
