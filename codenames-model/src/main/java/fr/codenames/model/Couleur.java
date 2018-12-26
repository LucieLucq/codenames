package fr.codenames.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="couleur")
public enum Couleur {
	ROUGE,
	BLEUE,
	NEUTRE,
	NOIRE,
	MIXTE
}
