package model;

public class User {
	private String userID;
	private String nome;
	private String ruolo;
	private int puntiTot;	
	private Bet lastPlayedBet;
	private Bet toPlayBet;
	private Image image;
	
	public User(String userID, String nome, String ruolo, Image img, int puntiTot, Bet lastPlayedBet, Bet toPlayBet) {
		super();
		this.userID = userID;
		this.nome = nome;
		this.ruolo = ruolo;
		this.puntiTot = puntiTot;
		this.lastPlayedBet = lastPlayedBet;
		this.toPlayBet = toPlayBet;
		this.image=img;
	}
	
	public Image getImage() {return image;}
	public int getPuntiTot() {return puntiTot;}
	public void setPuntiTot(int puntiTot) {this.puntiTot = puntiTot;}
	public String getUserID() {return userID;}
	public String getNome() {return nome;}
	public String getRuolo() {return ruolo;}
	public Bet getLastPlayedBet() {return lastPlayedBet;}
	public void setLastPlayedBet(Bet lastPlayedBet) {this.lastPlayedBet = lastPlayedBet;}
	public Bet getToPlayBet() {return toPlayBet;}
	public void setToPlayBet(Bet toPlayBet) {this.toPlayBet = toPlayBet;}

}
