package fr.codenames.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="difficulte")
public enum Difficulte {
	FACILE,
	MOYEN,
	DIFFICILE
}
