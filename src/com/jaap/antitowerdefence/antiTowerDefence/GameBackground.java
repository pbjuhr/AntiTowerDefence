package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.jaap.antitowerdefence.terrain.Terrain;

//TODO errorhandling
public class GameBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private int width;
    private int height;
    private Terrain[] grass;
    private Terrain[] road;

    public GameBackground(int width, int height) {
	this.width = width;
	this.height = height;
    }

    public void setTerrain(Terrain[] grass, Terrain[] road) {
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
	BufferedImage waterImg = null;
	try {
	    waterImg = ImageIO.read(new File("assets/img/Water.png"));
	} catch (IOException e) {
	    //e.printStackTrace();
	}
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		g.drawImage(waterImg, col*32, row*32, null);
	    }
	}
    }

    private void drawGrass(Graphics g) {
	for (Terrain t : grass) {
	    BufferedImage grassImg = null;
	    try {
		grassImg = ImageIO.read(new File("assets/img/Grass.png"));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(grassImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	}
    }

    private void drawRoad(Graphics g) {
	for (Terrain t : road) {
	    BufferedImage roadImg = null;
	    try {
		roadImg = ImageIO.read(new File("assets/img/Road.png"));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(roadImg, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	}
    }

}
