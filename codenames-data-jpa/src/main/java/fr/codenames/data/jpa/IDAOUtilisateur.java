package fr.codenames.data.jpa;

import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.codenames.exception.AccountLockedException;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	@Query("select u from Utilisateur u where u.username = :username AND u.password = :password")
    public Utilisateur connexion(@Param("username") String username, @Param("password") String motDePasse);
}