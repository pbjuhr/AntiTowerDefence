package antiTowerDefence;

/**
 * @author Peter Bjuhr
 * Short introduction to the program
 */
public class AntiTowerDefence {

    /**
     * @param args, (optional) contain a levels xml-file
     */
    public static void main(String[] args) {
    	String levelFile = "levels.xml";
	if (args.length < 0) {
	    levelFile = args[0];
	}
    	new AntiTowerDefenceController(levelFile);
    }

}
