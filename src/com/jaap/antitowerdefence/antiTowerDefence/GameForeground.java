package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

//import com.jaap.antitowerdefence.terrain.Terrain;
//import com.jaap.antitowerdefence.unit.FarmerUnit;
//import com.jaap.antitowerdefence.unit.Unit;

public class GameForeground extends JComponent {

    private static final long serialVersionUID = 1L;
    //private Unit[] units;
//    private Tower[] towers;
    //private Terrain[] switches;
int dx=0;
    public GameForeground() {//Unit[] units, Tower[] towers, Terrain[] switches) { // portals
//	towers = new Tower[1];
//	towers[0] = new Tower();
//	towers[0].setPosition(new Position(5, 10));
	Timer timer = new Timer(50, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		repaint();
		dx++;
	    }
	});
	timer.setRepeats(true);
	timer.start();
//	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//	for (Unit u : units) {
//	    BufferedImage unit = null;
//	    try {
////		unit = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/" + u.getClass().getSimpleName() + ".png").getFile()));
//		unit = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/Farmer.png").getFile()));
//	    } catch (IOException e) {
//		//e.printStackTrace();
//	    }
//	    g.drawImage(unit, u.getPosition().getX()*32, u.getPosition().getY()*32, null);
//	}

//	for (Tower t : towers) {
	    BufferedImage tower = null;
	    try {
//		tower = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/" + t.getClass().getSimpleName() + ".png").getFile()));
		tower = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/Tower.png").getFile()));
	    } catch (IOException e) {
		//e.printStackTrace();
	    }
	    g.drawImage(tower,5*32+dx, 10*32-dx, null);
//	}

//	for (Terrain s : switches) {
//	    BufferedImage switchPlate = null;
//	    try {
////		switchPlate = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/" + s.getClass().getSimpleName() + ".png").getFile()));
//		switchPlate = ImageIO.read(new File(getClass().getResource("../../../../../assets/img/Portal.png").getFile()));
//	    } catch (IOException e) {
//		//e.printStackTrace();
//	    }
//	    g.drawImage(switchPlate, s.getPosition().getX()*32, s.getPosition().getY()*32, null);
//	}
    }

}
