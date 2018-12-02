package dao;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class UserDAO {
	
	// 1) Create
	public static void create(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try {
			s = conn.prepareStatement("INSERT INTO UTENTI VALUES (?,?,?,?,?,?)");
			s.setString(1, u.getUserID());
			
			s.setString(2, u.getNome_cognome());
			s.setString(3, u.getRuolo());
			s.setInt(4, 0);
			s.setNull(5, java.sql.Types.INTEGER);	// lastPlayedBet è null			
			if (u.getToPlayBet()==null) s.setNull(6, java.sql.Types.INTEGER);	// non esiste alcuna toPlayBet nel db
			else s.setInt(6, u.getToPlayBet().getID());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	// 2) Read
	//    Nota: se viene lanciata una UserNotFoundException significa che l'utente non è presente nel db,
	//			pertanto va fatta una Create dall'esterno.
	public static User read(String userID) throws SQLException, UserNotFoundException {
		User utente = null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM UTENTI WHERE FB_USER_ID=?");
			s.setString(1, userID);
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				String nome_cognome = rs.getString("nome_cognome");
				String ruolo = rs.getString("ruolo");
				int puntiTot = rs.getInt("puntiTot");	
				Bet lastPlayedBet = PronosticoDAO.read(rs.getInt("lastPlayedBet"));
				Bet toPlayBet = SchedinaDAO.read(rs.getInt("toPlayBet"));
				
				utente = new User(userID, nome_cognome, ruolo, puntiTot, lastPlayedBet, toPlayBet, null);
			} else {
				throw new UserNotFoundException();
			}
			
			
		} catch (SQLException|NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return utente;		
	}
	
	// 3) Update
	public static void update(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			s = conn.prepareStatement("UPDATE UTENTI SET ruolo=?, puntiTot=?, lastPlayedBet = ?, toPlayBet=? WHERE FB_User_ID=?");
			s.setString(1, u.getRuolo());
			s.setInt(2, u.getPuntiTot());
			
			if(u.getLastPlayedBet() == null) s.setNull(3, java.sql.Types.INTEGER);
			else s.setInt(3, u.getLastPlayedBet().getID());
			
			if(u.getToPlayBet() == null) s.setNull(4, java.sql.Types.INTEGER);
			else s.setInt(4, u.getToPlayBet().getID());
			
			s.setString(5, u.getUserID());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	// 4) Delete
	public static void delete(User u) throws SQLException{
		Bet b = u.getLastPlayedBet();
		if(b!=null) PronosticoDAO.delete(b);		
		
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE FROM UTENTI WHERE FB_User_ID=?");
			s.setString(1, u.getUserID());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	// Aggiorna il toPlayBet a tutti gli utenti
	public static void setToPlayBet(Bet b) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM UTENTI");
			ResultSet rs = s.executeQuery();
			
			ArrayList<String> userIDs = new ArrayList<String>();
			while (rs.next()) userIDs.add(rs.getString("FB_user_ID"));
			s.close();
			
			for(String userID:userIDs) {
				User u = UserDAO.read(userID);
				u.setToPlayBet(b);
				UserDAO.update(u);
			}
			
		} catch (SQLException|NullPointerException|UserNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}		
	}

}
