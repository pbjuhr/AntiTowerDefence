package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.JComponent;
import javax.swing.Timer;

import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.*;
import com.jaap.antitowerdefence.unit.*;

/**
 * This class contains the foreground graphics elements of a level,
 * including units, towers and various road objects.
 * @author Joakim Sandman (tm08jsn)
 */
public class GameForeground extends JComponent {

    private static final long serialVersionUID = 1L;
    private Image[] images;			// Images to draw.
    private CopyOnWriteArrayList<Unit> units;	// Unit array to draw.
    private CopyOnWriteArrayList<Tower> towers;	// Tower array to draw.
    private CopyOnWriteArrayList<Terrain> road;	// Road array to draw.
    private LevelStats stats;			// Stats for current level.
    private Timer timer;			// Timer for drawing.
    private Timer levelTime;			// Timer to show time.
    private long time;				// Seconds passed in level.
    private Semaphore timeBusy;			// Semaphore for "time".
    private Semaphore towersBusy;		// Semaphore for "towers".

    /**
     * Initializes a new GameForeground, including the timer to draw this
     * component periodically.
     * @param images Images to draw.
     * @param fps The update speed of this component in frames per second.
     */
    public GameForeground(Image[] images, int fps) {
	this.images = images;
	towersBusy = new Semaphore(1);
	timeBusy = new Semaphore(1);
	time = 0;
	timer = new Timer(Math.round(1000/fps), new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		repaint();
	    }
	});
	timer.setRepeats(true);
	levelTime = new Timer(1000, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    timeBusy.acquire();
		} catch (InterruptedException e) {
		    // Do nothing and hope it works.
		}
		time++;
		timeBusy.release();
	    }
	});
	levelTime.setRepeats(true);
    }

    /**
     * Sets the road and unit arrays.
     * @param units Unit array to draw.
     * @param road Road array to draw.
     */
    public void setTerrainAndUnits(CopyOnWriteArrayList<Unit> units,
	    CopyOnWriteArrayList<Terrain> road) {
	this.units = units;
	this.road = road;
    }

    /**
     * Sets the tower array.
     * @param towers Tower array to draw.
     */
    public void setTowers(CopyOnWriteArrayList<Tower> towers) {
	try {
	    towersBusy.acquire();
	} catch (InterruptedException e) {
	    // Do nothing and hope it works.
	}
	this.towers = towers;
	towersBusy.release();
    }

    /**
     *  Sets the level stats, resets time and draws this component.
     * @param stats Stats for current level.
     */
    public void setStats(LevelStats stats) {
	this.stats = stats;
	time = 0;
	repaint();
    }

    /**
     * Starts the timers.
     */
    public void start() {
	timer.start();
	levelTime.start();
    }

    /**
     * Stops the timers.
     */
    public void stop() {
	timer.stop();
	levelTime.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawRoadObjects(g);
	drawUnits(g);
	drawTowers(g);
	// Draw score, credits and time in the upper left corner.
	g.setColor(Color.WHITE);
	g.setFont(new Font("Courier new", Font.BOLD, 14));
	g.drawString("Score: " + stats.getScore() + "/" + stats.getWinScore(),
		10, 15);
	g.drawString("Credits: " + stats.getCredits(), 10, 30);
	try {
	    timeBusy.acquire();
	} catch (InterruptedException e) {
	    // Do nothing and hope it works.
	}
	String timeString = String.format("%02d:%02d", time/60, time%60);
	timeBusy.release();
	g.drawString("Time: " + timeString, 10, 45);
    }

    /**
     * Draws the correct road object tiles where there are any.
     * @param g The Graphics object to draw with.
     */
    private void drawRoadObjects(Graphics g) {
	for (Terrain t : road) {
	    int x = t.getPosition().getX()*32;
	    int y = t.getPosition().getY()*32;
	    if (t instanceof Start) {
		Image dirImg = smallDirImage(((Switch) t).getDirection());
		g.drawImage(images[8], x, y, null);
		g.drawImage(dirImg, x, y, null);
	    } else if (t instanceof Goal) {
		g.drawImage(images[9], x, y, null);
	    } else if (t instanceof Portal) {
		Portal portal = (Portal) t;
		Direction dir = portal.getDirection();
		if (portal.hasReciever()) {
		    g.drawImage(images[14], x, y, null);
		} else if (dir == null) {
		    g.drawImage(images[13], x, y, null);
		} else {
		    g.drawImage(images[15], x, y, null);
		    g.drawImage(smallDirImage(dir), x, y, null);
		}
	    } else if (t instanceof Switch) {
		Image dirImg = bigDirImage(((Switch) t).getDirection());
		g.drawImage(dirImg, x, y, null);
	    }
	}
    }

    /**
     * Finds the correct "big" image for the given direction.
     * @param dir The direction.
     * @return The image.
     */
    private Image bigDirImage(Direction dir) {
	Image dirImg = null;
	switch (dir) {
	case EAST:
	    dirImg = images[0];
	    break;
	case NORTH:
	    dirImg = images[1];
	    break;
	case SOUTH:
	    dirImg = images[2];
	    break;
	case WEST:
	    dirImg = images[3];
	    break;
	}
	return dirImg;
    }

    /**
     * Finds the correct "small" image for the given direction.
     * @param dir The direction.
     * @return The image.
     */
    private Image smallDirImage(Direction dir) {
	Image dirImg = null;
	switch (dir) {
	case EAST:
	    dirImg = images[4];
	    break;
	case NORTH:
	    dirImg = images[5];
	    break;
	case SOUTH:
	    dirImg = images[6];
	    break;
	case WEST:
	    dirImg = images[7];
	    break;
	}
	return dirImg;
    }

    /**
     * Draws units where there are units.
     * @param g The Graphics object to draw with.
     */
    private void drawUnits(Graphics g) {
	for (Unit u : units) {
	    Position pos = animatedPosition(u);
	    int x = pos.getX();
	    int y = pos.getY();
	    if (u instanceof FarmerUnit) {
		g.drawImage(images[19], x, y, null);
	    } else if (u instanceof SoldierUnit) {
		g.drawImage(images[20], x, y, null);
	    } else if (u instanceof TeleporterUnit) {
		g.drawImage(images[21], x, y, null);
	    }
	}
    }

    /**
     * Draws towers where there are towers.
     * @param g The Graphics object to draw with.
     */
    private void drawTowers(Graphics g) {
	try {
	    towersBusy.acquire();
	} catch (InterruptedException e) {
	    // Do nothing and hope it works.
	}
	for (Tower t : towers) {
	    int x = t.getPosition().getX()*32;
	    int y = t.getPosition().getY()*32;
	    Unit unitShot = t.getUnitShot();
	    if (unitShot == null) {
		g.drawImage(images[16], x, y, null); // Tower.
	    } else {
		Position pos = animatedPosition(unitShot);
		int shootX = pos.getX();
		int shootY = pos.getY();
		g.setColor(Color.RED); // Lazer color.
		g.drawLine(x + 16, y + 16, shootX + 18, shootY + 30); // Lazer.
		g.drawImage(images[18], shootX, shootY, null); // Blood.
		g.drawImage(images[17], x, y, null); // Shooting tower.
	    }
	}
	towersBusy.release();
    }

    /**
     * Finds the appropriate (pixel) position of a unit in motion.
     * @param u The unit.
     * @return The (pixel) position.
     */
    private Position animatedPosition(Unit u) {
	int x = u.getPosition().getX()*32;
	int y = u.getPosition().getY()*32;
	int animationOffset = (int)
		(32 * (1- (double) u.getCoolDown()/u.getMaxCoolDown()));
	switch (u.getDirection()) {
	case EAST:
	    x += animationOffset;
	    break;
	case NORTH:
	    y -= animationOffset;
	    break;
	case SOUTH:
	    y += animationOffset;
	    break;
	case WEST:
	    x -= animationOffset;
	    break;
	}
	return new Position(x, y);
    }

}
