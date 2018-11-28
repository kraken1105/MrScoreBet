package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class ServerDatabase {
	
	private static Connection conn = null;
	
	private static void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/policy/MrScoreBet.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("[DEBUG] Connessione to SQLite stabilita.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
	
	private static void closeConnection() throws SQLException {
		if(ServerDatabase.conn!=null) ServerDatabase.conn.close();
	}
	
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
}
    
