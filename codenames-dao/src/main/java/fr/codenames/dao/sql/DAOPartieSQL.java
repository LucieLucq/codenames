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
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Carte;
import fr.codenames.model.Grille;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;



public class DAOPartieSQL extends DAOSQL implements IDAOPartie {
	
	public Partie map(ResultSet result) throws SQLException {
		Partie p = new Partie();
		// Associer les valeurs de la db à l'objet
		p.setId(result.getInt("PAR_ID"));

		//rattacher la partie à la grille
		Grille g=new Grille (); 
		g.setId(result.getInt("PAR_GRILLE_ID"));
		p.setGrille(g);
		return p;
	}
	
	public List<Partie> findAll() {
		List<Partie> mesParties = new ArrayList<Partie>();
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("select * from Partie");

			while (myResult.next()) {
				Partie p = this.map(myResult);

				mesParties.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mesParties;
	}
	
	public Partie findById(int id) {
		Partie maPartie = null;
		try {
			this.connect();
			PreparedStatement myStatement = this.connection.prepareStatement("select * from Partie where PAR_ID= ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				maPartie = this.map(myResult);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maPartie;
	}
	
	public Partie save(Partie p) {
		try {
			// gerer la modification d'un produit
			this.connect();
			String myQuery = "";
			if (p.getId() == 0) {
				myQuery = "insert into Partie (PAR_GRILLE_ID) values (?) ";

			} else {
				myQuery = "update Partie  set PAR_GRILLE_ID=?"
						+ " where PAR_ID=? ";
			}

			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setInt(1, p.getGrille().getId());
			
			if (p.getId() > 0) {
				myStatement.setInt(2, p.getId());
			}
			myStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;

	}

	public void deleteById(int id) {
		try {

			this.connect();
			PreparedStatement myStatement = this.connection.prepareStatement("delete from Partie where PAR_ID =? ");
			myStatement.setInt(1, id);
			myStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Partie p) {
		this.deleteById(p.getId());
	}

	}