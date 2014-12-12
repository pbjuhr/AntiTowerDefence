package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.TeleporterUnit;
import com.jaap.antitowerdefence.unit.Unit;
//TODO errorhandling help about hiScore splashscreens enabling(+portals) levels
public class AntiTowerDefenceController {

    private boolean paused;
    private AntiTowerDefenceGame game;
    private AntiTowerDefenceGUI gui;
//    private ArrayList<Terrain> switches;

    public AntiTowerDefenceController(String level) {
	paused = true;
	game = new AntiTowerDefenceGame(level, 20);
	game.newLevel();
	game.newLevel();
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		@Override
		public void run() {
		    gui = new AntiTowerDefenceGUI(40, game.getLevel().getLevelStats());
		    gui.newLevelGUI(game.getLevel().getPossibleTowerPositions(),
			    game.getLevel().getWalkableTerrain(),
			    game.getLevel().getUnits(),
			    game.getLevel().getTowers());
		}
	    });
	} catch (InvocationTargetException | InterruptedException e) {
	    //e.printStackTrace();
	}
	setMenuListeners();
	setButtons();
	setSwitchListeners();
    }

    private void setMenuListeners() {
	JMenuItem restart = gui.getMenuItemRestart();
	JMenuItem restartLevel = gui.getMenuItemRestartLevel();
	JMenuItem pause = gui.getMenuItemPause();
	JMenuItem quit = gui.getMenuItemQuit();

	JMenuItem help = gui.getMenuItemHelp();
	JMenuItem about = gui.getMenuItemAbout();

	JMenuItem highscore = gui.getMenuItemHighscore();

	restart.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		paused = false;
		newGame();
		runGame();
		if (restart.getText() != "Restart") {
		    restart.setText("Restart");
		    restartLevel.setEnabled(true);
		    pause.setEnabled(true);
		    Component[] buttons = gui.getButtons().getComponents();
		    for (Component b : buttons) {
			b.setEnabled(true);
		    }
		}
	    }
	});

	restartLevel.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		paused = false;
		game.restartLevel();
	    }
	});

	pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (paused) {
		    paused = false;
		    pause.setText("Pause");
		} else {
		    paused = true;
		    pause.setText("Resume");
		}
	    }
	});

	quit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});

	help.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showHelpFrame();
	    }
	});

	about.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showAboutFrame();
	    }
	});

	highscore.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showHighscoreFrame(game.getHighScore());
	    }
	});
    }

    private void setButtons() {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		for (String unit : game.getPossibleUnits()) {
		    if (unit.equals("FarmerUnit")) {
			JButton farmerButton = new JButton("Farmer", new ImageIcon("assets/img/FarmerUnit.png"));
			farmerButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent arg0) {
				game.createFarmer();
			    }
			});
			farmerButton.setBackground(Color.LIGHT_GRAY);
			farmerButton.setEnabled(false);
			gui.addButton(farmerButton);
		    }

		    if (unit.equals("SoldierUnit")) {
			JButton soldierButton = new JButton("Soldier", new ImageIcon("assets/img/SoldierUnit.png"));
			soldierButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent arg0) {
				game.createSoldier();
			    }
			});
			soldierButton.setBackground(Color.LIGHT_GRAY);
			soldierButton.setEnabled(false);
			gui.addButton(soldierButton);
		    }

		    if (unit.equals("TeleporterUnit")) {
			JButton wizardButton = new JButton("Wizard", new ImageIcon("assets/img/TeleporterUnit.png"));
			wizardButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent arg0) {
				game.createTeleporter();
			    }
			});
			wizardButton.setBackground(Color.LIGHT_GRAY);
			wizardButton.setEnabled(false);
			gui.addButton(wizardButton);

			JButton portalButton = new JButton("Portal", new ImageIcon("assets/img/Portal.png"));
			portalButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent arg0) {
				for (Unit u : game.getLevel().getUnits()) {
				    if (u instanceof TeleporterUnit) {
					((TeleporterUnit) u).placePortal();
				    }
				}
			    }
			});
			portalButton.setBackground(Color.LIGHT_GRAY);
			portalButton.setEnabled(false);
			gui.addButton(portalButton);
		    }
		}
	    }
	});
    }

    private void setSwitchListeners() {
	for (Terrain t : game.getLevel().getSwitches()) {
	    gui.getGameForeground().addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if ((e.getX() >= t.getPosition().getX()*32)
			    && (e.getX() < (t.getPosition().getX()+1)*32)
			    && (e.getY() >= t.getPosition().getY()*32)
			    && (e.getY() < (t.getPosition().getY()+1)*32)) {
			((Switch) t).changeDirection();
		    }
		}
	    });
	}
    }

    private void runGame() {
	Timer timer = new Timer(1000/20, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (paused) {
		    gamePaused();
		} else if (game.hasLost()) {
		    gameLost();
		} else if (game.hasWon()) {
		    gameWin();
		} else {
		    game.step();
		}
	    }
	});
	timer.setRepeats(true);
	timer.start();
    }

    private void updateGui() {

    }

    private void gameLost() {
	game.restartLevel();
    }

    private void gameWin() {
	game.newLevel();
	HighScoreDB db = game.getHighScore();
	int score = game.getCurrentLevelNumber();
	String isHighScore = db.isHighScore(score);
	if (isHighScore.equals("true")) {
	    String name = gui.showNewHighscoreFrame(db);
	    db.addScore(name, score);
	} else if (isHighScore.equals("fail")) {
	    
	}
    }

    private void gamePaused() {

    }

    private void resumeGame() {

    }

    private void newGame() {

    }

}
