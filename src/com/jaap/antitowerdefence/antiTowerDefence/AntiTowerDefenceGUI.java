package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceGUI {

    GamePanel gamePanel;

    JPanel buttonPanel;

    JMenuItem restart;
    JMenuItem restartLevel;
    JMenuItem pause;
    JMenuItem quit;

    JMenuItem help;
    JMenuItem about;

    JMenuItem hightscore;

    public AntiTowerDefenceGUI() {
	JFrame frame = new JFrame("Anti Tower Defence");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setBackground(Color.BLACK);
	frame.setSize(640, 480);
	frame.setLocation(640, 250);

	JMenuBar menuBar = buildMenuBar();
	frame.setJMenuBar(menuBar);

	JPanel mainPanel = new JPanel(new BorderLayout(), true);
	frame.add(mainPanel);

	gamePanel = new GamePanel();
	mainPanel.add(gamePanel, BorderLayout.CENTER);

	buttonPanel = new JPanel(); // FlowLayout
	mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	//frame.pack();
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

//    Fönstret å menyn
//    JFrame Highsores
//    LayeredPanel gamePanel
//    JPanel guiElements
//    Knapp playButton
//    Knapp pauseButton
//    setGamePanel()
//    addButton(Knapp k) //Här har knappen och knappens lyssnare redan skapats av kontrollern
//    getGamePanel()
//    getGuiElements()
//    buildLowerPanel()

    // gamePanel class
    // images saved where?
    // listeners
    // help, about, highscore
}
