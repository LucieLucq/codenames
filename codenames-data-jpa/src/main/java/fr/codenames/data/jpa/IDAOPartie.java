package fr.codenames.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Partie;



public interface IDAOPartie extends JpaRepository<Partie, Integer> {

}
