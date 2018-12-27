package fr.codenames.dao.sql;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOJPA {
		protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("NomPersistenceUnit");
		protected EntityManager em = emf.createEntityManager();

		public void close() {
			em.close();
			emf.close();
		}
}