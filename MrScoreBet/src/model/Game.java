package model;

public class Game {
	private String squadre;
	private String pronostico;	// null se l'istanza è una schedina da giocare
	private String risultato;	// null se l'istanza è una schedina giocata
	
	public Game(String squadre, String pronostico, String risultato) {
		super();
		this.squadre = squadre;
		this.pronostico = pronostico;
		this.risultato = risultato;
	}

	public String getSquadre() {return squadre;}
	public void setSquadre(String squadre) {this.squadre = squadre;}
	public String getPronostico() {return pronostico;}
	public void setPronostico(String pronostico) {this.pronostico = pronostico;}
	public String getRisultato() {return risultato;}
	public void setRisultato(String risultato) {this.risultato = risultato;}
	
}
