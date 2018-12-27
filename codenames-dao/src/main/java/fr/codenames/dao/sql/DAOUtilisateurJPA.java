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

	public void connexion() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Saisir votre nom d'utilisateur : ");
		String username = sc.nextLine();
		System.out.print("Saisir votre mot de passe : ");
		String motDePasse = sc.nextLine();

		try {
			Query myQuery = em.createQuery("select u.password from Utilisateur u where u.username = :username");
			myQuery.setParameter("username", username);

			ResultSet myResult = (ResultSet) myQuery.getSingleResult();

			if (myResult.next()) {
				String MDP = myResult.getString(1);
				if (MDP.equals(motDePasse)) {
					if (Joueur.isBanni()) {
						throw new AccountLockedException();
					}
					System.out.println("Connexion réussie");
				} 
				
				else {
					System.out.println("Mot de Passe incorrect");

				}
			} 
			else {
				System.out.println("Nom d'utilisateur incorrect");
			}
			myResult = (ResultSet) myQuery.getSingleResult();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
//	public Utilisateur auth(String username, String password)
//			throws UsernameOrPasswordNotFoundException, AccountLockedException {
//		try {
//			TypedQuery<Utilisateur> myQuery = em.createQuery(
//					"select u from Utilisateur u where u.username = :username AND u.password = :password",
//					Utilisateur.class);
//
//			myQuery.setParameter("username", username);
//			myQuery.setParameter("password", password);
//
//			ResultSet myResult = (ResultSet) myQuery.getSingleResult();
//			
//			 if (myResult.next()) {
//				Utilisateur monUtilisateur = this.map(myResult);
//
//				if (monUtilisateur. == TypeUtilisateur.JOUEUR) {
//					if (((Joueur) monUtilisateur).isBanni()) {
//						throw new AccountLockedException();
//					}
//				}
//
//				return monUtilisateur;
//			}
//		}
//	
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		throw new UsernameOrPasswordNotFoundException();
//	}
//
//	
//	
//
//	public Utilisateur map(ResultSet result) throws SQLException {
//		Utilisateur monUtilisateur = null;
//
//		if (result.getInt("UTI_TYPE") == TypeUtilisateur.JOUEUR.ordinal()) {
//			monUtilisateur = new Joueur();
//			((Joueur) monUtilisateur).setPseudo(result.getString("JOU_PSEUDO"));
//			((Joueur) monUtilisateur).setBanni(result.getBoolean("JOU_BANNI"));
//			//((Joueur) monUtilisateur).setId(result.getInt(monUtilisateur.getId());
//		}
//
//		else {
//			monUtilisateur = new Administrateur();
//		}
//
//		// ASSOCIER LES VALEURS DE LA DB A L'OBJET
//		monUtilisateur.setId(result.getInt("UTI_ID"));
//		monUtilisateur.setNom(result.getString("UTI_NOM"));
//		monUtilisateur.setPrenom(result.getString("UTI_PRENOM"));
//		monUtilisateur.setUsername(result.getString("UTI_USERNAME"));
//
//		return monUtilisateur;
//	}
}