package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;
import javax.swing.text.*;

import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class AntiTowerDefenceGUI {

    private JFrame frame;
//    private int width;
    private int height;
    private Image[] images;

//    private JLayeredPane gamePanels;
    private GameBackground gameBackground;
    private GameForeground gameForeground;
    private JComponent cover;
    private JComponent coverTitel;
    private JComponent options;
    private JLabel titelText;
    private JButton nextLevelButton;

    private JPanel buttonPanel;

    private JMenuItem restart;
    private JMenuItem restartLevel;
    private JMenuItem pause;
    private JMenuItem quit;

    private JMenuItem help;
    private JMenuItem about;

    private JMenuItem hightscore;

    public AntiTowerDefenceGUI(Image[] images, int fps, int width, int height) {
//	this.width = width;
	this.height = height;
	this.images = images;
	frame = new JFrame("Anti Tower Defence");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);

	JMenuBar menuBar = buildMenuBar();
	frame.setJMenuBar(menuBar);

	JPanel mainPanel = new JPanel(new BorderLayout(), true);
	frame.add(mainPanel);

	JLayeredPane gamePanels = new JLayeredPane();
	mainPanel.add(gamePanels, BorderLayout.CENTER);
	gamePanels.setPreferredSize(new Dimension(width*32, height*32));

	gameBackground = new GameBackground(images, width, height);
	gameBackground.setBounds(0, 0, width*32, height*32);
	gamePanels.add(gameBackground, new Integer(0), 0);

	gameForeground = new GameForeground(images, fps);
	gameForeground.setBounds(0, 0, width*32, height*32);
	gamePanels.add(gameForeground, new Integer(1), 0);

	cover = new JComponent() {
	    private static final long serialVersionUID = 1L;
	    @Override
	    public void paintComponent(Graphics g) {
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, width*32, height*32);
	    }
	};
	cover.setBounds(0, 0, width*32, height*32);
	cover.setVisible(false);
	gamePanels.add(cover, new Integer(2), 0);

	coverTitel = new JComponent() {
	    private static final long serialVersionUID = 1L;
	};
	coverTitel.setLayout(new FlowLayout());
	coverTitel.setBounds(0, height*32/8, width*32, height*32/4);
	coverTitel.setVisible(false);
	gamePanels.add(coverTitel, new Integer(3), 0);

	options = new JComponent() {
	    private static final long serialVersionUID = 1L;
	};
	options.setLayout(new FlowLayout());
	options.setBounds(0, height*32*3/8, width*32, height*32/4);
	options.setVisible(false);
	gamePanels.add(options, new Integer(3), 0);

	titelText = new JLabel("Anti Tower Defence");
	titelText.setFont(new Font("Serif", Font.BOLD, height*32/8));
	coverTitel.add(titelText);

	nextLevelButton = new JButton("Next Level");
	nextLevelButton.setBackground(Color.LIGHT_GRAY);
	nextLevelButton.setPreferredSize(
		new Dimension(height*32/4, height*32/8));

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

    public void emptyButtons() {
	buttonPanel.removeAll();
	buttonPanel.repaint();
    }

    public JButton getNextLevelButton() {
	return nextLevelButton;
    }

    public GameForeground getGameForeground() {
	return gameForeground;
    }

    public void newLevelGUI(Terrain[] grass,
	    CopyOnWriteArrayList<Terrain> road,
	    CopyOnWriteArrayList<Unit> units,
	    CopyOnWriteArrayList<Tower> towers, LevelStats stats) {
	gameBackground.setTerrain(grass, road);
	gameForeground.setTerrainAndUnits(units, road);
	setTowers(towers);
	gameForeground.setStats(stats);
    }

    public void setTowers(CopyOnWriteArrayList<Tower> towers) {
	gameForeground.setTowers(towers);
    }

    public boolean isPaused() {
	return cover.isVisible();
    }

    public void hidePausePanel() {
	cover.setVisible(false);
	coverTitel.setVisible(false);
	options.setVisible(false);
	options.removeAll();
	restart.setText("Restart");
	restartLevel.setEnabled(true);
	pause.setText("Pause");
	pause.setEnabled(true);
    }

    public void showPausePanel(String text) {
	pause.setText("Resume");
	for (Component b : buttonPanel.getComponents()) {
	    b.setEnabled(false);
	}

	titelText.setText(text);
	cover.setVisible(true);
	coverTitel.setVisible(true);
	options.setVisible(true);

	JButton resumeButton = new JButton("Resume Game");
	resumeButton.setBackground(Color.LIGHT_GRAY);
	resumeButton.setPreferredSize(new Dimension(height*32/4, height*32/8));
	resumeButton.addActionListener(pause.getActionListeners()[0]);
	options.add(resumeButton);
    }

    public void showLosePanel(String text) {
	restart.setText("New Game");
	restartLevel.setEnabled(false);
	pause.setEnabled(false);
	for (Component b : buttonPanel.getComponents()) {
	    b.setEnabled(false);
	}

	titelText.setText(text);
	cover.setVisible(true);
	coverTitel.setVisible(true);
	options.setVisible(true);

	JButton newGameButton = new JButton("New Game");
	newGameButton.setBackground(Color.LIGHT_GRAY);
	newGameButton.setPreferredSize(new Dimension(height*32/4, height*32/8));
	newGameButton.addActionListener(restart.getActionListeners()[0]);
	options.add(newGameButton);

	JButton quitButton = new JButton("Quit");
	quitButton.setBackground(Color.LIGHT_GRAY);
	quitButton.setPreferredSize(new Dimension(height*32/4, height*32/8));
	quitButton.addActionListener(quit.getActionListeners()[0]);
	options.add(quitButton);
    }

    public void showWinPanel(String text) {
	pause.setEnabled(false);
	for (Component b : buttonPanel.getComponents()) {
	    b.setEnabled(false);
	}

	titelText.setText(text);
	cover.setVisible(true);
	coverTitel.setVisible(true);
	options.setVisible(true);

	options.add(nextLevelButton);

	JButton restartLevelButton = new JButton("Restart Level");
	restartLevelButton.setBackground(Color.LIGHT_GRAY);
	restartLevelButton.setPreferredSize(
		new Dimension(height*32/4, height*32/8));
	restartLevelButton.addActionListener(
		restartLevel.getActionListeners()[0]);
	options.add(restartLevelButton);
    }

    @SuppressWarnings("resource")
    public void showHelpFrame() {
	JFrame helpFrame = new JFrame("Help");
	helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JPanel helpPanel = new JPanel(new GridLayout(7, 1));
	
	JScrollPane helpScrollPane = new JScrollPane(helpPanel);
	helpFrame.add(helpScrollPane);

	JPanel introPanel = new JPanel(new BorderLayout());
	helpPanel.add(introPanel);
	JPanel introImagePanel = new JPanel();
	introPanel.add(introImagePanel, BorderLayout.NORTH);
	introImagePanel.add(new JLabel(new ImageIcon(images[8])));
	introImagePanel.add(new JLabel(new ImageIcon(images[8])));
	JTextArea introTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Intro.txt"))
		.useDelimiter("\\Z").next());
	introPanel.add(introTextPanel, BorderLayout.CENTER);
	introTextPanel.setEditable(false);

	JPanel farmerPanel = new JPanel(new BorderLayout());
	helpPanel.add(farmerPanel);
	JPanel farmerImagePanel = new JPanel();
	farmerPanel.add(farmerImagePanel, BorderLayout.NORTH);
	farmerImagePanel.add(new JLabel(new ImageIcon(images[19])));
	JTextArea farmerTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Farmer.txt"))
		.useDelimiter("\\Z").next());
	farmerPanel.add(farmerTextPanel, BorderLayout.CENTER);
	farmerTextPanel.setEditable(false);

	JPanel soldierPanel = new JPanel(new BorderLayout());
	helpPanel.add(soldierPanel);
	JPanel soldierImagePanel = new JPanel();
	soldierPanel.add(soldierImagePanel, BorderLayout.NORTH);
	soldierImagePanel.add(new JLabel(new ImageIcon(images[20])));
	JTextArea soldierTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Soldier.txt"))
		.useDelimiter("\\Z").next());
	soldierPanel.add(soldierTextPanel, BorderLayout.CENTER);
	soldierTextPanel.setEditable(false);

	JPanel wizardPanel = new JPanel(new BorderLayout());
	helpPanel.add(wizardPanel);
	JPanel wizardImagePanel = new JPanel();
	wizardPanel.add(wizardImagePanel, BorderLayout.NORTH);
	wizardImagePanel.add(new JLabel(new ImageIcon(images[21])));
	JTextArea wizardTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Wizard.txt"))
		.useDelimiter("\\Z").next());
	wizardPanel.add(wizardTextPanel, BorderLayout.CENTER);
	wizardTextPanel.setEditable(false);

	JPanel portalPanel = new JPanel(new BorderLayout());
	helpPanel.add(portalPanel);
	JPanel portalImagePanel = new JPanel();
	portalPanel.add(portalImagePanel, BorderLayout.NORTH);
	portalImagePanel.add(new JLabel(new ImageIcon(images[13])));
	portalImagePanel.add(new JLabel(new ImageIcon(images[14])));
	portalImagePanel.add(new JLabel(new ImageIcon(images[15])));
	JTextArea portalTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Portal.txt"))
		.useDelimiter("\\Z").next());
	portalPanel.add(portalTextPanel, BorderLayout.CENTER);
	portalTextPanel.setEditable(false);

	JPanel switchPanel = new JPanel(new BorderLayout());
	helpPanel.add(switchPanel);
	JPanel switchImagePanel = new JPanel();
	switchPanel.add(switchImagePanel, BorderLayout.NORTH);
	switchImagePanel.add(new JLabel(new ImageIcon(images[0])));
	switchImagePanel.add(new JLabel(new ImageIcon(images[1])));
	switchImagePanel.add(new JLabel(new ImageIcon(images[2])));
	switchImagePanel.add(new JLabel(new ImageIcon(images[3])));
	JTextArea switchTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Switch.txt"))
		.useDelimiter("\\Z").next());
	switchPanel.add(switchTextPanel, BorderLayout.CENTER);
	switchTextPanel.setEditable(false);

	JPanel towerPanel = new JPanel(new BorderLayout());
	helpPanel.add(towerPanel);
	JPanel towerImagePanel = new JPanel();
	towerPanel.add(towerImagePanel, BorderLayout.NORTH);
	towerImagePanel.add(new JLabel(new ImageIcon(images[16])));
	JTextArea towerTextPanel = new JTextArea(
		new Scanner(ResourcesLoader.load("gameinfo/Tower.txt"))
		.useDelimiter("\\Z").next());
	towerPanel.add(towerTextPanel, BorderLayout.CENTER);
	towerTextPanel.setEditable(false);

	helpFrame.pack();
	helpFrame.setVisible(true);
    }

    @SuppressWarnings("resource")
    public void showAboutFrame() {
	JFrame aboutFrame = new JFrame("About");
	aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	aboutFrame.setResizable(false);

	JPanel aboutPanel = new JPanel();
	aboutFrame.add(aboutPanel);
	JTextArea textArea = new JTextArea(9, 15);
	aboutPanel.add(textArea);
	textArea.setEditable(false);
	textArea.setText(
		new Scanner(ResourcesLoader.load("gameinfo/About.txt"))
		.useDelimiter("\\Z").next());

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

	if (db.connectToDB()) {
	    String[][] currentHighscore = db.getHighScoreTopTen();
	    if (currentHighscore != null) {
		for (String[] s : currentHighscore) {
		    if (s[1] != null) {
			char[] spaces = new char[6 - s[1].length()];
			Arrays.fill(spaces, ' ');
			scoresAndNames += s[1] + new String(spaces) + s[0]
				+ "\n";
		    }
		}
	    }
	} else {
	    scoresAndNames = "ERROR 626.\n"
		    + "Failed to connect to highscore database!";
	}

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

    public void showNewHighscoreFrame(HighScoreDB db, int score) {
	JFrame newHighscoreFrame = new JFrame("New Highscore");
	newHighscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	newHighscoreFrame.setResizable(false);

	JPanel newHighscorePanel = new JPanel(new GridLayout(3, 1));
	newHighscoreFrame.add(newHighscorePanel);

	JPanel levelPanel = new JPanel();
	newHighscorePanel.add(levelPanel);
	JLabel scoreLabel = new JLabel("You reached level: " + score);
	levelPanel.add(scoreLabel);

	JPanel outputPanel = new JPanel();
	newHighscorePanel.add(outputPanel);
	JLabel errorLabel = new JLabel("");
	outputPanel.add(errorLabel);

	JPanel inputPanel = new JPanel();
	newHighscorePanel.add(inputPanel);
	JLabel nameLabel = new JLabel("Name: ");
	inputPanel.add(nameLabel);
	JTextField nameField = new JTextField(20);
	inputPanel.add(nameField);
	nameField.setDocument(new PlainDocument() {
	    private static final long serialVersionUID = 1L;
	    @Override
	    public void insertString(int offset, String  str,
		    AttributeSet attr) {
		if (str == null) {
		    return;
		}
		if ((getLength() + str.length()) <= 20) {
		    try {
			super.insertString(offset, str, attr);
		    } catch (BadLocationException e) {
			//e.printStackTrace();
		    }
		}
	    }
	});
	JButton enterButton = new JButton("Enter");
	inputPanel.add(enterButton);
	enterButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (db.connectToDB()) {
		    String success = db.addScore(nameField.getText(), score);
		    if (success.equals("invalid")) {
			errorLabel.setText("Invalid name. Try again!");
		    } else if (success.equals("fail")) {
			errorLabel.setText("ERROR 42. Failed to upload to"
				+ " highscore database!");
		    } else {
			newHighscoreFrame.dispatchEvent(
				new WindowEvent(newHighscoreFrame,
					WindowEvent.WINDOW_CLOSING));
			showHighscoreFrame(db);
		    }
		} else {
		    errorLabel.setText("ERROR 626. Failed to connect to"
			    + " highscore database!");
		}
	    }
	});

	newHighscoreFrame.pack();
	newHighscoreFrame.setLocationRelativeTo(null);
	newHighscoreFrame.setVisible(true);
    }

    public void showErrorFrame(String message) {
	JFrame errorFrame = new JFrame("Error");
	errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	errorFrame.setResizable(false);

	JPanel errorPanel = new JPanel();
	errorFrame.add(errorPanel);
	JTextArea textArea = new JTextArea();
	errorPanel.add(textArea);
	textArea.setEditable(false);
	textArea.setText(message);

	errorFrame.pack();
	errorFrame.setLocationRelativeTo(null);
	errorFrame.setVisible(true);
    }

}
