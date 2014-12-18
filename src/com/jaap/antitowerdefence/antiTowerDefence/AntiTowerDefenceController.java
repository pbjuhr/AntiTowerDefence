package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jaap.antitowerdefence.level.Level;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.*;

/**
 * This class represents the main initializer and controller (communicator)
 * of a game. It is responsible for starting up the game and the GUI and then
 * running the main game loop. It is also responsible for synchronizing the
 * game back end with the GUI front end.
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceController implements PropertyChangeListener {

    private String levelName;		// Name of the level file.
    private boolean paused;		// Pause state of the game.
    private AntiTowerDefenceGame game;	// Game back end.
    private AntiTowerDefenceGUI gui;	// GUI front end.
    private Timer gameTimer;		// Timer for running the game.
    private int stepsPerSec;		// Back end steps per second.
    private int fps;			// Front end frames per second.
    private JButton farmerButton;	// FarmerButton reference.
    private JButton soldierButton;	// SoldierButton reference.
    private JButton wizardButton;	// WizardButton reference.
    private JButton portalButton;	// PortalButton reference.
    private Image[] images;		// Images to draw.
    private boolean firstRun;		// If the constructor is running.

    /**
     * Initializes a new AntiTowerDefenceController, including loading
     * all the images, starting a new game and showing the GUI.
     * @param level
     */
    public AntiTowerDefenceController(String level) {
	loadImages();
	firstRun = true;
	stepsPerSec = 32;
	fps = 64;
	levelName = level;
	paused = false;
	newGame();
	firstRun = false;
	gui.showLosePanel("Anti Tower Defence");
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
	gui.setTowers(game.getLevel().getTowers());
    }

    /**
     * Initializes a new game (and GUI if firstRun).
     */
    private void newGame() {
	stopTime();
	game = new AntiTowerDefenceGame(levelName, stepsPerSec);
	if (firstRun) {
	    try {
		SwingUtilities.invokeAndWait(new Runnable() {
		    @Override
		    public void run() {
			gui = new AntiTowerDefenceGUI(images, fps,
				game.getLevelReader().getXDimension(),
				game.getLevelReader().getYDimension());
			setMenuListeners();
			setNextLevelButtonListener();
		    }
		});
	    } catch (InvocationTargetException | InterruptedException e) {
		System.exit(0);
	    }
	}
	newLevel();
    }

    /**
     * Initializes a new level.
     */
    private void newLevel() {
	stopTime();
	farmerButton = null;
	soldierButton = null;
	wizardButton = null;
	portalButton = null;
	Level level = game.getLevel();
	level.addPropertyChangeListener(this);
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
	    System.exit(0);
	}
    }

    /**
     * Stops all timers.
     */
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
		// Do nothing and hope it works.
	    }
	}
    }

    /**
     * Sets the listeners for the menu items.
     */
    @SuppressWarnings("serial")
    private void setMenuListeners() {
	gui.restart.addActionListener(new ActionListener() {
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

	gui.restartLevel.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		paused = false;
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			stopTime();
			game.restartLevel();
			newLevel();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});

	gui.pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		pauseAction();
	    }
	});
	// Disable <space> activating focused buttons.
	InputMap im = (InputMap)UIManager.get("Button.focusInputMap");
	im.put(KeyStroke.getKeyStroke("pressed SPACE"), "none");
	im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
	// Set <space> as hotKey for pause.
	gui.pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "pause");
	gui.pause.getActionMap().put("pause", new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		pauseAction();
	    }
	});

	gui.quit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});

	gui.help.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showHelpFrame();
	    }
	});

	gui.about.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showAboutFrame();
	    }
	});

	gui.highscore.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		gui.showHighscoreFrame(game.getHighScore());
	    }
	});
    }

    /**
     * Actions to perform when the pause button is activated.
     */
    private void pauseAction() {
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
	    // Stop all timers from within the EDT. 
	    if (gameTimer != null) {
		gameTimer.cancel();
	    }
	    gui.getGameForeground().stop();
	    gui.showPausePanel("Paused");
	}
    }

    /**
     * Sets the buttonPanel buttons for the current level, depending
     * on which unit are available. Adds hotKeys to the buttons.
     */
    @SuppressWarnings("serial")
    private void setButtons() {
	gui.emptyButtons();
	for (String unit : game.getPossibleUnits()) {
	    if (unit.equals("FarmerUnit")) {
	        JButton farmerButton = new JButton("Farmer",
	    	    new ImageIcon(images[19]));
	        farmerButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
			game.createFarmer();
		    }
		});
		farmerButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),
				"farmerButton");
		farmerButton.getActionMap().put("farmerButton",
	        	new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
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
	    	    new ImageIcon(images[20]));
	        soldierButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	        	game.createSoldier();
	            }
	        });
	        soldierButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),
				"soldierButton");
	        soldierButton.getActionMap().put("soldierButton",
	        	new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
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
	    	    new ImageIcon(images[21]));
	        wizardButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	        	game.createTeleporter();
	            }
	        });
	        wizardButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),
				"wizardButton");
	        wizardButton.getActionMap().put("wizardButton",
	        	new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
	        	game.createTeleporter();
	            }
	        });
	        wizardButton.setBackground(Color.LIGHT_GRAY);
	        wizardButton.setEnabled(false);
	        this.wizardButton = wizardButton;
	        gui.addButton(wizardButton);

	        JButton portalButton = new JButton("Portal",
	    	    new ImageIcon(images[13]));
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
	        portalButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0),
				"portalButton");
	        portalButton.getActionMap().put("portalButton",
	        	new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
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

    /**
     * Sets the mouse listeners for observing when switches are clicked. 
     */
    private void setSwitchListeners() {
	for (Terrain t : game.getLevel().getSwitches()) {
	    gui.getGameForeground().addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (!gui.isPaused()) {
			// Within the borders of the switch tile.
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

    /**
     * Sets the Actions to perform when the nextLevelButton is clicked.
     */
    private void setNextLevelButtonListener() {
	gui.getNextLevelButton().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    protected Void doInBackground() throws Exception {
			stopTime();
			game.newLevel();
			newLevel();
			runGame();
			return null;
		    }
		};
		worker.execute();
	    }
	});
    }

    /**
     * The main loop of the game. Loops the game.step() method until the
     * game is lost, won or paused.
     */
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
			    if (game.hasErrors()) {
				gui.showErrorFrame(game.getErrorMessage());
				gui.showLosePanel("Game Error!");
			    }
			}
		    });
		    if (game.hasErrors()) {
			stopTime();
		    }
		}
	    }
	}, 0, 1000/stepsPerSec);
    }

    /**
     * Determines which unit buttons should be enabled this step
     * and sets them accordingly. 
     */
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

    /**
     * Stops the timers and shows the appropriate panels and highscore frames.
     */
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

    /**
     * Stops the timers and shows the appropriate panels and highscore frames.
     */
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

    /**
     * Shows the current highscore frame if no highscore was achieved.
     * Otherwise shows the frame for entering a new highscore.
     */
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
		    gui.showErrorFrame("ERROR 1764.\n"
			    + "Failed to compare sore to current highscore!");
		}
	    }
	} else {
	    gui.showHighscoreFrame(db);
	}
    }

    /**
     * Loads all the necessary images for the GUI.
     */
    private void loadImages() {
	images = new Image[22];
	try {
	    images[0] = ImageIO.read(ResourcesLoader
		    .load("img/DirBig/EAST.png"));
	    images[1] = ImageIO.read(ResourcesLoader
		    .load("img/DirBig/NORTH.png"));
	    images[2] = ImageIO.read(ResourcesLoader
		    .load("img/DirBig/SOUTH.png"));
	    images[3] = ImageIO.read(ResourcesLoader
		    .load("img/DirBig/WEST.png"));

	    images[4] = ImageIO.read(ResourcesLoader
		    .load("img/DirSmall/EAST.png"));
	    images[5] = ImageIO.read(ResourcesLoader
		    .load("img/DirSmall/NORTH.png"));
	    images[6] = ImageIO.read(ResourcesLoader
		    .load("img/DirSmall/SOUTH.png"));
	    images[7] = ImageIO.read(ResourcesLoader
		    .load("img/DirSmall/WEST.png"));

	    images[8] = ImageIO.read(ResourcesLoader.load("img/Start.png"));
	    images[9] = ImageIO.read(ResourcesLoader.load("img/Goal.png"));

	    images[10] = ImageIO.read(ResourcesLoader.load("img/Water.png"));
	    images[11] = ImageIO.read(ResourcesLoader.load("img/Grass.png"));
	    images[12] = ImageIO.read(ResourcesLoader.load("img/Road.png"));

	    images[13] = ImageIO.read(ResourcesLoader.load("img/Portal.png"));
	    images[14] = ImageIO.read(ResourcesLoader
		    .load("img/Portal_start.png"));
	    images[15] = ImageIO.read(ResourcesLoader
		    .load("img/Portal_end.png"));

	    images[16] = ImageIO.read(ResourcesLoader.load("img/Tower.png"));
	    images[17] = ImageIO.read(ResourcesLoader
		    .load("img/Tower_shoot.png"));
	    images[18] = ImageIO.read(ResourcesLoader.load("img/red.png"));

	    images[19] = ImageIO.read(ResourcesLoader
		    .load("img/FarmerUnit.png"));
	    images[20] = ImageIO.read(ResourcesLoader
		    .load("img/SoldierUnit.png"));
	    images[21] = ImageIO.read(ResourcesLoader
		    .load("img/TeleporterUnit.png"));
	} catch (IOException e) {
	    System.exit(0);
	}
    }

}
