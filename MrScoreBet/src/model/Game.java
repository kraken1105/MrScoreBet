package model;

public class Game {
	private String squadre;
	private char pronostico;	// null se l'istanza è una schedina da giocare
	private char risultato;		// null se l'istanza è una schedina giocata
	
	public Game(String squadre, char pronostico, char risultato) {
		super();
		this.squadre = squadre;
		this.pronostico = pronostico;
		this.risultato = risultato;
	}

	public String getSquadre() {return squadre;}
	public void setSquadre(String squadre) {this.squadre = squadre;}
	public char getPronostico() {return pronostico;}
	public void setPronostico(char pronostico) {this.pronostico = pronostico;}
	public char getRisultato() {return risultato;}
	public void setRisultato(char risultato) {this.risultato = risultato;}
	
}
