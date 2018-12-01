package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.*;

public class SchedinaDAO {
	
	// 1) Create
	//	  Nota: restituisce l'ID generato dal DB, il chiamante lo deve assegnare alla schedina
	public static int create(Bet b) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		int generated_ID = -1;
		
		try {
			s = conn.prepareStatement("INSERT INTO SCHEDINE (numGiornata,orarioScadenza,presenza_esiti," + 
					"match1,match2,match3,match4,match5,match6,match7,match8,match9,match10," + 
					"esito_match1,esito_match2,esito_match3,esito_match4,esito_match5,esito_match6," + 
					"esito_match7,esito_match8,esito_match9,esito_match10) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, b.getNumGiornata());
			s.setString(2, b.getOrarioScadenza().toString());
			s.setBoolean(3, false);
			
			for(int i=0; i<10; i++)
				s.setString(i+4, b.getGameList().get(i).getSquadre());
			
			for(int i=0; i<10; i++)
				s.setString(i+14, null);
			
			s.executeUpdate();
			
			ResultSet rs = s.getGeneratedKeys();
		    if(rs.next()) {
		    	generated_ID = rs.getInt(1);
		    }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return generated_ID;
	}
	
	// 2) Read
	public static Bet read(int ID) throws SQLException {
		Bet bet = null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM SCHEDINE WHERE ID=?");
			s.setInt(1, ID);
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				int numGiornata = rs.getInt("numGiornata");
				LocalDateTime orarioScadenza = LocalDateTime.parse(rs.getString("orarioScadenza"), DateTimeFormatter.ISO_DATE_TIME);
				ArrayList<Game> gameList = new ArrayList<Game>();				
				
				boolean presenza_esiti = rs.getBoolean("presenza_esiti");				
				for(int i=1; i<11; i++)
					gameList.add(new Game(rs.getString("match"+i), null, presenza_esiti?rs.getString("esito_match"+i):null));
								
				bet = new Bet(ID, numGiornata, orarioScadenza, gameList, null);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return bet;		
	}
	
	// 3) Update
	//	  Richiamato solo quando un admin inserisce gli esiti
	public static void update(Bet b) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			String comando = new String("UPDATE SCHEDINE SET presenza_esiti=1, ");
			for(int i=1; i<11; i++) {
				comando = comando+"esito_match"+i+"=?";
				if(i<10) comando = comando+", ";
				else comando = comando+" WHERE ID=?";
			}
			
			s = conn.prepareStatement(comando);
			
			for(int i=1; i<11; i++)
				s.setString(i, b.getGameList().get(i-1).getRisultato());
			
			s.setInt(11, b.getID());
			
			s.executeUpdate();
			
			// Calcola i punteggi di tutti i pronostici associati alla schedina b.
			// Di conseguenza, vengono aggiornati anche i punteggi totali degli utenti.
			PronosticoDAO.calcolaPunti(b);
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	// Restituisce l'ultima schedina da giocare
	public static Bet getToPlayBet() throws SQLException {
		Bet bet = null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM SCHEDINE WHERE presenza_esiti=0");
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				int ID = rs.getInt("ID");
				int numGiornata = rs.getInt("numGiornata");
				LocalDateTime orarioScadenza = LocalDateTime.parse(rs.getString("orarioScadenza"), DateTimeFormatter.ISO_DATE_TIME);
				ArrayList<Game> gameList = new ArrayList<Game>();				
				
				for(int i=1; i<11; i++)
					gameList.add(new Game(rs.getString("match"+i), null, null));
								
				bet = new Bet(ID, numGiornata, orarioScadenza, gameList, null);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return bet;		
	}
		

}
