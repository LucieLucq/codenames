package fr.codenames.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

public class DAOCarteSQL extends DAOSQL implements IDAOCarte {
	public Carte map(ResultSet result) throws SQLException {
		//pour factoriser par la suite	
			Carte c = new Carte();
			
			// Associer les valeurs de la db à l'objet
			c.setId(result.getInt("CAR_ID"));
			c.setLibelle(result.getString("CAR_LIBELLE"));
			
			return c;
		}

		public List<Carte> findAll() {
			
			List<Carte> mesCartes = new ArrayList<Carte>();
			
			try {
				this.connect();
				Statement myStatement = this.connection.createStatement();
				ResultSet myResult = myStatement.executeQuery("select * from carte");

				while (myResult.next()) {
					Carte c = this.map(myResult);

					// ajout de la carte dans la liste
					mesCartes.add(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return mesCartes;
		}

		public Carte findById(int id) {
			
			Carte maCarte = null;
			
			try {
				this.connect();
				
				PreparedStatement myStatement = this.connection
						.prepareStatement("select * from carte where CAR_ID= ?");
				myStatement.setInt(1, id);
				ResultSet myResult = myStatement.executeQuery();

				if (myResult.next()) {
					maCarte = this.map(myResult);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return maCarte;
		}

		public Carte save(Carte c) {
			
			try {
				
				// gérer la modification d'une carte
				this.connect();
				String myQuery = "";
				if (c.getId() == 0) {
					myQuery = "insert into carte (CAR_LIBELLE) values (?)";

				} else {
					myQuery = "update carte set CAR_LIBELLE= ? where CAR_ID=? ";
				}

				PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

				myStatement.setString(1, c.getLibelle());
			

				if (c.getId() > 0) {
					myStatement.setInt(2, c.getId());
				}
				myStatement.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return c;

		}

		public void deleteById(int id) {
			try {

				this.connect();
				PreparedStatement myStatement = this.connection
						.prepareStatement("delete from carte where CAR_ID =? ");
				myStatement.setInt(1, id);
				myStatement.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void delete(Carte c) {
			this.deleteById(c.getId());
		}
	}