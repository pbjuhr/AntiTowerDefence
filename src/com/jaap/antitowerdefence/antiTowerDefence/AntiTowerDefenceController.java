package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.*;

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
    private JButton farmerButton;
    private JButton soldierButton;
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
	updateGUI();
    }

    private void updateGUI() {
	stopTime();
	firstRun = false;
	farmerButton = null;
	soldierButton = null;
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
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			stopTime();
			game.restartLevel();
			updateGUI();
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
		    if (gameTimer != null) {
			gameTimer.cancel();
		    }
		    gui.getGameForeground().stop();
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
	    try {
		if (unit.equals("FarmerUnit")) {
		    JButton farmerButton = new JButton("Farmer",
			    new ImageIcon(ImageIO.read(ResourcesLoader.load("img/FarmerUnit.png"))));
		    farmerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    game.createFarmer();
			}
		    });
		    farmerButton.setBackground(Color.LIGHT_GRAY);
		    farmerButton.setEnabled(false);
		    this.farmerButton = farmerButton;
		    gui.addButton(farmerButton);
		}

		if (unit.equals("SoldierUnit")) {
		    JButton soldierButton = new JButton("Soldier",
			    new ImageIcon(ImageIO.read(ResourcesLoader.load("img/SoldierUnit.png"))));
		    soldierButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    game.createSoldier();
			}
		    });
		    soldierButton.setBackground(Color.LIGHT_GRAY);
		    soldierButton.setEnabled(false);
		    this.soldierButton = soldierButton;
		    gui.addButton(soldierButton);
		}

		if (unit.equals("TeleporterUnit")) {
		    JButton wizardButton = new JButton("Wizard",
			    new ImageIcon(ImageIO.read(ResourcesLoader.load("img/TeleporterUnit.png"))));
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

		    JButton portalButton = new JButton("Portal",
			    new ImageIcon(ImageIO.read(ResourcesLoader.load("img/Portal.png"))));
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
	    } catch (IOException e) {
		//e.printStackTrace();
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
			updateGUI();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});
    }

    private void runGame() {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		gui.getGameForeground().start();
		gui.hidePausePanel();
		enableButtons();

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
		    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			    enableButtons();
			    gui.setTowers(game.getLevel().getTowers());
			}
		    });
		}
	    }
	}, 0, 1000/stepsPerSec);
    }

    private void enableButtons() {
	int currentCredits = game.getLevel().getLevelStats().getCredits();
	if (wizardButton != null) {
	    boolean teleporterActive = false;
	    for (Unit u : game.getLevel().getUnits()) {
		if (u instanceof TeleporterUnit) {
		    teleporterActive = true;
		    if (((TeleporterUnit) u).getPlacedPortals() < 2) {
			portalButton.setEnabled(true);
		    } else {
			portalButton.setEnabled(false);
		    }
		    break;
		}
	    }
	    if (teleporterActive || (TeleporterUnit.cost > currentCredits)) {
		wizardButton.setEnabled(false);
	    } else {
		wizardButton.setEnabled(true);
	    }
	}
	if (soldierButton != null) {
	    if (SoldierUnit.cost > currentCredits) {
		soldierButton.setEnabled(false);
	    } else {
		soldierButton.setEnabled(true);
	    }
	}
	if (farmerButton != null) {
	    if (FarmerUnit.cost > currentCredits) {
		farmerButton.setEnabled(false);
	    } else {
		farmerButton.setEnabled(true);
	    }
	}
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
	    if (score > 0) {
		String isHighScore = db.isHighScore(score);
		if (isHighScore.equals("true")) {
		    gui.showNewHighscoreFrame(db, score);
		} else if (isHighScore.equals("false")) {
		    gui.showHighscoreFrame(db);
		} else {
		    //ERROR
		}
	    }
	} else {
	    //ERROR
	}
    }

}
