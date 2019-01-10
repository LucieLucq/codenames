package fr.codenames.dao;

import fr.codenames.model.Carte;

public interface IDAOCarte extends IDAO<Carte> {

	public Carte findByLibelle(String libelle);
}