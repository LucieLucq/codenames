package fr.codenames.dao.sql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Partie;

public class DAOPartieJPA implements IDAOPartie {
private EntityManager em;
	
	
	public DAOPartieJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	/*public Partie map(ResultSet result) throws SQLException {
		Partie myPartie = new Partie();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myPartie.setId(result.getInt("PAR_ID"));
		
		myPartie.setGrille(new Grille());
		myPartie.getGrille().setId(result.getInt("PAR_GRILLE_ID"));
		
		myPartie.setCapitaine(new Joueur());
		myPartie.getCapitaine().setId(result.getInt("PAR_CAPITAINE_ID"));
		
		return myPartie;
	}*/
	
	
	public List<Partie> findAll() {
		return em.createQuery("select p from Partie p", Partie.class).getResultList();
	}
	
	public Partie findById(int id) {
		return em.find(Partie.class, id);
	}
		
	
	public Partie save(Partie entity) {
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
	
	
	public void deleteById(int id) {
		Partie myPartie = new Partie();
		myPartie.setId(id);
		this.delete(myPartie);
	}
		
	
	public void delete(Partie entity) {
		em.remove(em.merge(entity));
	}
}