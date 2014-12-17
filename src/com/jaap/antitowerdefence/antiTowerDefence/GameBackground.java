package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * @author Joakim Sandman (tm08jsn)
 */
public class GameBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private Image[] images;
    private int width;
    private int height;
    private Terrain[] grass;
    private CopyOnWriteArrayList<Terrain> road;

    public GameBackground(Image[] images, int width, int height) {
	this.images = images;
	this.width = width;
	this.height = height;
    }

    public void setTerrain(Terrain[] grass,
	    CopyOnWriteArrayList<Terrain> road) {
	this.grass = grass;
	this.road = road;
	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawWater(g);
	drawGrass(g);
	drawRoad(g);
    }

    private void drawWater(Graphics g) {
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		g.drawImage(images[10], col*32, row*32, null);
	    }
	}
    }

    private void drawGrass(Graphics g) {
	for (Terrain t : grass) {
	    g.drawImage(images[11], t.getPosition().getX()*32,
		    t.getPosition().getY()*32, null);
	}
    }

    private void drawRoad(Graphics g) {
	for (Terrain t : road) {
	    g.drawImage(images[12], t.getPosition().getX()*32,
		    t.getPosition().getY()*32, null);
	}
    }

}
