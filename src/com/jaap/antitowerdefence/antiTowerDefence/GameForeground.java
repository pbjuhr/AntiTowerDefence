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
 * @author Joakim Sandman (tm08jsn)
 */
public class GameForeground extends JComponent {

    private static final long serialVersionUID = 1L;
    private Image[] images;
    private CopyOnWriteArrayList<Unit> units;
    private CopyOnWriteArrayList<Tower> towers;
    private CopyOnWriteArrayList<Terrain> road;
    private LevelStats stats;
    private Timer timer;
    private Timer levelTime;
    private long time;
    private Semaphore timeBusy;
    private Semaphore towersBusy;

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
		    //e.printStackTrace();
		}
		time++;
		timeBusy.release();
	    }
	});
	levelTime.setRepeats(true);
    }

    public void setTerrainAndUnits(CopyOnWriteArrayList<Unit> units,
	    CopyOnWriteArrayList<Terrain> road) {
	this.units = units;
	this.road = road;
    }

    public void setTowers(CopyOnWriteArrayList<Tower> towers) {
	try {
	    towersBusy.acquire();
	} catch (InterruptedException e) {
	    //e.printStackTrace();
	}
	this.towers = towers;
	towersBusy.release();
    }

    public void setStats(LevelStats stats) {
	this.stats = stats;
	time = 0;
	repaint();
    }

    public void start() {
	timer.start();
	levelTime.start();
    }

    public void stop() {
	timer.stop();
	levelTime.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawRoadObjects(g);
	drawTowers(g);
	drawUnits(g);
	g.setColor(Color.WHITE);
	g.setFont(new Font("Courier new", Font.BOLD, 14));
	g.drawString("Score: " + stats.getScore() + "/" + stats.getWinScore(),
		10, 15);
	g.drawString("Credits: " + stats.getCredits(), 10, 30);
	try {
	    timeBusy.acquire();
	} catch (InterruptedException e) {
	    // e.printStackTrace();
	}
	String timeString = String.format("%02d:%02d", time/60, time%60);
	timeBusy.release();
	g.drawString("Time: " + timeString, 10, 45);
    }

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

    private void drawTowers(Graphics g) {
	try {
	    towersBusy.acquire();
	} catch (InterruptedException e) {
	    //e.printStackTrace();
	}
	for (Tower t : towers) {
	    int x = t.getPosition().getX()*32;
	    int y = t.getPosition().getY()*32;
	    Position shootPos = t.getShootPosition();
	    if (shootPos == null) {
		g.drawImage(images[16], x, y, null);
	    } else {
		int shootX = shootPos.getX()*32;
		int shootY = shootPos.getY()*32;
		g.setColor(Color.RED);
		g.drawLine(x + 16, y + 16, shootX + 18, shootY + 30);
		g.drawImage(images[18], shootX, shootY, null);
		g.drawImage(images[17], x, y, null);
	    }
	}
	towersBusy.release();
    }

    private void drawUnits(Graphics g) {
	for (Unit u : units) {
	    int x = 0;
	    int y = 0;
	    int animationOffset = (int)
		    (32 * (1- (double) u.getCoolDown()/u.getMaxCoolDown()));
	    switch (u.getDirection()) {
	    case EAST:
		x = u.getPosition().getX()*32 + animationOffset;
		y = u.getPosition().getY()*32;
		break;
	    case NORTH:
		x = u.getPosition().getX()*32;
		y = u.getPosition().getY()*32 - animationOffset;
		break;
	    case SOUTH:
		x = u.getPosition().getX()*32;
		y = u.getPosition().getY()*32 + animationOffset;
		break;
	    case WEST:
		x = u.getPosition().getX()*32 - animationOffset;
		y = u.getPosition().getY()*32;
		break;
	    }
	    if (u instanceof FarmerUnit) {
		g.drawImage(images[19], x, y, null);
	    } else if (u instanceof SoldierUnit) {
		g.drawImage(images[20], x, y, null);
	    } else if (u instanceof TeleporterUnit) {
		g.drawImage(images[21], x, y, null);
	    }
	}
    }

}
