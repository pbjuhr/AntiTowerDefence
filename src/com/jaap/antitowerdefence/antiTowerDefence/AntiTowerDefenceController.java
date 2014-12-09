package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AntiTowerDefenceController {

    private boolean paused;
    private AntiTowerDefenceGame game;
    private AntiTowerDefenceGUI gui;

    public AntiTowerDefenceController(String level) {
	paused = true;
	game = new AntiTowerDefenceGame(level);
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		@Override
		public void run() {
		    gui = new AntiTowerDefenceGUI();
		}
	    });
	} catch (InvocationTargetException | InterruptedException e) {
	    //e.printStackTrace();
	}
	setListeners();
    }

    private void setListeners() {
	JMenuItem pause = gui.getMenuItemPause();
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

	JMenuItem quit = gui.getMenuItemQuit();
	quit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});

	JMenuItem help = gui.getMenuItemHelp();
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

	JMenuItem about = gui.getMenuItemAbout();
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
    }

    private void runGame() {

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
