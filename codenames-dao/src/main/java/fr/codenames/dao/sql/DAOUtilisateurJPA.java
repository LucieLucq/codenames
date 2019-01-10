package fr.codenames.dao.sql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Joueur;
import fr.codenames.model.Utilisateur;

public class DAOUtilisateurJPA implements IDAOUtilisateur {
	private EntityManager em;

	public DAOUtilisateurJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	public List<Utilisateur> findAll() {
		return em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
	}

	public Utilisateur findById(int id) {
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

	public Utilisateur connexion(String username, String motDePasse)
			throws AccountLockedException, UsernameOrPasswordNotFoundException {

		// JPQL -> entityManager
		// Ajouter les paramètres (username, mdp)
		// Récupérer l'Utilisateur -> UN SEUL UTILISATEUR
		// SI YA PAS DUSER -> EXCEPTION DE TYPE NoResultException
		// SI CA CATCH PAS, CEST QUE LUSER EXISTE
		
		Utilisateur monUtilisateur;
		try {
			TypedQuery<Utilisateur> myQuery = em.createQuery(
					"select u from Utilisateur u where u.username = :username AND u.password = :password",
					Utilisateur.class);
			myQuery.setParameter("username", username);
			myQuery.setParameter("password", motDePasse);

			monUtilisateur = myQuery.getSingleResult();
			if (monUtilisateur instanceof Joueur) {
				if (((Joueur) monUtilisateur).isBanni()) {

					throw new AccountLockedException();

				}

			}

			return monUtilisateur;

		} catch (NoResultException e) {

			throw new UsernameOrPasswordNotFoundException();

		}

	}

	public Utilisateur findByLibelle(String Libelle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}