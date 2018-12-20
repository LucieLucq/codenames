package fr.codenames.model;

import java.util.ArrayList;

public class Grille {
	private int id;
	private Difficulte difficulte;
	private ArrayList<Case> cases = new ArrayList<Case>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}

}