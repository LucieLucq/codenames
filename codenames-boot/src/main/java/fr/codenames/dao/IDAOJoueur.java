package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Joueur;



public interface IDAOJoueur extends JpaRepository<Joueur, Integer> {

}
