package fr.codenames.dao.sql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

public class DAOCarteJPA implements IDAOCarte {
	private EntityManager em;

	public DAOCarteJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	/*
	 * public Carte map(ResultSet result) throws SQLException { Carte myCarte = new
	 * Carte();
	 * 
	 * // ASSOCIER LES VALEURS DE LA DB A L'OBJET
	 * myCarte.setId(result.getInt("CAR_ID"));
	 * myCarte.setLibelle(result.getString("CAR_LIBELLE"));
	 * 
	 * return myCarte; }
	 */

	public List<Carte> findAll() {
		return em.createQuery("select c from Carte c", Carte.class).getResultList();
	}

	public Carte findById(int id) {
		return em.find(Carte.class, id);
	}

	public Carte save(Carte entity) {
		// On démarre la transaction
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
		Carte myCarte = new Carte();
		myCarte.setId(id);
		this.delete(myCarte);
	}

	public void delete(Carte entity) {
		em.remove(em.merge(entity));
	}

	public Carte findByMot(String mot) {
		Carte maCarte;
		TypedQuery<Carte> myQuery = em.createQuery("select c from Carte c where c.libelle = :libelle ", Carte.class);
		myQuery.setParameter("libelle", mot);
		maCarte = myQuery.getSingleResult();
		System.out.print(
				" => Carte trouvée ! <= " + "\n" + "La carte est " + maCarte.getId() + " : " + maCarte.getLibelle());
		return maCarte;
	}

}
