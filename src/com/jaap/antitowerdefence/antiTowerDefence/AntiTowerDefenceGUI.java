package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;
//TODO errorhandling getPanel splashscreens
/**
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceGUI {

    JFrame frame;

    JLayeredPane gamePanels;
    GameBackground gameBackground;
    GameForeground gameForeground;

    JPanel buttonPanel;

    JMenuItem restart;
    JMenuItem restartLevel;
    JMenuItem pause;
    JMenuItem quit;

    JMenuItem help;
    JMenuItem about;

    JMenuItem hightscore;

    public AntiTowerDefenceGUI(int fps, LevelStats stats) {
	frame = new JFrame("Anti Tower Defence");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);

	JMenuBar menuBar = buildMenuBar();
	frame.setJMenuBar(menuBar);

	JPanel mainPanel = new JPanel(new BorderLayout(), true);
	frame.add(mainPanel);

	JLayeredPane gamePanels = new JLayeredPane();
	mainPanel.add(gamePanels, BorderLayout.CENTER);
	gamePanels.setPreferredSize(new Dimension(640, 480));

	gameBackground = new GameBackground();
	gameBackground.setBounds(0, 0, 640, 480);
	gamePanels.add(gameBackground, new Integer(0), 0);

	gameForeground = new GameForeground(fps, stats);
	gameForeground.setBounds(0, 0, 640, 480);
	gamePanels.add(gameForeground, new Integer(1), 0);

	buttonPanel = new JPanel();
	buttonPanel.setBackground(Color.DARK_GRAY);
	mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }

    private JMenuBar buildMenuBar() {
	JMenuBar menuBar = new JMenuBar();

	JMenu gameMenu = new JMenu("Game");
	menuBar.add(gameMenu);
	restart = new JMenuItem("New Game");
	gameMenu.add(restart);
	restartLevel = new JMenuItem("Restart Level");
	restartLevel.setEnabled(false);
	gameMenu.add(restartLevel);
	pause = new JMenuItem("Pause");
	pause.setEnabled(false);
	gameMenu.add(pause);
	quit = new JMenuItem("Quit");
	gameMenu.add(quit);

	JMenu helpMenu = new JMenu("Help");
	menuBar.add(helpMenu);
	help = new JMenuItem("Help");
	helpMenu.add(help);
	about = new JMenuItem("About");
	helpMenu.add(about);

	JMenu highscoreMenu = new JMenu("Highscore");
	menuBar.add(highscoreMenu);
	hightscore = new JMenuItem("Highscore");
	highscoreMenu.add(hightscore);

	return menuBar;
    }

    public JMenuItem getMenuItemRestart() {
	return restart;
    }

    public JMenuItem getMenuItemRestartLevel() {
	return restartLevel;
    }

    public JMenuItem getMenuItemPause() {
	return pause;
    }

    public JMenuItem getMenuItemQuit() {
	return quit;
    }

    public JMenuItem getMenuItemHelp() {
	return help;
    }

    public JMenuItem getMenuItemAbout() {
	return about;
    }

    public JMenuItem getMenuItemHighscore() {
	return hightscore;
    }

    public void addButton(JButton button) {
	buttonPanel.add(button);
	frame.pack();
	frame.setVisible(true);
    }

    public JPanel getButtons() {
	return buttonPanel;
    }

    public GameForeground getGameForeground() {
	return gameForeground;
    }

    public void newLevelGUI(Terrain[] grass, Terrain[] road, ArrayList<Unit> units, Tower[] towers) {
	gameBackground.setTerrain(grass, road);
	gameForeground.setTerrainAndObjects(units, towers, road);
    }

    @SuppressWarnings("resource")
    public void showHelpFrame() {
	JFrame helpFrame = new JFrame("Help");
	helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	helpFrame.setLayout(new GridLayout(7, 1));

	try {
	    JPanel introPanel = new JPanel(new BorderLayout());
	    helpFrame.add(introPanel);
	    JPanel introImagePanel = new JPanel();
	    introPanel.add(introImagePanel, BorderLayout.NORTH);
	    introImagePanel.add(new JLabel(new ImageIcon("assets/img/Start.png")));
	    introImagePanel.add(new JLabel(new ImageIcon("assets/img/Goal.png")));
	    JTextArea introTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Intro.txt")).useDelimiter("\\Z").next());
	    introPanel.add(introTextPanel, BorderLayout.CENTER);
	    introTextPanel.setEditable(false);

	    JPanel farmerPanel = new JPanel(new BorderLayout());
	    helpFrame.add(farmerPanel);
	    JPanel farmerImagePanel = new JPanel();
	    farmerPanel.add(farmerImagePanel, BorderLayout.NORTH);
	    farmerImagePanel.add(new JLabel(new ImageIcon("assets/img/FarmerUnit.png")));
	    JTextArea farmerTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Farmer.txt")).useDelimiter("\\Z").next());
	    farmerPanel.add(farmerTextPanel, BorderLayout.CENTER);
	    farmerTextPanel.setEditable(false);

	    JPanel soldierPanel = new JPanel(new BorderLayout());
	    helpFrame.add(soldierPanel);
	    JPanel soldierImagePanel = new JPanel();
	    soldierPanel.add(soldierImagePanel, BorderLayout.NORTH);
	    soldierImagePanel.add(new JLabel(new ImageIcon("assets/img/SoldierUnit.png")));
	    JTextArea soldierTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Soldier.txt")).useDelimiter("\\Z").next());
	    soldierPanel.add(soldierTextPanel, BorderLayout.CENTER);
	    soldierTextPanel.setEditable(false);

	    JPanel wizardPanel = new JPanel(new BorderLayout());
	    helpFrame.add(wizardPanel);
	    JPanel wizardImagePanel = new JPanel();
	    wizardPanel.add(wizardImagePanel, BorderLayout.NORTH);
	    wizardImagePanel.add(new JLabel(new ImageIcon("assets/img/TeleporterUnit.png")));
	    JTextArea wizardTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Wizard.txt")).useDelimiter("\\Z").next());
	    wizardPanel.add(wizardTextPanel, BorderLayout.CENTER);
	    wizardTextPanel.setEditable(false);

	    JPanel portalPanel = new JPanel(new BorderLayout());
	    helpFrame.add(portalPanel);
	    JPanel portalImagePanel = new JPanel();
	    portalPanel.add(portalImagePanel, BorderLayout.NORTH);
	    portalImagePanel.add(new JLabel(new ImageIcon("assets/img/Portal.png")));
	    portalImagePanel.add(new JLabel(new ImageIcon("assets/img/Portal_start.png")));
	    portalImagePanel.add(new JLabel(new ImageIcon("assets/img/Portal_end.png")));
	    JTextArea portalTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Portal.txt")).useDelimiter("\\Z").next());
	    portalPanel.add(portalTextPanel, BorderLayout.CENTER);
	    portalTextPanel.setEditable(false);

	    JPanel switchPanel = new JPanel(new BorderLayout());
	    helpFrame.add(switchPanel);
	    JPanel switchImagePanel = new JPanel();
	    switchPanel.add(switchImagePanel, BorderLayout.NORTH);
	    switchImagePanel.add(new JLabel(new ImageIcon("assets/img/DirBig/NORTH.png")));
	    switchImagePanel.add(new JLabel(new ImageIcon("assets/img/DirBig/EAST.png")));
	    switchImagePanel.add(new JLabel(new ImageIcon("assets/img/DirBig/SOUTH.png")));
	    switchImagePanel.add(new JLabel(new ImageIcon("assets/img/DirBig/WEST.png")));
	    JTextArea switchTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Switch.txt")).useDelimiter("\\Z").next());
	    switchPanel.add(switchTextPanel, BorderLayout.CENTER);
	    switchTextPanel.setEditable(false);

	    JPanel towerPanel = new JPanel(new BorderLayout());
	    helpFrame.add(towerPanel);
	    JPanel towerImagePanel = new JPanel();
	    towerPanel.add(towerImagePanel, BorderLayout.NORTH);
	    towerImagePanel.add(new JLabel(new ImageIcon("assets/img/Tower.png")));
	    JTextArea towerTextPanel = new JTextArea(new Scanner(new File("assets/gameinfo/Tower.txt")).useDelimiter("\\Z").next());
	    towerPanel.add(towerTextPanel, BorderLayout.CENTER);
	    towerTextPanel.setEditable(false);

	} catch (FileNotFoundException e) {
	    //e.printStackTrace();
	}

	helpFrame.pack();
	helpFrame.setVisible(true);
    }

    @SuppressWarnings("resource")
    public void showAboutFrame() {
	JFrame aboutFrame = new JFrame("About");
	aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	aboutFrame.setResizable(false);

	try {
	    JPanel aboutPanel = new JPanel();
	    aboutFrame.add(aboutPanel);
	    JTextArea textArea = new JTextArea(9, 15);
	    aboutPanel.add(textArea);
	    textArea.setEditable(false);
	    textArea.setText(new Scanner(new File("assets/gameinfo/About.txt")).useDelimiter("\\Z").next());
	} catch (FileNotFoundException e) {
	    //e.printStackTrace();
	}

	aboutFrame.pack();
	aboutFrame.setLocationRelativeTo(null);
	aboutFrame.setVisible(true);
    }

    public void showHighscoreFrame(HighScoreDB db) {
	JFrame highscoreFrame = new JFrame("Highscore");
	highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	highscoreFrame.setResizable(false);

	String scoresAndNames = "Anti Tower Defence Highscore\n\n"
		+ "lvl:  Name:\n\n";

//	String[][] currentHighscore = db.getHighScoreTopTen();
//	if (currentHighscore != null) {
//	    for (String[] s : currentHighscore) {
//		scoresAndNames += s[1] + "   " + s[0] + "\n";
//	    }
//	}

	JPanel highscorePanel = new JPanel();
	highscoreFrame.add(highscorePanel);
	JTextArea textArea = new JTextArea(15, 25);
	highscorePanel.add(textArea);
	textArea.setEditable(false);
	textArea.setText(scoresAndNames);

	highscoreFrame.pack();
	highscoreFrame.setLocationRelativeTo(null);
	highscoreFrame.setVisible(true);
    }

    public String showNewHighscoreFrame(HighScoreDB db) {
	return null;
    }

//    Fönstret å menyn
//    JFrame Highsores
//    LayeredPanel gamePanel
//    JPanel guiElements
//    Knapp playButton
//    Knapp pauseButton
//    setGamePanel()
//    addButton(Knapp k)
    //Här har knappen och knappens lyssnare redan skapats av kontrollern
//    getGamePanel()
//    getGuiElements()
//    buildLowerPanel()

    // gamePanel class
    // images saved where?
    // listeners
    // help, about, highscore
}
