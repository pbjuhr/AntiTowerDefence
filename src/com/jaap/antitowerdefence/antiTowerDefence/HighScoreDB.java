package com.jaap.antitowerdefence.antiTowerDefence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * HighScoreDB.java
 * 
 * TODO: BESKRIVNING, om uppkoppling till databasen misslyckas?
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class HighScoreDB {


    /*Variables*/
    Connection conn;
    PreparedStatement addHighScore;
    PreparedStatement getHighScore;

    /**
     * Constructor connects the database
     */
    public HighScoreDB() {
	connectToDB();
	setPreparedStatments();
    }

    /**
     * connectToDB connects to the database using a passwort, a username, a URI
     * and a mySQL JDBC driver.
     */
    private void connectToDB() {
	Properties connectionProps = new Properties();
	connectionProps.put("user", "root");
	connectionProps.put("password", "Anngurt_8580");

	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(
		    "jdbc:mysql://localhost:3306/atd_high_score",
		    connectionProps);
	} catch (SQLException e) {
	    //TODO: Check system.exit()
	    System.out.println("Error: unable connect to database!");
	    System.exit(1);
	} catch(ClassNotFoundException ex) {
	    System.out.println("Error: unable to load driver class!");
	    System.exit(1);
	}
    }

    /**
     * addScore checks the name for invalid characters and if the name is valid,
     * adds the score to the database.
     * @param name - player name
     * @param score - player score
     * @return success if the score was added, invalid if name containd invalid
     * characters, fail if the database insertion failed
     */
    public String addScore(String name, int score) {
	if(name.contains(";") || name.contains("'") || name.contains("--") || 
		name.contains("xp_") || name.contains("/*")){
	    return "invalid";
	}
	try {
	    addHighScore.setString(1, name);
	    addHighScore.setInt(2, score);
	    addHighScore.executeUpdate();
	} catch (SQLException e) {
	    return "fail";
	}
	return "success";
    }

    /**
     * getHighScoreTopTen retrives the ten highest scores from the database, 
     * saves the result(name, score) in a String matrix and returns the result
     * @return A String matrix with the result or null
     */
    public String[][] getHighScoreTopTen() {
	ResultSet topTen;
	int i = 0;
	String[][] highScore = new String[10][2];
	try {
	    topTen = getHighScore.executeQuery();
	    while (topTen.next()) {
		highScore[i][0] = topTen.getString("Name");
		highScore[i][1] = Integer.toString(topTen.getInt("Score"));
		i++;
	    }
	} catch (SQLException e) {
	    return null;
	}
	return highScore;
    }

    /**
     * isHighScore checks if a given score is a valid highscore, top ten or 
     * higher
     * @param score - The score to check
     * @return true if the score qualifies, false is the score does not
     * qualify, fail if the communication with the database fails
     */
    public String isHighScore(int score) {
	ResultSet topTen;
	int i = 0;
	try {
	    topTen = getHighScore.executeQuery();
	    while (topTen.next()) {
		if(score > topTen.getInt("Score")){
		    return "true";
		}
		i++;
	    }
	} catch (SQLException e) {
	    return "fail";
	}
	if(i < 10){
	    return "true";
	}
	return "false";
    }

    /**
     * setPreparedStatements constructs prepared SQL-statements for inserting
     * a highscore into the database and for retrieveing the ten highest scores
     * from the database.
     */
    private void setPreparedStatments() {
	try {
	    addHighScore = conn.prepareStatement(
		    "INSERT INTO HighScore (Name,Score) VALUES (?,?);");
	    getHighScore = conn.prepareStatement(
		    "SELECT * FROM HighScore ORDER BY Score DESC limit 10;");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
