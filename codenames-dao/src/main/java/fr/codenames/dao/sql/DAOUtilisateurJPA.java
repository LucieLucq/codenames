package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Administrateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.TypeUtilisateur;
import fr.codenames.model.Utilisateur;
import fr.codenames.exception.NonUniqueUsernameException;

public class DAOUtilisateurJPA extends DAOJPA implements IDAOUtilisateur {
	private EntityManager em;

	public DAOUtilisateurJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
	}

	public Utilisateur findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Utilisateur.class, id);
	}

	public Utilisateur save(Utilisateur entity) {
		em.getTransaction().begin();

		if (entity.getId() == 0) {
			em.persist(entity);
		}

		else {
			entity = em.merge(entity);
		}

		// On commit la transaction
		em.getTransaction().commit();

		return entity;
	}

	public void delete(Utilisateur entity) {
		// TODO Auto-generated method stub
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
	}

	public Utilisateur auth(String username, String password)
			throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
			TypedQuery<Utilisateur> myQuery = em.createQuery(
					"select u from Utilisateur u where u.username = :username AND u.password = :password",
					Utilisateur.class);

			myQuery.setParameter("username", username);
			myQuery.setParameter("password", password);

			ResultSet myResult = (ResultSet) myQuery.getSingleResult();
			
			 if (myResult.next()) {
				Utilisateur monUtilisateur = this.map(myResult);

				if (monUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
					if (((Joueur) monUtilisateur).isBanni()) {
						throw new AccountLockedException();
					}
				}

				return monUtilisateur;
			}
		}
	
		catch (SQLException e) {
			e.printStackTrace();
		}

		throw new UsernameOrPasswordNotFoundException();
	}

	
	

	public Utilisateur map(ResultSet result) throws SQLException {
		Utilisateur monUtilisateur = null;

		if (result.getInt("UTI_TYPE") == TypeUtilisateur.JOUEUR.ordinal()) {
			monUtilisateur = new Joueur();
			((Joueur) monUtilisateur).setPseudo(result.getString("UTI_PSEUDO"));
			((Joueur) monUtilisateur).setBanni(result.getBoolean("UTI_BANNI"));
		}

		else {
			monUtilisateur = new Administrateur();
		}

		// ASSOCIER LES VALEURS DE LA DB A L'OBJET
		monUtilisateur.setId(result.getInt("UTI_ID"));
		monUtilisateur.setNom(result.getString("UTI_NOM"));
		monUtilisateur.setPrenom(result.getString("UTI_PRENOM"));
		monUtilisateur.setUsername(result.getString("UTI_USERNAME"));

		return monUtilisateur;
	}
}