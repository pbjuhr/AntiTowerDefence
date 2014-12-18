package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * This class contains the background graphics elements of a level.
 * @author Joakim Sandman (tm08jsn)
 */
public class GameBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private Image[] images;			// Images to draw.
    private int width;				// Width (in tiles).
    private int height;				// Height (in tiles).
    private Terrain[] grass;			// Grass array to draw.
    private CopyOnWriteArrayList<Terrain> road; // Road array to draw.

    /**
     * Initializes a new GameBackground.
     * @param images Images to draw.
     * @param width Width of background (in tiles).
     * @param height Height of background (in tiles).
     */
    public GameBackground(Image[] images, int width, int height) {
	this.images = images;
	this.width = width;
	this.height = height;
    }

    /**
     * Sets the terrain arrays and draws this component.
     * @param grass Grass array to draw.
     * @param road Road array to draw.
     */
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

    /**
     * Draws water tiles over the entire background.
     * @param g The Graphics object to draw with.
     */
    private void drawWater(Graphics g) {
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		g.drawImage(images[10], col*32, row*32, null);
	    }
	}
    }

    /**
     * Draws grass tiles where there is grass.
     * @param g The Graphics object to draw with.
     */
    private void drawGrass(Graphics g) {
	for (Terrain t : grass) {
	    g.drawImage(images[11], t.getPosition().getX()*32,
		    t.getPosition().getY()*32, null);
	}
    }

    /**
     * Draws road tiles where there is road.
     * @param g The Graphics object to draw with.
     */
    private void drawRoad(Graphics g) {
	for (Terrain t : road) {
	    g.drawImage(images[12], t.getPosition().getX()*32,
		    t.getPosition().getY()*32, null);
	}
    }

}
