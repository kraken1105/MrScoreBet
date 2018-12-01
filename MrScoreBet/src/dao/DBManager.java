package dao;

import java.sql.*;
 
public class DBManager {
	
	protected Connection connection;	
	private static DBManager instance = null;
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:sqlite:C:/utility/db/MrScoreBet.db";        
		connection = DriverManager.getConnection(url);		
		return connection;
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
}
    
