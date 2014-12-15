package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.TeleporterUnit;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceController {

    private String levelName;
    private boolean paused;
    private AntiTowerDefenceGame game;
    private AntiTowerDefenceGUI gui;
    private Timer gameTimer;
    private int stepsPerSec;
    private int fps;
    private JButton wizardButton;
    private JButton portalButton;
    private boolean firstRun;

    public AntiTowerDefenceController(String level) {
	firstRun = true;
	stepsPerSec = 20;
	fps = 40;
	levelName = level;
	paused = false;
	newGame();
	gui.showLosePanel("Anti Tower Defence");
    }

    private void newGame() {
	stopTime();
	game = new AntiTowerDefenceGame(levelName, stepsPerSec);
	game.newLevel();
	game.newLevel();
	if (firstRun) {
	    try {
		SwingUtilities.invokeAndWait(new Runnable() {
		    @Override
		    public void run() {
			gui = new AntiTowerDefenceGUI(fps,
				game.getLevelReader().getXDimension(),
				game.getLevelReader().getYDimension());
			setMenuListeners();
			setNextLevelButtonListener();
		    }
		});
	    } catch (InvocationTargetException | InterruptedException e) {
		//e.printStackTrace();
	    }
	}
	newLevelGUI();
    }

    private void newLevelGUI() {
	stopTime();
	firstRun = false;
	wizardButton = null;
	portalButton = null;
	Level level = game.getLevel();
	gui.newLevelGUI(level.getPossibleTowerPositions(),
		level.getWalkableTerrain(), level.getUnits(),
		level.getTowers(), level.getLevelStats());
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		@Override
		public void run() {
		    setSwitchListeners();
		    setButtons();
		}
	    });
	} catch (InvocationTargetException | InterruptedException e) {
	    //e.printStackTrace();
	}
    }

    private void stopTime() {
	if (!firstRun) {
	    if (gameTimer != null) {
		gameTimer.cancel();
	    }
	    try {
		SwingUtilities.invokeAndWait(new Runnable() {
		    @Override
		    public void run() {
			gui.getGameForeground().stop();
		    }
		});
	    } catch (InvocationTargetException | InterruptedException e) {
		//e.printStackTrace();
	    }
	}
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
		pause.setText("Pause");
		if (restart.getText() != "Restart") {
		    restart.setText("Restart");
		    restartLevel.setEnabled(true);
		    pause.setEnabled(true);
		}
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			newGame();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});

	restartLevel.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		paused = false;
		pause.setText("Pause");
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			stopTime();
			game.restartLevel();
			newLevelGUI();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});

	pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (paused) {
		    paused = false;
		    pause.setText("Pause");
		    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
			    runGame();
			    return null;
			}
		    };
		    worker.execute();
		} else {
		    paused = true;
		    pause.setText("Resume");
		    if (gameTimer != null) {
			gameTimer.cancel();
		    }
		    Component[] buttons = gui.getButtons().getComponents();
		    for (Component b : buttons) {
			b.setEnabled(false);
		    }
		    gui.showPausePanel("Paused");
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
	gui.emptyButtons();
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
		this.wizardButton = wizardButton;
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
		this.portalButton = portalButton;
		gui.addButton(portalButton);
	    }
	}
    }

    private void setSwitchListeners() {
	for (Terrain t : game.getLevel().getSwitches()) {
	    gui.getGameForeground().addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (!gui.isPaused()) {
			if ((e.getX() >= t.getPosition().getX()*32)
				&& (e.getX() < (t.getPosition().getX()+1)*32)
				&& (e.getY() >= t.getPosition().getY()*32)
				&& (e.getY() < (t.getPosition().getY()+1)*32)) {
			    ((Switch) t).changeDirection();
			}
		    }
		}
	    });
	}
    }

    private void setNextLevelButtonListener() {
	gui.getNextLevelButton().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			stopTime();
			game.newLevel();
			newLevelGUI();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});
    }

    private void runGame() {
	gui.getGameForeground().start();
	gui.hidePausePanel();
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		Component[] buttons = gui.getButtons().getComponents();
		for (Component b : buttons) {
		    b.setEnabled(true);
		}
	    }
	});
	gameTimer = new Timer();
	gameTimer.schedule(new TimerTask() {
	    @Override
	    public void run() {
		if (game.hasLost()) {
		    gameLost();
		} else if (game.hasWon()) {
		    gameWon();
		} else {
		    game.step();
		    boolean teleporterActive = false;
		    for (Unit u : game.getLevel().getUnits()) {
			if (u instanceof TeleporterUnit) {
			    wizardButton.setEnabled(false);
			    if (((TeleporterUnit) u).getPlacedPortals() < 2) {
				portalButton.setEnabled(true);
			    } else {
				portalButton.setEnabled(false);
			    }
			    teleporterActive = true;
			    break;
			}
		    }
		    if (!teleporterActive && (wizardButton != null) && (portalButton != null)) {
			wizardButton.setEnabled(true);
			portalButton.setEnabled(false);
		    }
		}
	    }
	}, 0, 1000/stepsPerSec);
    }

    private void gameLost() {
	stopTime();
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		gui.showLosePanel("Game Over!");
		handlePossibleHighscore();
	    }
	});
    }

    private void gameWon() {
	stopTime();
	if (game.getCurrentLevelNumber() < game.getLevelReader().getNrOfLevels()) {
	    SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		    gui.showWinPanel("Level Complete!");
		}
	    });
	} else {
	    SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		    gui.showLosePanel("Game Completed!");
		    handlePossibleHighscore();
		}
	    });
	}
    }

    private void handlePossibleHighscore() {
	HighScoreDB db = game.getHighScore();
	if (db.connectToDB()) {
	    int score = game.getCurrentLevelNumber();
	    String isHighScore = db.isHighScore(score);
	    if (isHighScore.equals("true")) {
		gui.showNewHighscoreFrame(db, score);
	    } else if (isHighScore.equals("fail")) {
		//ERROR
	    }
	} else {
	    //ERROR
	}
    }

}
