package com.jaap.antitowerdefence.antiTowerDefence;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GamePanel extends JComponent {

    public GamePanel() {
	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(35,245,50,100);
        g.setColor(Color.RED);
        g.fillRect(30,240,15,50);
    }

}
