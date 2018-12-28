package fr.codenames.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="utilisateur")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Utilisateur {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UTI_ID")
	private int id;
	
	@Column(name="UTI_NOM")
	@NotEmpty
	@NotNull
	private String nom;
	
	@Column(name="UTI_PRENOM")
	@NotEmpty
	@NotNull
	private String prenom;
	
	@Column(name="UTI_USERNAME")
	@NotEmpty
	@NotNull
	private String username;
	
	@Column(name="UTI_PASSWORD")
	@NotEmpty
	@NotNull
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}