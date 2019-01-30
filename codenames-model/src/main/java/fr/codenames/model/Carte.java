package fr.codenames.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;



@Entity
@Table(name="carte")
public class Carte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAR_ID")
	@JsonView(Views.Common.class)
	private int id;
	
	@Column(name="CAR_LIBELLE")
	@NotEmpty(message=" Attention : un mot doit etre saisi ! ")
	@NotNull
	@JsonView(Views.Carte.class)
	private String libelle;

	
	@OneToMany(mappedBy="carte")
	private List<Case> cases;
	
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
}