package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bet {
	private int numGiornata;
	private ArrayList<Game> gameList;
	private LocalDateTime orarioScadenza;
	private int punti;	// null se non sono ancora stati pubblicati i risultati
	
	public Bet(int numGiornata, ArrayList<Game> gameList, LocalDateTime orarioScadenza, int punti) {
		super();
		this.numGiornata = numGiornata;
		this.gameList = gameList;
		this.orarioScadenza = orarioScadenza;
		this.punti = punti;
	}

	public int getNumGiornata() {return numGiornata;}
	public void setNumGiornata(int numGiornata) {this.numGiornata = numGiornata;}
	public ArrayList<Game> getGameList() {return gameList;}
	public void setGameList(ArrayList<Game> gameList) {this.gameList = gameList;}
	public LocalDateTime getOrarioScadenza() {return orarioScadenza;}
	public void setOrarioScadenza(LocalDateTime orarioScadenza) {this.orarioScadenza = orarioScadenza;}
	public int getPunti() {return punti;}
	public void setPunti(int punti) {this.punti = punti;}		

}
