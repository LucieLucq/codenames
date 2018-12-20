package fr.codenames.model;

import java.util.ArrayList;

public class Partie {
	

	private int id;
	private Grille grille;
	private Joueur capitaine;
	private ArrayList<Message> messages=new ArrayList<Message>();

	public int getId() {
		return id;
	}

	public Joueur getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(Joueur capitaine) {
		this.capitaine = capitaine;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public ArrayList<Message> getMessage() {
		return messages;
	}

	public void setMessage(ArrayList<Message> message) {
		this.messages = message;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static Partie creerPartie(int id ) {
		Partie p=new Partie();
		p.setId(id);

		return p;
	}
}
