package fr.codenames.model;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grille")
public class Grille {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRI_ID")
	private int id;
	
	@OneToMany
	@JoinColumn(name="GRI_CASE_ID")
	private ArrayList<Case> cases;
	
	@OneToMany(mappedBy="grille")
	private ArrayList<Partie> parties;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name="GRI_DIFFICULTE")
	private Difficulte difficulte;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
}