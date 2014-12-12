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
 * A class that manages all communication with the Anti Tower Defence highscore
 * database. After the class has been initiated the function connectToDB has to 
 * be called to connect to the database.
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class HighScoreDB {


    /*Variables*/
    Connection conn;			//The database connection
    PreparedStatement addHighScore;	//SQL-query to add a score
    PreparedStatement getHighScore;	//SQL-query to retrieve top ten scores

    /**
     * Constructor initiates an object of the class
     */
    public HighScoreDB() {

    }

    /**
     * connectToDB connects to the database using a passwort, a username, a URI
     * and a mySQL JDBC driver. If this is successful it sets the prepared
     * statments
     * @return true if the connection was successful, false if not
     */
    public boolean connectToDB() {
	Properties connectionProps = new Properties();
	connectionProps.put("user", "v135h14g13");
	connectionProps.put("password", "Sheoja8a");

	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(
		    "jdbc:mysql://mysql.cs.umu.se:3306/v135h14g13",
		    connectionProps);
	    setPreparedStatments();
	} catch (SQLException e) {
	    return false;
	} catch(ClassNotFoundException ex) {
	    return false;
	}
	return true;
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
		highScore[i][0] = topTen.getString("name");
		highScore[i][1] = Integer.toString(topTen.getInt("score"));
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
		if(score > topTen.getInt("score")){
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
     * @throws SQLException 
     */
    private void setPreparedStatments() throws SQLException {

	addHighScore = conn.prepareStatement(
		"INSERT INTO HighScore (name,score) VALUES (?,?);");
	getHighScore = conn.prepareStatement(
		"SELECT * FROM HighScore ORDER BY score DESC limit 10;");

    }
}
