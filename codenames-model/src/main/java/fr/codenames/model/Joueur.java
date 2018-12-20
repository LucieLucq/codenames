package fr.codenames.model;

import fr.codenames.model.Joueur;

public class Joueur extends Utilisateur {
	private int id;
	private String pseudo;
	private boolean banni;
	private Utilisateur utilisateur;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public boolean isBanni() {
		return banni;
	}



	public void setBanni(boolean banni) {
		this.banni = banni;
	}



	public Utilisateur getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



	public static Joueur creerJoueur(int id, String pseudo, boolean banni) {
		Joueur j = new Joueur();
		j.setId(id);
		j.setPseudo(pseudo);
		j.setBanni(banni);
		return j;

	}
}
