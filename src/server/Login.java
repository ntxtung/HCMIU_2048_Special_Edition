package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import game.Player;

public class Login {
	
	private static Login instance = null;

	private static final String dbServer = "localhost";
	private static final String dbName   = "2048";
	//private static final int dbPort = 3306;
	private static final String dbUsername = "2048";
	private static final String dbPassword = "2048";
	private static Connection connection = null;
	private static Statement statement;
	private static ResultSet resultSet;
	
	private Login() {
		connectDB(); 
	}
	
	public static Login getInstance() {
		if (instance == null)
			instance = new Login();
		return instance;
	}
	
	private static boolean connectDB(String dbServer, String dbName, String dbUsername, String dbPassword) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s&useSSL=false", 
																dbServer, dbName, dbUsername, dbPassword));
			statement = connection.createStatement();
			
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false; //exception
		} 
		
	}
	
	private static boolean connectDB() {
		return connectDB(dbServer, dbName, dbUsername, dbPassword);
	}
	
	public static boolean isLoginValid(String username, String password) {
		if (connection == null) {
			connectDB();
			return isLoginValid(username, password);
		} else {
			try {
				//resultSet = statement.executeQuery("SELECT * FROM account WHERE username=2048");
				PreparedStatement sql = connection.prepareStatement("SELECT * FROM account WHERE username=? AND password=? LIMIT 1");
				sql.setString(1, username);
				sql.setString(2, password);
				resultSet = sql.executeQuery();
				
				//System.out.println(resultSet.toString());
				
				// CHECK VALIDITY 
				//System.out.println("ROW = " + resultSet.getRow());
				if (resultSet.next()) 
					return true;
				else
					return false;
				/////////////////
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public static Player getPlayerInfo(String username, String password) {
		if (isLoginValid(username, password)) {
			try {
				int playerId 	  = resultSet.getInt	(1);
				String playerName = resultSet.getString (2);
				int level 		  = resultSet.getInt	(3);
				Player player = new Player(playerId, playerName, level);
				return player;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
}
