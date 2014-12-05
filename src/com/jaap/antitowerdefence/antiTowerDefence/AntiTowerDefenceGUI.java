package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceGUI {

    JPanel gamePanel;

    JPanel buttonPanel;

    public AntiTowerDefenceGUI() {
	JFrame frame = new JFrame("Anti Tower Defence"); // Name?
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setBackground(Color.BLACK);
	frame.setSize(640, 480);
	frame.setLocation(640, 250);

	JMenuBar menuBar = buildMenuBar();
	frame.setJMenuBar(menuBar);

	JPanel mainPanel = new JPanel(new BorderLayout(), true);
	frame.add(mainPanel);

	gamePanel = new JPanel();
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
	JMenuItem restart = new JMenuItem("New Game");
	gameMenu.add(restart);
	restart.addActionListener(new ActionListener() { // Just toggle for now
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (restart.getText() == "New Game") {
		    restart.setText("Restart");
		} else {
		    restart.setText("New Game");
		}
	    }
	});
	JMenuItem restartLevel = new JMenuItem("Restart Level");
	gameMenu.add(restartLevel);
	JMenuItem pause = new JMenuItem("Pause");
	gameMenu.add(pause);
	pause.addActionListener(new ActionListener() { // Just toggle for now
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (pause.getText() == "Pause") {
		    pause.setText("Resume");
		} else {
		    pause.setText("Pause");
		}
	    }
	});
	JMenuItem quit = new JMenuItem("Quit");
	gameMenu.add(quit);

	JMenu helpMenu = new JMenu("Help");
	menuBar.add(helpMenu);
	JMenuItem help = new JMenuItem("Help");
	helpMenu.add(help);
	JMenuItem about = new JMenuItem("About");
	helpMenu.add(about);

	return menuBar;
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

}
