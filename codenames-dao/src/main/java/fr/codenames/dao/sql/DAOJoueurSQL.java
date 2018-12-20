package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.model.Grille;
import fr.codenames.model.Joueur;
import fr.codenames.model.Utilisateur;

public class DAOJoueurSQL extends DAOSQL  implements IDAOJoueur {

	public Joueur map(ResultSet result) throws SQLException {
		Joueur j = new Joueur();
		// Associer les valeurs de la db à l'objet
		j.setId(result.getInt("JOU_ID"));
		j.setPseudo(result.getString("JOU_PSEUDO"));
		j.setBanni(result.getBoolean("JOU_BANNI"));
	
		
		//rattacher un joueur à un utilisateur
				Utilisateur u=new Utilisateur (); 
				u.setId(result.getInt("JOU_UTILISATEUR_ID"));
				j.setUtilisateur(u);
				return j;
	}

	public List<Joueur> findAll() {
		List<Joueur> mesJoueurs = new ArrayList<Joueur>();
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("select * from Joueur");

			while (myResult.next()) {
				Joueur j = this.map(myResult);

				// ajout du produit dans la liste
				mesJoueurs.add(j);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mesJoueurs;
	}

	public Joueur findById(int id) {
		Joueur monJoueur = null;
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("select * from Joueur where JOU_ID= ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				monJoueur = this.map(myResult);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monJoueur;
	}

	public Joueur save(Joueur j) {
		try {
			// gerer la modification d'un joueur
			this.connect();
			String myQuery = "";
			if (j.getId() == 0) {
				myQuery = "insert into Joueur ( JOU_PSEUDO, JOU_BANNI, JOU_UTILISATEUR_ID) values (?,?,?) ";

			} else {
				myQuery = "update Joueur set JOU_PSEUDO= ? ," + " JOU_BANNI=? ,"+ "JOU_UTILISATEUR_ID=?"  + " where JOU_ID=? ";
			}

			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setString(1, j.getPseudo());
			myStatement.setBoolean(2, j.isBanni());
			// on precise l'identifiant de l'utilisateur pour ce joueur
			myStatement.setInt(3, j.getUtilisateur().getId());
	
			if (j.getId() > 0) {
				myStatement.setInt(4, j.getId());
			}
			myStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return j;

	}

	public void delete(Joueur entity) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}


}
