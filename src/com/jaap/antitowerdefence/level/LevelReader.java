package com.jaap.antitowerdefence.level;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CopyOnWriteArrayList;

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
import com.jaap.antitowerdefence.antiTowerDefence.ResourcesLoader;
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
 * not valid a default file is used.
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelReader {

    /* Variables */   
    private File levelFile; 		//Used the user inputs a filepath 
    private InputStream inputStream;	//Used to read from assets in jar-file
    private String defaultLevelFile;	// path to the default xml-file
    private Document gameLevels; 	// a parsed document of the game levels
    private int nrOfLevels; 		// nr of maps in file
    private int xDimension;	 	// x-dimension of level maps
    private int yDimension; 		// y-dimension of level maps
    private boolean inputFile;

    /**
     * LevelReader constructor. Creates the xml-reader with the given file name.
     * Validates ande parse the file. Sets nrOfLevels and the level maps
     * dimensions.
     * 
     * @param levelFile - file containing valid Anti Tower Defence levels
     * 
     * @throws ParserConfigurationException - Error while parsing the level file
     * @throws IOException - Error while creating the docBuilder
     * @throws SAXException - Error while creating the docBuilder
     */
    public LevelReader(String levelFile) throws ParserConfigurationException,
    SAXException, IOException {
	defaultLevelFile = "levels/levels.xml";
	/*If a file is given as input from the user a File is created, if not
	 * an inputstream has to be used to read rom assets in the jar file*/
	if(levelFile == null){
	    inputStream = ResourcesLoader.load(defaultLevelFile);
	    inputFile = false;
	    parseLevelFile();

	}else{
	    this.levelFile = new File(levelFile);
	    inputFile = true;
	    /*A file from user input is always validated with a xml-schema*/
	    validateInputFile();
	    parseLevelFile();
	}
	setNrOfLevels();
	setLevelDimensions();
    }

    /**
     * validateInputFile creates a scema with a xsd file (levelsSchema.xsd) and 
     * validates levelfiles given as input by the user  using this schema. This 
     * is done so that no invalid files are used by the game. If the given file 
     * is invalid the default xml file (levels.xml) is used.
     */
    private void validateInputFile() {

	StreamSource schemaFile;
	Source xmlLevelFile;
	schemaFile =
		new StreamSource(
			ResourcesLoader.load("levels/levelsSchema.xsd"));
	xmlLevelFile = new StreamSource(levelFile);

	SchemaFactory schemaFactory = SchemaFactory
		.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	Schema schema;
	try {
	    schema = schemaFactory.newSchema(schemaFile);
	    Validator levelValidator = schema.newValidator();
	    levelValidator.validate(xmlLevelFile);
	} catch (SAXException e) {
	    /*If the schema can not be created and used for validation, the 
	     * default file (levels.xml) is used */	    
	    inputStream = ResourcesLoader.load(defaultLevelFile);
	    inputFile = false;
	    System.out.println("Error: The schema could not be created");
	} catch (IOException e) {
	    /*If the levelfile is invalid, the default file (levels.xml) is 
	     * used */    
	    inputStream = ResourcesLoader.load(defaultLevelFile);
	    inputFile = false;
	    System.out.println("Error: The file is unvalid");
	}
    }   

    /**
     * parseLevelFile uses a DocumentBuilder to parse the level file making it
     * possible to read from the file.
     * 
     * @throws ParserConfigurationException - Error while parsing the level file
     * @throws IOException - Error while creating the docBuilder
     * @throws SAXException - Error while creating the docBuilder
     */
    private void parseLevelFile() throws ParserConfigurationException,
    SAXException, IOException {

	DocumentBuilder dBuilder;
	DocumentBuilderFactory dbFactory = 
		DocumentBuilderFactory.newInstance();

	dBuilder = dbFactory.newDocumentBuilder();

	if(inputFile){
	    gameLevels = dBuilder.parse(levelFile);
	}else{
	    gameLevels = dBuilder.parse(inputStream);
	}	
	gameLevels.getDocumentElement().normalize();
    }

    /**
     * setNrOfLevels extracts the nr of levels in the level file and sets the
     * variable nrOfLevels.
     */
    private void setNrOfLevels() {
	Element levels = (Element) gameLevels.getElementsByTagName("levels")
		.item(0);
	nrOfLevels = Integer.parseInt(levels.getAttribute("nrOfLevels"));
    }

    /**
     * setLevelDimensions extracts the x and y dimensions of the level maps from
     * the level file and sets the variables xDimension and yDimension.
     */
    private void setLevelDimensions() {
	NodeList levels = gameLevels.getElementsByTagName("levels");
	Element levelsInfo = (Element) levels.item(0);
	xDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionX"));
	yDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionY"));
    }

    /**
     * getRoad extracts all the walkable positions, road, start and goal, of a
     * given level from the level file. Constructs terrain objects using these
     * positions, saves the objects in a Terrain ArrayList and returns the
     * array.
     * 
     * @param currentLevel - the level to extract the positions from
     * @return an ArrayList with walkable Terrain objects or null
     */
    public CopyOnWriteArrayList<Terrain> getRoad(int currentLevel) {

	CopyOnWriteArrayList<Terrain> road;
	NodeList positions;
	Element level;
	Element terrainRoad;
	int levelNr;
	NodeList levels = gameLevels.getElementsByTagName("level");
	for (int i = 0; i < levels.getLength(); i++) {
	    level = (Element) levels.item(i);
	    levelNr = Integer.parseInt(level.getAttribute("levelNumber"));
	    if (levelNr == currentLevel) {
		terrainRoad = (Element) level.getElementsByTagName("road")
			.item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road = new CopyOnWriteArrayList<Terrain>();
		for (int m = 0; m < (positions.getLength()); m++) {
		    road.add(
			    getRoadObject("road", (Element) positions.item(m)));
		}
		terrainRoad = (Element) level.getElementsByTagName("start")
			.item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road.add(getRoadObject("start", (Element) positions.item(0)));
		terrainRoad = (Element) level.getElementsByTagName("goal")
			.item(0);
		positions = terrainRoad.getElementsByTagName("position");
		road.add(getRoadObject("goal", (Element) positions.item(0)));
		return road;
	    }
	}
	return null;
    }

    /**
     * getRoadObject creates and returns an instance of a Terrain object with a
     * given position and type.
     * 
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

	if (type.equals("start")) {
	    road = new Start(new Position(x, y), null);
	} else if (type.equals("goal")) {
	    road = new Goal(new Position(x, y));
	} else {
	    road = new Road(new Position(x, y));
	}
	return road;
    }

    /**
     * getGrass extracts all the grass positions of a given level from the 
     * level file. Constructs grass objects using these positions and saves the
     * objects in a Terrain array and returns the array.
     * 
     * @param currentLevel - the level to extract the positions from
     * @return - an array of Grass objects or null
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
	for (int i = 0; i < levels.getLength(); i++) {
	    level = (Element) levels.item(i);
	    levelNr = Integer.parseInt(level.getAttribute("levelNumber"));
	    if (levelNr == currentLevel) {
		terrainGrass = (Element) level.getElementsByTagName("grass")
			.item(0);
		positions = terrainGrass.getElementsByTagName("position");
		grass = new Terrain[positions.getLength()];
		for (int m = 0; m < positions.getLength(); m++) {
		    position = (Element) positions.item(m);
		    x = Integer.parseInt(position.getAttribute("x"));
		    y = Integer.parseInt(position.getAttribute("y"));
		    grass[m] = new Grass(new Position(x, y));
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
     * 
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
	for (int i = 0; i < levels.getLength(); i++) {
	    level = (Element) levels.item(i);
	    if (Integer.parseInt(
		    level.getAttribute("levelNumber")) == currentLevel) {
		credits = Integer.parseInt(level
			.getElementsByTagName("credits").item(0)
			.getTextContent());
		winScore = Integer.parseInt(level
			.getElementsByTagName("winScore").item(0)
			.getTextContent());
		levelStats = new LevelStats(winScore, credits);
		return levelStats;
	    }
	}
	return null;
    }

    /**
     * gerNrOfTowers extracts information from the level file about the nr of
     * towers to be implemented in the given level.
     * 
     * @param currentLevel - the level to extract information about
     * @return - number of towers in the level
     */
    public int getNrOfTowers(int currentLevel) {
	NodeList levels = gameLevels.getElementsByTagName("level");
	Element level;
	int nrOfTowers;
	for (int i = 0; i < levels.getLength(); i++) {
	    level = (Element) levels.item(i);
	    if (Integer.parseInt(
		    level.getAttribute("levelNumber")) == currentLevel) {
		nrOfTowers = Integer.parseInt(level
			.getElementsByTagName("towers").item(0)
			.getTextContent());
		return nrOfTowers;
	    }
	}
	return 0;
    }

    /**
     * getUnits extracts information from the level file about which units that
     * are are supposed to be implemented in the given level.
     * 
     * @param currentLevel
     * @return - a string array of the units imlemented in the level or null
     */
    public String[] getUnits(int currentLevel) {
	String[] hasUnits;
	NodeList levels = gameLevels.getElementsByTagName("level");
	NodeList units;
	Element level;
	String unitName;
	for (int i = 0; i < levels.getLength(); i++) {
	    level = (Element) levels.item(i);
	    if (Integer.parseInt(
		    level.getAttribute("levelNumber")) == currentLevel) {
		units = level.getElementsByTagName("unit");
		hasUnits = new String[units.getLength()];
		for (int m = 0; m < units.getLength(); m++) {
		    unitName = units.item(m).getTextContent();
		    if (unitName.equals("FarmerUnit") || 
			    unitName.equals("SoldierUnit") || 
			    unitName.equals("TeleporterUnit")) {
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
     * 
     * @return - number of levels in level file
     */
    public int getNrOfLevels() {
	return nrOfLevels;
    }

    /**
     * getXDimension provides the x-dimension of the level maps
     * 
     * @return x-dimension of level maps
     */
    public int getXDimension() {
	return xDimension;
    }

    /**
     * getYDimension provides the y-dimension of the level maps
     * 
     * @return y-dimension of level maps
     */
    public int getYDimension() {
	return yDimension;
    }
}
