package model;

public class User {
	private int userID;
	private String nome;
	private String cognome;
	private String ruolo;
	private int puntiTot;	
	private Bet lastPlayedBet;
	private Bet toPlayBet;
	
	public User(int userID, String nome, String cognome, String ruolo, int puntiTot) {
		super();
		this.userID = userID;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
		this.puntiTot = puntiTot;
		// TO-DO: settare anche ultima e nuova bolletta nel costruttore
	}

	public int getPuntiTot() {return puntiTot;}
	public void setPuntiTot(int puntiTot) {this.puntiTot = puntiTot;}
	public int getUserID() {return userID;}
	public String getNome() {return nome;}
	public String getCognome() {return cognome;}
	public String getRuolo() {return ruolo;}
	public Bet getLastPlayedBet() {return lastPlayedBet;}
	public void setLastPlayedBet(Bet lastPlayedBet) {this.lastPlayedBet = lastPlayedBet;}
	public Bet getToPlayBet() {return toPlayBet;}
	public void setToPlayBet(Bet toPlayBet) {this.toPlayBet = toPlayBet;}

}
