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

import com.jaap.antitowerdefence.terrain.Terrain;

/*import com.jaap.antitowerdefence.antiTowerDefence.Position;
import com.jaap.antitowerdefence.terrain.Terrain;*/

/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class LevelReader {

    private File levelFile;
    private Document gameLevels;
    private int nrOfLevels; // nr of maps in file
    private int xDimension;
    private int yDimension;

    public LevelReader(String levelFile) {
	// 1. Skapar xml-lasaren med level-strangen
	// 2. Kollar hur manga banor det finns och s√§tter nrOfLevels
	this.levelFile = new File(levelFile);
	validateLevelFile();
	parseLevelFile();
	setNrOfLevels();
	setLevelDimensions();
    }

    private void validateLevelFile() {

	//Validerar XML-filen med validatorn 
	// HANTERA OM EJ VALIDERING G≈R IGENOM!!
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
	    System.out.println("Fel vid validering av filen");
	}

    }

    private void parseLevelFile() {
	//Initierar l‰saren med xml-filen
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

    private void setNrOfLevels() {
	Element levels = 
		(Element)gameLevels.getElementsByTagName("levels").item(0);
	nrOfLevels = Integer.parseInt(levels.getAttribute("nrOfLevels"));	
    }

    private void setLevelDimensions() {
	NodeList levels = gameLevels.getElementsByTagName("levels");
	Element levelsInfo = (Element)levels.item(0);
	xDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionX"));
	yDimension = Integer.parseInt(levelsInfo.getAttribute("dimensionY"));
    }

    public Terrain[][] getMap(int currentLevel) {
	Terrain[][] map = new Terrain[xDimension][yDimension];

	return map;
    }

    /*  public Position[] getPossibleTowerPositions(int currenLevel) {
	//L‰ser av alla gr‰spositioner och l‰gger till

    }*/

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

    /*public int getNrOfTowers(int currentLevel) {

    }

    public boolean[] hasUnits(int currentLevel){

    }*/

    public int getNrOfLevels(){
	return nrOfLevels;
    }
}
