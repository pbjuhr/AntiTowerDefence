package antiTowerDefence;

public class AntiTowerDefenceController {
    
    private AntiTowerDefenceGUI gui;
    private AntiTowerDefenceGame game;

    public AntiTowerDefenceController(String level) {
	game = new AntiTowerDefenceGame(level);
    }
}
