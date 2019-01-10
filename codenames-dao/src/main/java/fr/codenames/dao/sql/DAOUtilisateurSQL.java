package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.Utilisateur;

public class DAOUtilisateurSQL extends DAOSQL implements IDAOUtilisateur {

	public Utilisateur map(ResultSet result) throws SQLException {
		Utilisateur u = new Utilisateur();
		// Associer les valeurs de la db à l'objet
		u.setId(result.getInt("UTI_ID"));
		u.setNom(result.getString("UTI_NOM"));
		u.setPrenom(result.getString("UTI_PRENOM"));
		u.setUsername(result.getString("UTI_USERNAME"));
		u.setPassword(result.getString("UTI_PASSWORD"));
		return u;
	}

	public List<Utilisateur> findAll() {
		List<Utilisateur> mesUtilisateurs = new ArrayList<Utilisateur>();
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("select * from utilisateur");

			while (myResult.next()) {
				Utilisateur u = this.map(myResult);

				// ajout du produit dans la liste
				mesUtilisateurs.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mesUtilisateurs;
	}

	public Utilisateur findById(int id) {
		Utilisateur monUtilisateur = null;
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("select * from utilisateur where UTI_ID= ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				monUtilisateur = this.map(myResult);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monUtilisateur;
	}

	public Utilisateur save(Utilisateur u) {
		try {
			// gerer la modification d'un produit
			this.connect();
			String myQuery = "";
			if (u.getId() == 0) {
				myQuery = "insert into utilisateur ( UTI_NOM, UTI_PRENOM, UTI_USERNAME, UTI_PASSWORD) values (?,?,?,?) ";

			} else {
				myQuery = "update utilisateur set UTI_NOM= ? ," + " UTI_PRENOM=? ," + " UTI_USERNAME=? ,"
						+ " UTI_PASSWORD=? " + " where UTI_ID=? ";
			}

			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setString(1, u.getNom());
			myStatement.setString(2, u.getPrenom());
			myStatement.setString(3, u.getUsername());
			myStatement.setString(4, u.getPassword());

			if (u.getId() > 0) {
				myStatement.setInt(5, u.getId());
			}
			myStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;

	}

	public void deleteById(int id) {
		try {

			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("delete from Utilisateur where UTI_ID =? ");
			myStatement.setInt(1, id);
			myStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Utilisateur u) {
		this.deleteById(u.getId());
	}

	///////////////////////////////////////////// Connexion
	public void connexion() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Saisir votre nom d'utilisateur : ");
		String username = sc.nextLine();
		System.out.print("Saisir votre mot de passe : ");
		String motDePasse = sc.nextLine();

		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("select UTI_PASSWORD from utilisateur where UTI_USERNAME= ? ");
			myStatement.setString(1, username);

			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				String MDP = myResult.getString(1);
				if (MDP.equals(motDePasse)) {
					System.out.println("Connexion réussie");
				} else {
					System.out.println("Mot de Passe incorrect");

				}
			} else {
				System.out.println("Nom d'utilisateur incorrect");
			}
			myResult = myStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////// Inscription
	public void inscription() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Saisir votre nom : ");
		String nom = sc.nextLine();
		System.out.print("Saisir votre prenom : ");
		String prenom = sc.nextLine();
		System.out.print("Saisir votre nom d'utilisateur : ");
		String username = sc.nextLine();
		System.out.print("Saisir votre mot de passe : ");
		String password = sc.nextLine();
		System.out.print("Saisir votre pseudo : ");
		String pseudo = sc.nextLine();

		Utilisateur nouvelUtilisateur = new Utilisateur();
		Joueur nouveauJoueur = new Joueur();
		try {
			this.connect();

			PreparedStatement myStatement = this.connection.prepareStatement(
					"insert into utilisateur (UTI_NOM, UTI_PRENOM, UTI_USERNAME, UTI_PASSWORD) values (?,?,?,?)");
			myStatement.setString(1, nom);
			myStatement.setString(2, prenom);
			myStatement.setString(3, username);
			myStatement.setString(4, password);

			nouvelUtilisateur.setNom(nom);
			nouvelUtilisateur.setPrenom(prenom);
			nouvelUtilisateur.setUsername(username);
			nouvelUtilisateur.setPassword(password);
			myStatement.execute();

			PreparedStatement myStatement1 = this.connection
					.prepareStatement("select UTI_ID from utilisateur order by UTI_ID desc limit 0,1");
			ResultSet myResult = myStatement1.executeQuery();

			if (myResult.next()) {
			nouvelUtilisateur.setId(myResult.getInt("UTI_ID"));}

			PreparedStatement myStatement2 = this.connection
					.prepareStatement("insert into joueur (JOU_PSEUDO, JOU_UTILISATEUR_ID) values (?,?)");
			myStatement2.setString(1, pseudo);
			myStatement2.setInt(2, nouvelUtilisateur.getId());

			nouveauJoueur.setPseudo(pseudo);
			nouveauJoueur.setId(nouvelUtilisateur.getId());

			myStatement2.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}