package fr.codenames.model;

public class Case {
	private int id;
	private Couleur couleur;
	private Carte carte;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}
}