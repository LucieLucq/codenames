package fr.codenames.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="participation")
public class Participation {
	
	
	private Partie partie;
	
	
	private Joueur joueur;
	
	
	private Role role;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}