package com.jaap.antitowerdefence.level;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Goal;
import com.jaap.antitowerdefence.terrain.Grass;
import com.jaap.antitowerdefence.terrain.Road;
import com.jaap.antitowerdefence.terrain.Start;
import com.jaap.antitowerdefence.terrain.Terrain;

/**
 * LevelReader.java
 * 
 * A XML-reader that takes a level file (xml) for the game Anti Tower Defence, 
 * checks if the file is valid and, if so, parse the file. The file is 
 * validated with a xml-schema, "levelsSchema.xsd".The class contains functions 
 * for retrieving information about the game levels. If the given xml-file is 
 * not valid a ddeffault file is used.
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelReader {

    /*Variables*/
    private File levelFile;		// xml-file containing game levels
    private Document gameLevels;	// a parsed document of the game levels
    private int nrOfLevels; 		// nr of maps in file
    private int xDimension; 		// x-dimension of level maps
    private int yDimension; 		// y-dimension of level maps

    /**
     * LevelReader constructor. Creates the xml-reader with the given file name.
     * Validates ande parse the file. Sets nrOfLevels and the level maps 
     * dimensions.
     * @param levelFile - file containing valid Anti Tower Defence levels
     */
    public LevelReader(String levelFile) {
	this.levelFile = new File(levelFile);
	validateLevelFile();
	parseLevelFile();
	setNrOfLevels();
	setLevelDimensions();
    }

    /**
     * validateLevelFile uses a xml-schema, levelsSchema.xsd to validate the 
     * current level file. This way only valid level files are used.
     */
    private void validateLevelFile() {

	//TODO!!
	// HANTERA OM EJ VALIDERING GÅR IGENOM!!
	// och andra felmeddelanden
	File schemaFile = new File("src/levelsSchema.xsd");
	Source xmlLevelFile = new StreamSource(levelFile);
	SchemaFactory schemaFactory = SchemaFactory
		.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	Schema schema;
	try {
	    schema = schemaFactory.newSchema(schemaFile);
	    Validator levelValidator = schema.newValidator();
	    levelValidator.validate(xmlLevelFile);
	} catch (SAXException e) {
	    System.out.println("Fel vid skapandet av nytt schema");
	} catch (IOException e) {
	    /*Om levelfilen inte är korrekt så används defaultfilen 
	     * "levels.xml"*/
	    levelFile = new File("src/levels.xml");
	    System.out.println("Fel vid validering av filen");
	}
    }

    /**
     * parseLevelFile uses a DocumentBuilder to parse the level file making
     * it possible to read from the file.
     */
    private void parseLevelFile() {
	//TODO!!
	// HANTERA OM EJ VALIDERING GÅR IGENOM!!
	// och andra felmeddelanden
	DocumentBuilder dBuilder;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	try {
	    dBuilder = dbFactory.newDocumentBuilder();
	    gameLevels = dBuilder.parse(levelFile);
	    gameLevels.getDocumentElement().normalize();
	} catch (SAXException | IOException e) {
	    System.out.println("Fel vid skapandet av docBuilder");
	} catch (ParserConfigurationException e) {
	    System.out.println("Fel vid parsning");
	}
    }

    /**
     * setNrOfLevels extracts the nr of levels in the level file and sets
     * the variable nrOfLevels.
     */
    private void setNrOfLevels() {
	Element levels = 
		(Element)gameLevels.getElementsByTagName("levels").item(0);
	nrOfLevels = Integer.parseInt(levels.getAttribute("nrOfLevels"));	
    }

    /**
     * setLevelDimensions extracts the x and y dimensions of the level maps 
     * from the level file and sets the variables xDimension and yDimension.
     */
    private void setLevelDimensions() {
	NodeList levels = gameLevels.getElementsByTagName("levels");
	Element levelsInfo = (Element)levels.item(0);
	xDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionX"));
	yDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionY"));
    }

    /**
     * getRoad extracts all the walkable positions, road, start and goal, of a 
     * given level from the level file. Constructs terrain objects using 
     * these positions, saves the objects in a Terrain array and returns the 
     * array.
     * @param currentLevel - the level to extract the positions from
     * @return an array with walkable Terrain objects or null
     */
    public Terrain[] getRoad(int currentLevel) {
	Terrain[] road;
	NodeList positions;
	Element level;
	Element terrainRoad;
	int n = 0;
	int levelNr;
	NodeList levels = gameLevels.getElementsByTagName("level");
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    levelNr = Integer.parseInt(level.getAttribute("levelNumber"));
	    if( levelNr == currentLevel) {
		terrainRoad = (Element)level
			.getElementsByTagName("road").item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road = new Terrain[(positions.getLength()+2)];
		for(int m = 0; m < (positions.getLength()); m++){
		    road[m] = getRoadObject("road", (Element)positions.item(m));
		    n = m;
		}
		terrainRoad = (Element)level
			.getElementsByTagName("start").item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road[n+1] = getRoadObject("start", (Element)positions.item(0));
		terrainRoad = (Element)level
			.getElementsByTagName("goal").item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road[n+2] = getRoadObject("goal", (Element)positions.item(0));
		return road;
	    }
	}
	return null;
    }

    /**
     * getRoadObject creates and returns an instance of a Terrain object with 
     * a given position and type.
     * @param type - road, goal or start
     * @param position - the position to give to the Terrain object
     * @return - a Terrain object
     */
    private Terrain getRoadObject(String type, Element position) {
	Terrain road;
	int x;
	int y;
	x = Integer.parseInt(position.getAttribute("x"));
	y = Integer.parseInt(position.getAttribute("y"));

	if(type.equals("start")){
	    road = new Start(new Position(x, y), true);
	}else if(type.equals("goal")) {
	    road = new Goal(new Position(x, y), true);
	}else {
	    road = new Road(new Position(x, y), true);
	}
	return road;
    }
    
    /**
     * getGrass extracts all the grass positions of a given level from the 
     * level file. Constructs grass objects using these positions and saves the 
     * objects in a Terrain array and returns the array.
     * @param currentLevel - the level to extract the positions from
     * @return an array of Grass objects or null
     */
    public Terrain[] getGrass(int currentLevel) {
	Terrain[] grass;
	NodeList positions;
	Element level;
	Element terrainGrass;
	Element position;
	int x;
	int y;
	int levelNr;
	NodeList levels = gameLevels.getElementsByTagName("level");
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    levelNr = Integer.parseInt(level.getAttribute("levelNumber"));
	    if( levelNr == currentLevel) {
		terrainGrass = (Element)level
			.getElementsByTagName("grass").item(0);
		positions = terrainGrass.getElementsByTagName("position");
		grass = new Terrain[positions.getLength()];
		for(int m = 0; m < positions.getLength(); m++){
		    position = (Element)positions.item(m);
		    x = Integer.parseInt(position.getAttribute("x"));
		    y = Integer.parseInt(position.getAttribute("y"));
		    grass[m] = new Grass(new Position(x, y), true);
		}
		return grass;
	    }
	}
	return null;
    }

    /**
     * getLevelStats extracts the start conditions of a given level. It 
     * constructs a LevelStat object with the extracte information and returns 
     * this object.
     * @param currentLevel - the level to extract information about
     * @return - A LevelStat object containing the start conditions of the 
     * level or null
     */
    public LevelStats getLevelStats(int currentLevel) {
	LevelStats levelStats;
	NodeList levels = gameLevels.getElementsByTagName("level");
	Element level;
	int winScore;
	int credits;
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    if(Integer.parseInt(level.getAttribute("levelNumber")) == currentLevel) {
		credits = Integer.parseInt(level
			.getElementsByTagName("credits")
			.item(0).getTextContent());
		winScore = Integer.parseInt(level
			.getElementsByTagName("winScore")
			.item(0).getTextContent());
		levelStats = new LevelStats(winScore, credits);
		return levelStats;
	    }
	}
	return null;
    }

    /**
     * gerNrOfTowers extracts information from the level file about the nr of 
     * towers to be implemented in the given level.
     * @param currentLevel - the level to extract information about
     * @return - number of towers in the level
     */
    public int getNrOfTowers(int currentLevel) {
	NodeList levels = gameLevels.getElementsByTagName("level");
	Element level;
	int nrOfTowers;
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    if(Integer.parseInt(
		    level.getAttribute("levelNumber")) == currentLevel) {
		
		nrOfTowers = Integer.parseInt(
			level.getElementsByTagName("towers").
				item(0).getTextContent());
		return nrOfTowers;
	    }
	}
	return 0;
    }

/*    public boolean[] hasUnits(int currentLevel){
	boolean[] hasUnits = new boolean[3];
	NodeList levels = gameLevels.getElementsByTagName("level");
	NodeList units;
	Element level;
	String unitName;
	for(int y = 0; y < 3; y++) {
	    hasUnits[y] = false;
	}
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    if(Integer.parseInt(level.getAttribute("levelNumber")) == currentLevel) {
		units = level.getElementsByTagName("unit");
		for(int m = 0; m < units.getLength(); m++){
		    unitName = units.item(m).getTextContent();
		    if(unitName.equals("Farmer")) {
			hasUnits[0] = true;
		    }else if(unitName.equals("Soldier")) {
			hasUnits[1] = true;
		    }else if(unitName.equals("Wizard")) {
			hasUnits[2] = true;
		    }
		}
		return hasUnits;
	    }
	}
	return null;
    }
*/
    /**
     * getUnits extracts information from the level file about which units that 
     * are are supposed to be implemented in the given level.
     * @param currentLevel
     * @return - a string array of the units imlemented in the level or null
     */
    public String[] getUnits(int currentLevel){
	String[] hasUnits;
	NodeList levels = gameLevels.getElementsByTagName("level");
	NodeList units;
	Element level;
	String unitName;
	for(int i = 0; i < levels.getLength(); i++) {
	    level = (Element)levels.item(i);
	    if(Integer.parseInt(
		    level.getAttribute("levelNumber")) == currentLevel) {
		
		units = level.getElementsByTagName("unit");
		hasUnits = new String[units.getLength()];
		for(int m = 0; m < units.getLength(); m++){
		    unitName = units.item(m).getTextContent();
		    if(unitName.equals("Farmer") || unitName.equals("Soldier")
			    || unitName.equals("Wizard")) {
			hasUnits[m] = unitName;
		    }
		}
		return hasUnits;
	    }
	}
	return null;
    }

    /**
     * getNrOfLevels provides the number of levels in the level file
     * @return - number of levels in level file
     */
    public int getNrOfLevels(){
	return nrOfLevels;
    }

    /**
     * getXDimension provides the x-dimension of the level maps
     * @return x-dimension of level maps
     */
    public int getXDimension() {
	return xDimension;
    }
    
    /**
     * getYDimension provides the y-dimension of the level maps
     * @return y-dimension of level maps
     */
    public int getYDimension() {
	return yDimension;
    }
}
