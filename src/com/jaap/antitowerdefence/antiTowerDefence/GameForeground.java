package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Portal;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class GameForeground extends JComponent {

    private static final long serialVersionUID = 1L;
    private CopyOnWriteArrayList<Unit> units;
    private CopyOnWriteArrayList<Tower> towers;
    private CopyOnWriteArrayList<Terrain> road;
    private LevelStats stats;
    private Timer timer;
    private Semaphore towersBusy;

    public GameForeground(int fps) {
	towersBusy = new Semaphore(1);
	timer = new Timer(Math.round(1000/fps), new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		repaint();
	    }
	});
	timer.setRepeats(true);
    }

    public void setTerrainAndUnits(CopyOnWriteArrayList<Unit> units, CopyOnWriteArrayList<Terrain> road) {
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
	repaint();
    }

    public void start() {
	timer.start();
    }

    public void stop() {
	timer.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawRoadObjects(g);
	drawTowers(g);
	drawUnits(g);
	g.setFont(new Font("Courier new", Font.BOLD, 14));
	g.setColor(Color.WHITE);
	g.drawString("Score: " + stats.getScore() + "/" + stats.getWinScore(), 10, 15);
	g.drawString("Credits: " + stats.getCredits(), 10, 30);
    }

    private void drawRoadObjects(Graphics g) {
	for (Terrain t : road) {
	    BufferedImage roadObjectImg = null;
	    BufferedImage dirImg = null;
	    try {
		switch (t.getClass().getSimpleName()) {
		case "Start":
		    roadObjectImg = ImageIO.read(new File("assets/img/Start.png"));
		    dirImg = ImageIO.read(new File("assets/img/DirSmallWhite/" + ((Switch) t).getDirection() + ".png"));
		    break;
		case "Goal":
		    roadObjectImg = ImageIO.read(new File("assets/img/Goal.png"));
		    break;
		case "Portal":
		    if (((Portal) t).hasReciever()) {
			roadObjectImg = ImageIO.read(new File("assets/img/Portal_start.png"));
		    } else if (((Portal) t).getDirection() == null) {
			roadObjectImg = ImageIO.read(new File("assets/img/Portal.png"));
		    } else {
			roadObjectImg = ImageIO.read(new File("assets/img/Portal_end.png"));
			dirImg = ImageIO.read(new File("assets/img/DirSmallWhite/" + ((Portal) t).getDirection() + ".png"));
		    }
		    break;
		case "Switch":
		    roadObjectImg = ImageIO.read(new File("assets/img/DirBig/" + ((Switch) t).getDirection() + ".png"));
		    break;
		}
		g.drawImage(roadObjectImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
		if (dirImg != null) {
		    g.drawImage(dirImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
		}
	    }
	    catch (IOException e) {
		//e.printStackTrace();
	    }
	}
    }

    private void drawTowers(Graphics g) {
	try {
	    towersBusy.acquire();
	} catch (InterruptedException e) {
	    //e.printStackTrace();
	}
	for (Tower t : towers) {
	    BufferedImage towerImg = null;
	    BufferedImage shootImg = null;
	    try {
		if (t.getShootPosition() == null) {
		    towerImg = ImageIO.read(new File("assets/img/" + t.getClass().getSimpleName() + ".png"));
		} else {
		    towerImg = ImageIO.read(new File("assets/img/Tower_shoot.png"));
		    shootImg = ImageIO.read(new File("assets/img/red.png"));
		    g.setColor(Color.RED);
		    g.drawLine(t.getPosition().getX()*32 + 16, t.getPosition().getY()*32 + 16,
			    t.getShootPosition().getX()*32 + 16, t.getShootPosition().getY()*32 + 16);
		    g.drawImage(shootImg, t.getShootPosition().getX()*32, t.getShootPosition().getY()*32, null);
		}
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(towerImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	}
	towersBusy.release();
    }

    private void drawUnits(Graphics g) {
	for (Unit u : units) {
	    BufferedImage unitImg = null;
	    try {
		unitImg = ImageIO.read(new File("assets/img/" + u.getClass().getSimpleName() + ".png"));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    int x = 0;
	    int y = 0;
	    int animationOffset = (int) (32 * (1 - (double) u.getCoolDown()/u.getMaxCoolDown()));
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
	    g.drawImage(unitImg, x, y, null);
	}
    }

}
