package com.jaap.antitowerdefence.level;
/**
 * LevelStats.java
 * 
 * Class that keeps track of information about an Anti Tower Defence Game 
 * level. Set up values and current game statistics.
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelStats {

    private int score;
    private int winScore;
    private int credits;

    /**
     * Constructor for LevelStats. Initiates and assigns values to the 
     * attributes score, winScore and credits. 
     * @param winScore - the amount of score needed to win the level
     * @param credits - start credits
     */
    public LevelStats(int winScore, int credits) {
	this.winScore = winScore;
	this.credits = credits;
	score = 0;
    }

    /**
     * addScore adds score to the players current score
     * @param score - score to be added to the current score.
     */
    public void addScore(int score) {
	this.score += score;
    }

    /**
     * getScore provides the players current score 
     * @return current score
     */
    public int getScore() {
	return score;
    }

    /**
     * addCredits increses the players amount of credits by muliplying score 
     * with 10
     * @param score - number of units that has reached the goal
     */
    public void addCredits(int score) {
	//TODO!!
	//Hur räknas credits?
	//Det här är bara tillfälligt för test.
	credits += (score*10);
    }

    /**
     * getCredits provides the players current amount of credits
     * @return - current credit
     */
    public int getCredits() {
	return credits;
    }

    /**
     * getWinScore provides the score needed to win the level
     * @return - score needed to win level
     */
    public int getWinScore() {
	return winScore;
    }

}
