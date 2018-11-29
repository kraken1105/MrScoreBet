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
		String url = "jdbc:sqlite:C:/policy/MrScoreBet.db";        
		connection = DriverManager.getConnection(url);		
		return connection;
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	/*/**********************************************************************************
	public static void insert(String name, int score, boolean alreadyplayed) {
        String sql = "INSERT INTO UTENTI(name,score,alreadyplayed) "
        		+ "VALUES(?,?,?)";
        
        if(ServerDatabase.conn==null) {
        	ServerDatabase.connect();
        }
        	try {
        	PreparedStatement pstmt = ServerDatabase.conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.setBoolean(3, alreadyplayed);
            pstmt.executeUpdate();
        	} catch (SQLException e) {System.out.println(e.getMessage());}
        }
	
	public static ArrayList<String> selectAll(String name){
        String sql = "SELECT name FROM UTENTI WHERE name='"+name+"'";
        ArrayList<String> results = new ArrayList<String>();
        
        if(ServerDatabase.conn==null) {
        	ServerDatabase.connect();
        }
        
        try {
        	Statement stmt  = ServerDatabase.conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                results.add(rs.getString("name"));
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        
        return results;
    }	
	************************************************************************************/
	
}
    
