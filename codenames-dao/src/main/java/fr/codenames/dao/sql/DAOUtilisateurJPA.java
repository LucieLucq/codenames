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

	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
		TypedQuery<Utilisateur> myQuery = em.createQuery("select u from Utilisateur u where u.username = :username AND u.nom = :password", Utilisateur.class);
		
		myQuery.setParameter("username", username);
		myQuery.setParameter("password", password);
		
		System.out.println("Utilisateur connecté !");
		return myQuery.getSingleResult();
		
		if (myResult.next()) {
			Utilisateur myUtilisateur = this.map(myResult);
		if (myUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
			if (((Joueur) myUtilisateur).isBanni()) {
				throw new AccountLockedException();
			}
	}
	catch (Exception exception){
		System.out.println("Echec authentification.");
	}
		
//			throws UsernameOrPasswordNotFoundException, AccountLockedException {
//		try {
//			this.connect();
//			PreparedStatement myStatement = this.connection
//					.prepareStatement("SELECT * FROM utilisateur WHERE UTI_USERNAME = ? AND UTI_PASSWORD = ?");
//
//			myStatement.setString(1, username);
//			myStatement.setString(2, password);
//			ResultSet myResult = myStatement.executeQuery();
//
//			if (myResult.next()) {
//				Utilisateur myUtilisateur = this.map(myResult);

				if (myUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
					if (((Joueur) myUtilisateur).isBanni()) {
						throw new AccountLockedException();
					}
				}

				return myUtilisateur;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		throw new UsernameOrPasswordNotFoundException();
	}

	public Utilisateur map(ResultSet result) throws SQLException {
		Utilisateur myUtilisateur = null;

		if (result.getInt("UTI_TYPE") == TypeUtilisateur.JOUEUR.ordinal()) {
			myUtilisateur = new Joueur();
			((Joueur) myUtilisateur).setPseudo(result.getString("UTI_PSEUDO"));
			((Joueur) myUtilisateur).setBanni(result.getBoolean("UTI_BANNI"));
		}

		else {
			myUtilisateur = new Administrateur();
		}

		// ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myUtilisateur.setId(result.getInt("UTI_ID"));
		myUtilisateur.setNom(result.getString("UTI_NOM"));
		myUtilisateur.setPrenom(result.getString("UTI_PRENOM"));
		myUtilisateur.setUsername(result.getString("UTI_USERNAME"));

		return myUtilisateur;
	}
}