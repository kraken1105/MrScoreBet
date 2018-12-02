package dao;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class PronosticoDAO {
	
	// 1) Create
	//	  Nota: restituisce l'ID generato dal DB, il chiamante lo deve assegnare alla schedina.
	//			Questo metodo viene invocato unicamente quando un utente gioca una nuova schedina.			
	public static int create(Bet p, Bet schedina) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		int generated_ID = -1;
		
		try {
			s = conn.prepareStatement("INSERT INTO PRONOSTICI (schedina_ID,punti_calcolati,punti_pronostici," + 
					"pron_match1,pron_match2,pron_match3,pron_match4,pron_match5,pron_match6,pron_match7," + 
					"pron_match8,pron_match9,pron_match10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, schedina.getID());
			s.setBoolean(2, false);
			s.setNull(3, java.sql.Types.INTEGER);
			
			for(int i=0; i<10; i++)
				s.setString(i+4, p.getGameList().get(i).getPronostico());
		
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
			s = conn.prepareStatement("SELECT * FROM PRONOSTICI WHERE ID=?");
			s.setInt(1, ID);
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				bet = SchedinaDAO.read(rs.getInt("schedina_ID"));
				bet.setID(ID);
				if (rs.getBoolean("punti_calcolati")) bet.setPunti(rs.getInt("punti_pronostici"));
				
				for(int i=1; i<11; i++)
					bet.getGameList().get(i-1).setPronostico(rs.getString("pron_match"+i));
				
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
	//	  Richiamato solo quando un admin inserisce gli esiti.
	public static void update(Bet b) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			s = conn.prepareStatement("UPDATE PRONOSTICI SET punti_calcolati=1, punti_pronostici=? WHERE ID=?");			
			s.setInt(1, b.getPunti());
			s.setInt(2, b.getID());
			s.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	
	// 4) Delete
		public static void delete(Bet b) throws SQLException{
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement s = null;
			
			try {
				s = conn.prepareStatement("DELETE FROM PRONOSTICI WHERE ID=?");
				s.setInt(1, b.getID());
				
				s.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (s != null) s.close();
			}
		}
		
	
	// Calcola i punteggi di tutti i pronostici riferiti a una data schedina (e li somma a quelli dell'utente)
	public static void calcolaPunti(Bet b) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null, s2 = null;
			
		try {
			s = conn.prepareStatement("SELECT * FROM PRONOSTICI WHERE SCHEDINA_ID=?");
			s.setInt(1, b.getID());			
			ResultSet rs = s.executeQuery();
			
			ArrayList<Bet> pronList = new ArrayList<Bet>();
			while (rs.next()) {
				pronList.add(PronosticoDAO.read(rs.getInt("ID")));
			}			
			s.close();
			
			for(Bet pron:pronList) {
				int pron_corretti = 0;				
				
				for(int i=0; i<10; i++)
					if (pron.getGameList().get(i).getPronostico().equals(b.getGameList().get(i).getRisultato()))
						pron_corretti++;
				
				// Assegna 10 punti per ogni risultato correttamente pronosticato, ed ulteriori 100 se sono tutti corretti
				pron.setPunti(pron_corretti*10 + ((pron_corretti==10)?100:0) );
				PronosticoDAO.update(pron);
				
				// Aggiorna il punteggio totale dell'utente
				s = conn.prepareStatement("SELECT * FROM UTENTI WHERE lastPlayedBet=?");
				s.setInt(1, pron.getID());				
				rs = s.executeQuery();
				
				User u = null;
				if (rs.next()) {
					u = UserDAO.read(rs.getString("FB_user_ID"));
					u.setPuntiTot(u.getPuntiTot()+pron.getPunti());
				}				
				s.close();	
				
				UserDAO.update(u);				
			}
			
			
		} catch (SQLException|UserNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
			if (s2 != null) s2.close();
		}
	}

}