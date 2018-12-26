package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

public class DAOCarteJPA extends DAOJPA implements IDAOCarte {
private EntityManager em;
	
	
	public DAOCarteJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	public Carte map(ResultSet result) throws SQLException {
		Carte myCarte = new Carte();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myCarte.setId(result.getInt("CAR_ID"));
		myCarte.setLibelle(result.getString("CAR_LIBELLE"));
		
		return myCarte;
	}
	
	
	public List<Carte> findAll() {
		return em
				.createQuery("select c from Carte c", Carte.class)
				.getResultList();
	}
	
	
	public Carte findById(int id) {
		return em.find(Carte.class, id);
	}
	
	
	public Carte save(Carte entity) {
		//On démarre la transaction
				em.getTransaction().begin();
				
				if (entity.getId() == 0) {
					em.persist(entity);
				}
				
				else {
					entity = em.merge(entity);
				}
				
				//On commit la transaction
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
}