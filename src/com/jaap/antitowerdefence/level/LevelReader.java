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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
	// 2. Kollar hur manga banor det finns och sÃ¤tter nrOfLevels
	this.levelFile = new File(levelFile);
	validateLevelFile();
	parseLevelFile();
	setNrOfLevels();
	setLevelDimensions();
    }
    
    private void validateLevelFile() {
	
	//Validerar XML-filen med validatorn
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
	    // Fel på newSchema
	    e.printStackTrace();
	} catch (IOException e) {
	    //Fel vid validering
	    e.printStackTrace();
	}
	
    }
    
    private void parseLevelFile() {
	//Initierar läsaren med xml-filen
	DocumentBuilder dBuilder;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	try {
	    dBuilder = dbFactory.newDocumentBuilder();
	    gameLevels = dBuilder.parse(levelFile);
	    gameLevels.getDocumentElement().normalize();
	} catch (SAXException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ParserConfigurationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    private void setNrOfLevels() {
	NodeList levels = gameLevels.getElementsByTagName("levels");
	Element levelsInfo = (Element) levels.item(0);
	nrOfLevels = Integer.parseInt(levelsInfo.getAttribute("nrOfLevels"));	
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
	//Läser av alla gräspositioner och lägger till
	
    }
    
    public LevelStats getLevelStats(int currentLevel) {
	
    }
    
    public int getNrOfTowers(int currentLevel) {
	
    }
   
    public boolean[] hasUnits(int currentLevel){
	
    }*/
    
    public int getNrOfLevels(){
	return nrOfLevels;
    }
}
