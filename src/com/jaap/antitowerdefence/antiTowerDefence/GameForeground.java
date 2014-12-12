package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import com.jaap.antitowerdefence.level.LevelStats;
import com.jaap.antitowerdefence.terrain.Portal;
import com.jaap.antitowerdefence.terrain.Switch;
import com.jaap.antitowerdefence.terrain.Terrain;
import com.jaap.antitowerdefence.unit.Unit;
//TODO errorhandling switchlisteners working towers remove testunit
public class GameForeground extends JComponent {

    private static final long serialVersionUID = 1L;
    private ArrayList<Unit> units;
    private Tower[] towers;
    private Terrain[] road;
    private LevelStats stats;
    int animationOffset;
    double realOffset;

    public GameForeground(int fps, LevelStats stats) {
	this.stats = stats;
	animationOffset = 0;
	realOffset = 0;
	Timer timer = new Timer(Math.round(1000/fps), new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		repaint();
		realOffset = (realOffset + 32.0/fps) % 32;
		animationOffset = 0;//(int) realOffset;
	    }
	});
	timer.setRepeats(true);
	timer.start();
    }

    public void setTerrainAndObjects(ArrayList<Unit> units, Tower[] towers, Terrain[] road) {
	this.units = units;
	this.towers = towers;
	this.road = road;
	repaint();
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
	g.drawString("Time: " + 42, 10, 45);
    }

    private void drawRoadObjects(Graphics g) {
	for (Terrain t : road) {
	    BufferedImage roadObjectImg = null;
	    try {
		switch (t.getClass().getSimpleName()) {
		case "Start":
		    roadObjectImg = ImageIO.read(new File("assets/img/Start.png"));
		    break;
		case "Goal":
		    roadObjectImg = ImageIO.read(new File("assets/img/Goal.png"));
		    break;
		case "Portal":
		    if (((Portal) t).hasReciever()) {
			roadObjectImg = ImageIO.read(new File("assets/img/Portal_start.png"));
		    } else {
			roadObjectImg = ImageIO.read(new File("assets/img/Portal_end.png"));
		    }
		    break;
		case "Switch":
		    switch (((Switch) t).getDirection()) {
		    case EAST:
			roadObjectImg = ImageIO.read(new File("assets/img/DirBig/EAST.png"));
			break;
		    case NORTH:
			roadObjectImg = ImageIO.read(new File("assets/img/DirBig/NORTH.png"));
			break;
		    case SOUTH:
			roadObjectImg = ImageIO.read(new File("assets/img/DirBig/SOUTH.png"));
			break;
		    case WEST:
			roadObjectImg = ImageIO.read(new File("assets/img/DirBig/WEST.png"));
			break;
		    }
		    break;
		}
		g.drawImage(roadObjectImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	    }
	    catch (IOException e) {
		//e.printStackTrace();
	    }
	}
    }

    private void drawTowers(Graphics g) {
	for (Tower t : towers) {
	    BufferedImage towerImg = null;
	    BufferedImage shootImg = null;
	    try {
		if (t.getShootPosition() == null) {
		    towerImg = ImageIO.read(new File("assets/img/" + t.getClass().getSimpleName() + ".png"));
		} else {
		    towerImg = ImageIO.read(new File("assets/img/Tower_shoot.png"));
		    shootImg = ImageIO.read(new File("assets/img/red.png"));
		    g.drawImage(shootImg, t.getShootPosition().getX()*32, t.getShootPosition().getY()*32, null);
		}
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(towerImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	}
    }

    private void drawUnits(Graphics g) {
	for (Unit u : units) {
	    BufferedImage unitImg = null;
	    try {
		unitImg = ImageIO.read(new File("assets/img/" + u.getClass().getSimpleName() + ".png"));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    switch (u.getDirection()) {
	    case EAST:
		g.drawImage(unitImg, u.getPosition().getX()*32 + animationOffset, u.getPosition().getY()*32, null);
		break;
	    case NORTH:
		g.drawImage(unitImg, u.getPosition().getX()*32, u.getPosition().getY()*32 - animationOffset, null);
		break;
	    case SOUTH:
		g.drawImage(unitImg, u.getPosition().getX()*32, u.getPosition().getY()*32 + animationOffset, null);
		break;
	    case WEST:
		g.drawImage(unitImg, u.getPosition().getX()*32 - animationOffset, u.getPosition().getY()*32, null);
		break;
	    }
	}
    }

}
