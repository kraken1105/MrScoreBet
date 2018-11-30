package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bet {
	public Integer ID;
	private int numGiornata;
	private LocalDateTime orarioScadenza;
	private ArrayList<Game> gameList;
	private Integer punti;	// null se non sono ancora stati pubblicati i risultati
	
	public Bet(Integer ID, int numGiornata, LocalDateTime orarioScadenza, ArrayList<Game> gameList, Integer punti) {
		super();
		this.ID = ID;
		this.numGiornata = numGiornata;
		this.orarioScadenza = orarioScadenza;
		this.gameList = gameList;
		this.punti = punti;
	}	
	
	public Integer getID() {return ID;}
	public void setID(Integer ID) {this.ID = ID;}
	public int getNumGiornata() {return numGiornata;}
	public void setNumGiornata(int numGiornata) {this.numGiornata = numGiornata;}
	public ArrayList<Game> getGameList() {return gameList;}
	public void setGameList(ArrayList<Game> gameList) {this.gameList = gameList;}
	public LocalDateTime getOrarioScadenza() {return orarioScadenza;}
	public void setOrarioScadenza(LocalDateTime orarioScadenza) {this.orarioScadenza = orarioScadenza;}
	public Integer getPunti() {return punti;}
	public void setPunti(Integer punti) {this.punti = punti;}		

}
