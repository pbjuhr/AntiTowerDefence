package com.jaap.antitowerdefence.antiTowerDefence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class HighScoreDB {
    Connection conn;
    
    public HighScoreDB() {
	connectToDB();
    }

    private void connectToDB() {
	Properties connectionProps = new Properties();
	connectionProps.put("user", "root");
	connectionProps.put("password", "Anngurt_8580");
	try {
	    conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:80/atd_high_score",
	            connectionProps);
	} catch (SQLException e) {
	    // TODO Om uppkoppling misslyckas
	    e.printStackTrace();
	}
    }
    
    public void addScore(String name, int score) {

    }
/*
    public void removeScores() {

    }
*/
    public ArrayList<String> getScores() {
	return null;

    }

}
