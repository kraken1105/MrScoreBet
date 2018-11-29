package model;

public class User {
	private String userID;
	private String nome_cognome;
	private String ruolo;
	private int puntiTot;	
	private Bet lastPlayedBet;
	private Bet toPlayBet;
	private Image image;
	
	public User() {}
	
	public User(String userID, String nome_cognome, String ruolo, int puntiTot, Bet lastPlayedBet, Bet toPlayBet,
			Image image) {
		super();
		this.userID = userID;
		this.nome_cognome = nome_cognome;
		this.ruolo = ruolo;
		this.puntiTot = puntiTot;
		this.lastPlayedBet = lastPlayedBet;
		this.toPlayBet = toPlayBet;
		this.image = image;
	}

	public String getUserID() {return userID;}
	public void setUserID(String userID) {this.userID = userID;}
	public String getNome_cognome() {return nome_cognome;}
	public void setNome_cognome(String nome_cognome) {this.nome_cognome = nome_cognome;}
	public String getRuolo() {return ruolo;}
	public void setRuolo(String ruolo) {this.ruolo = ruolo;}
	public int getPuntiTot() {return puntiTot;}
	public void setPuntiTot(int puntiTot) {this.puntiTot = puntiTot;}
	public Bet getLastPlayedBet() {return lastPlayedBet;}
	public void setLastPlayedBet(Bet lastPlayedBet) {this.lastPlayedBet = lastPlayedBet;}
	public Bet getToPlayBet() {return toPlayBet;}
	public void setToPlayBet(Bet toPlayBet) {this.toPlayBet = toPlayBet;}
	public Image getImage() {return image;}
	public void setImage(Image image) {this.image = image;}	

}
