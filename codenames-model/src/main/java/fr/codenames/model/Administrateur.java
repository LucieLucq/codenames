package fr.codenames.model;

public class Administrateur extends Utilisateur {
	private Utilisateur utilisateur;
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
