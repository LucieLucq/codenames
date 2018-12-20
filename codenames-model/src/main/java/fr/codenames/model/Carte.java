package fr.codenames.model;

public class Carte {
	private int id;
	private String libelle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public static Carte creerMot(int id, String libelle) {
		Carte c=new Carte();
		c.setId(id);
		c.setLibelle(libelle);
		return c;
	}
		
}