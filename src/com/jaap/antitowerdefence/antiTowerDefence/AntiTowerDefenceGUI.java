package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	frame.setLocation(640, 250);
	//frame.setPreferredSize(new Dimension(656, 593));

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
