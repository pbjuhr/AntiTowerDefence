package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Terrain;

public class GameBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private Terrain[] grass;
    private Terrain[] road;

    public GameBackground() {//Terrain[] grass, Terrain[] road) {
//	this.grass = grass;
//	this.road = road;
	grass = new Terrain[1];
	grass[0] = new Grass(new Position(1, 2));
	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWater(g);

	for (Terrain t : grass) {
	    BufferedImage grass = null;
	    try {
		grass = ImageIO.read(new File(getClass().getResource("../../../../assets/" + t.getClass().getSimpleName() + ".png").getFile()));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(grass, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
	}

//	for (Terrain t : road) {
//	    BufferedImage road = null;
//	    try {
//		road = ImageIO.read(new File(getClass().getResource("../../../../assets/" + t.getClass().getSimpleName() + ".png").getFile()));
//	    } catch (IOException e) {
//		//e.printStackTrace();
//	    }
//	    g.drawImage(road, t.getPosition().getX()*32, t.getPosition().getY()*32, null);
//	}
    }

    private void drawWater(Graphics g) {
	BufferedImage water = null;
	try {
	    water = ImageIO.read(new File(getClass().getResource("../../../../assets/Water.png").getFile()));
	} catch (IOException e) {
	    //e.printStackTrace();
	}
	for (int row = 0; row < 15; row++) {
	    for (int col = 0; col < 20; col++) {
		g.drawImage(water, col*32, row*32, null);
	    }
	}
    }

}
