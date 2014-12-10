package com.jaap.antitowerdefence.antiTowerDefence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Anna Osterlund, id10aod
 *
 */
public class HighScoreDB {
    Connection conn;
    PreparedStatement addHighScore;
    PreparedStatement getHighScore;
    public HighScoreDB() {
	connectToDB();
	setPreparedStatments();
    }

    private void connectToDB() {
	Properties connectionProps = new Properties();
	connectionProps.put("user", "root");
	connectionProps.put("password", "Anngurt_8580");
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	 }
	 catch(ClassNotFoundException ex) {
	    System.out.println("Error: unable to load driver class!");
	    System.exit(1);
	 }
	
	try {
	    conn = DriverManager.getConnection(
		    "jdbc:mysql://localhost:3306/atd_high_score",
		    connectionProps);
	} catch (SQLException e) {
	    // TODO Om uppkoppling misslyckas
	    e.printStackTrace();
	}
    }

    public String addScore(String name, int score) {
	//Kolla följande?
	/*; ' -- xp_*/
	try {
	    addHighScore.setString(1, name);
	    addHighScore.setInt(2, score);
	    addHighScore.executeUpdate();
	} catch (SQLException e) {
	    return "fail";
	}
	return "success";
    }

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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
	return highScore;
    }
    
    public String isHighScore(int score) {
	ResultSet topTen;	
	try {
	    topTen = getHighScore.executeQuery();
	    while (topTen.next()) {
		if(score > topTen.getInt("Score")){
		    return "true";
		}
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return "fail";
	}
	 return "false";
    }

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
