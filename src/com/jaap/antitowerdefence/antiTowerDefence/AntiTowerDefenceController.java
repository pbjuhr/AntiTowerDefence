package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.jaap.antitowerdefence.unit.TeleporterUnit;
//TODO errorhandling help about hiScore splashscreens enabling(+portals) levels
public class AntiTowerDefenceController {

    private boolean paused;
    private AntiTowerDefenceGame game;
    private AntiTowerDefenceGUI gui;

    public AntiTowerDefenceController(String level) {
	paused = true;
	game = new AntiTowerDefenceGame(level, 20);
	game.newLevel();
	game.newLevel();
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		@Override
		public void run() {
		    gui = new AntiTowerDefenceGUI(40);
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
		game.newLevel();
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
		JFrame helpFrame = new JFrame("Help");
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.setLocation(100, 100);
		helpFrame.setLayout(new GridLayout(6, 1));

		JLabel farmer = new JLabel();
		helpFrame.add(farmer);
		farmer.setText("Farmer: Basic unit.");

		JLabel soldier = new JLabel();
		helpFrame.add(soldier);
		soldier.setText("Soldier: Advanced unit.");

		JLabel wizard = new JLabel();
		helpFrame.add(wizard);
		wizard.setText("Wizard: Portal placing unit.");

		helpFrame.pack();
		helpFrame.setVisible(true);
	    }
	});

	about.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		JFrame aboutFrame = new JFrame("About");
		aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutFrame.setLocation(100, 100);

		JTextArea textArea = new JTextArea(6, 15);
		aboutFrame.add(textArea);
		textArea.setEditable(false);
		textArea.setText("Anti Tower Defence\n" +
			"\nA game by:\n" +
			"Peter Bjuhr\n" +
			"Andreas Bolzyk\n" +
			"Joakim Sandman\n" +
			"Anna Österlund");

		aboutFrame.pack();
		aboutFrame.setVisible(true);
	    }
	});

	highscore.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		JFrame highscoreFrame = new JFrame("Highscore");
		highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		highscoreFrame.setLocation(100, 100);

		JTextArea textArea = new JTextArea(13, 25);
		highscoreFrame.add(textArea);
		textArea.setEditable(false);
		textArea.setText("Anti Tower Defence Highscore:\n" +
			"\nLVL:  NAME:\n" +
			"6     Peter\n" +
			"5     Andreas\n" +
			"5     Anna\n" +
			"3     Joakim");

		highscoreFrame.pack();
		highscoreFrame.setVisible(true);
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
//				TeleporterUnit wizard = game.createTeleporter();
			    }
			});
			wizardButton.setBackground(Color.LIGHT_GRAY);
			wizardButton.setEnabled(false);
			gui.addButton(wizardButton);

			JButton portalButton = new JButton("Portal", new ImageIcon("assets/img/Portal.png"));
			portalButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent arg0) {
//				wizard.pladePortal();
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

    }

    private void gameWin() {

    }

    private void gamePaused() {

    }

    private void resumeGame() {

    }

    private void newGame() {

    }

}
