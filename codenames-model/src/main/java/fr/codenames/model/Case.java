package fr.codenames.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="[case]")
public class Case {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAS_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="CAS_CARTE_ID")
	private Carte carte;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name="CAS_COULEUR")
	private Couleur couleur;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="CAS_GRILLE_ID")
	private Grille grille;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

}