package com.jaap.antitowerdefence.antiTowerDefence;

/**
 * @author Peter Bjuhr, Anna Österlund, Joakim Sandman, Andreas Bolzyk
 * Anti tower defence is a game wher units.......
 */
public class AntiTowerDefence {

    /**
     * @param args, (optional) contain a levels xml-file
     */
    public static void main(String[] args) {
    	String levelFile = "assets/levels/levels.xml";
	if (args.length > 0) {
	    levelFile = args[0];
	}
    	new AntiTowerDefenceController(levelFile);
    }

}
