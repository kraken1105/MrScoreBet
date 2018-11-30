package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Bet;
import model.Game;
import model.User;

public class testDAO {

	public static void main(String[] args) throws SQLException, UserNotFoundException {
		
		//********* Inserimento pronostico ****************
				ArrayList<Game> gameList = new ArrayList<Game>();
				gameList.add(new Game("Juventus-Inter", null, "X"));
				gameList.add(new Game("Napoli-Frosinone", null, "1"));
				gameList.add(new Game("Cagliari-Roma", null, "2"));
				gameList.add(new Game("Lazio-Sampdoria", null, "1"));
				gameList.add(new Game("Sassuolo-Fiorentina", null, "X"));
				gameList.add(new Game("Empoli-Bologna", null, "1"));
				gameList.add(new Game("Parma-Chievo", null, "1"));
				gameList.add(new Game("Udinese-Atalanta", null, "1"));
				gameList.add(new Game("Genoa-Spal", null, "X"));
				gameList.add(new Game("Milan-Torino", null, "X"));		
				Bet bet = new Bet(3, 15, LocalDateTime.of(2018, 12, 7, 20, 30), gameList, null);
				SchedinaDAO.update(bet);
				
				
				
				
				/*
				gameList = new ArrayList<Game>();
				gameList.add(new Game("Juventus-Inter", "X", null));
				gameList.add(new Game("Napoli-Frosinone", "1", null));
				gameList.add(new Game("Cagliari-Roma", "2", null));
				gameList.add(new Game("Lazio-Sampdoria", "1", null));
				gameList.add(new Game("Sassuolo-Fiorentina", "X", null));
				gameList.add(new Game("Empoli-Bologna", "1", null));
				gameList.add(new Game("Parma-Chievo", "1", null));
				gameList.add(new Game("Udinese-Atalanta", "1", null));
				gameList.add(new Game("Genoa-Spal", "X", null));
				gameList.add(new Game("Milan-Torino", "1", null));		
				Bet p = new Bet(1, 15, LocalDateTime.of(2018, 12, 7, 20, 30), gameList, null);
		
				User u = UserDAO.read("114147579601537");
		u.setLastPlayedBet(p);
		u.setToPlayBet(null);
		UserDAO.update(u);*/
		
		
		/*
		//*******************************************************************
		Bet bet = PronosticoDAO.read(1);
		System.out.println("Bet_ID="+bet.getID()+" secondaPartita="+bet.getGameList().get(1).getSquadre());
		
		
		
		
		
		//********* Inserimento Schedina da parte di un admin ****************
		ArrayList<Game> gameList = new ArrayList<Game>();
		gameList.add(new Game("Juventus-Inter", null, null));
		gameList.add(new Game("Napoli-Frosinone", null, null));
		gameList.add(new Game("Cagliari-Roma", null, null));
		gameList.add(new Game("Lazio-Sampdoria", null, null));
		gameList.add(new Game("Sassuolo-Fiorentina", null, null));
		gameList.add(new Game("Empoli-Bologna", null, null));
		gameList.add(new Game("Parma-Chievo", null, null));
		gameList.add(new Game("Udinese-Atalanta", null, null));
		gameList.add(new Game("Genoa-Spal", null, null));
		gameList.add(new Game("Milan-Torino", null, null));		
		Bet bet = new Bet(null, 15, LocalDateTime.of(2018, 12, 7, 20, 30), gameList, null);
		bet.setID(SchedinaDAO.create(bet));
		System.out.println("toPlayBet_ID="+bet.getID());
		
		
		//******** Login utente *************
		User utente = null;
		try {
			System.out.println("1");
			utente = UserDAO.read("1110");	// utente già presente nel db
			System.out.println("2");
		} catch(UserNotFoundException|SQLException e) {
			System.out.println("1new");
			///////////// [TO-DO] bisogna leggere il ruolo veramente
			utente = new User("1110", "Pierpaolo", "admin", 0, null, SchedinaDAO.getToPlayBet(), null);
			System.out.println("2new");
			UserDAO.create(utente); // creazione nuovo utente
			System.out.println("3new");
		}
		System.out.println("fine: "+utente.getUserID()+" "+utente.getNome_cognome());
		*/
		

	}

}
