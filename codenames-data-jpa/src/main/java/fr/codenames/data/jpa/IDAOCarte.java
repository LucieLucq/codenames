package fr.codenames.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Carte;



public interface IDAOCarte extends JpaRepository<Carte, Integer> {

	public Carte findByLibelle(String libelle);
}