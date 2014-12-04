package com.jaap.antitowerdefence.unit;

import com.jaap.antitowerdefence.terrain.Portal;

public class TeleporterUnit extends Unit{
    
    private Portal thePortal;
    
    public TeleporterUnit(){
	super();
	speed = 20;
	cost = 200;
	health = 70;
	thePortal = new Portal();
    }
    
    public void placePortal(){
	
    }
    
}
