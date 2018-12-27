package fr.codenames;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.sql.DAOCarteJPA;

import fr.codenames.dao.sql.DAOPartieJPA;

import fr.codenames.dao.sql.DAOUtilisateurJPA;

import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Carte;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;
import fr.codenames.exception.NonUniqueUsernameException;

public class Principale {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NomPersistenceUnit");

	public static DAOUtilisateurJPA daoUtilisateur = new DAOUtilisateurJPA(emf);
	
	public static IDAOCarte daoCarte = new DAOCarteJPA(emf);
	public static IDAOPartie daoPartie = new DAOPartieJPA(emf);
	public static Utilisateur utilisateur;
	public static Scanner sc;
	
	public static void main(String[] args) {
		
		

		sc = new Scanner(System.in);

		connexion();

		sc.close();
	}

	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (� saisir)
	 */
	public static void connexion() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();

		if (username.equals("")) {
			inscription();
			return;
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.auth(username, password);
			System.out.println(" => Vous etes connect� ! ");
			menu();

		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}

	/**
	 * S'inscrire (cr�er un nouveau compte utilisateur)
	 */
	public static void inscription() {
		Joueur nouveauJoueur = new Joueur();

		System.out.print("Indiquer votre nom : ");
		nouveauJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre pr�nom : ");
		nouveauJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		nouveauJoueur.setPseudo(sc.nextLine());

		System.out.print("Indiquer le nom d'utilisateur : ");
		nouveauJoueur.setUsername(sc.nextLine());

		System.out.print("Indiquer le mot de passe : ");
		nouveauJoueur.setPassword(sc.nextLine());

		try {
			daoUtilisateur.save(nouveauJoueur);
		}

		catch (NonUniqueUsernameException e) {
			System.out.println("Le nom d'utilisateur est d�j� utilis� !");
		}

		connexion();
	}

	/**
	 * Affiche le menu et d�marre les sous-programmes
	 */
	public static void menu() {
		int menu = 0;

		do {
			System.out.println("================================================");
			System.out.println("|             MENU DE SELECTION                |");
			System.out.println("================================================");
			System.out.println("|Actions possibles :                           |");
			System.out.println("|           1.	Voir les cartes                |");
			System.out.println("|           2.	Ajouter une carte              |");
			System.out.println("|           3.  Modifier une carte             |");
			System.out.println("|           4.  Supprimer une carte            |");
			System.out.println("|           5.	D�marrer une nouvelle partie   |");
			System.out.println("|           6.	Lister les parties             |");
			System.out.println("|           7.	Se d�connecter                 |");
			System.out.println("================================================");
			System.out.println("Veuillez selectionner votre action : ");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("=> Action 1 selectionn�e <= ");

				showCartes();
				break;

			case 2:
				System.out.println("=> Action 2 selectionn�e <= ");

				addCarte();
				break;

			case 3:
				System.out.println("=> Action 3 selectionn�e <= ");

				editCarte();
				break;

			case 4:
				System.out.println("=> Action 4 selectionn�e <= ");

				deleteCarte();
				break;

			case 6:
				System.out.println("=> Action 6 selectionn�e <= ");

				showParties();
				break;

			case 7:
				System.out.println("=> Action 7 selectionn�e <= ");

				utilisateur = null;
				menu = 0;
				System.out.println("Bye!");
				break;
			}
		} while (menu != 0);
	}

	/**
	 * Affiche la liste des cartes
	 */
	public static void showCartes() {
		for (Carte c : daoCarte.findAll()) {
			System.out.println(c.getId() + ". " + c.getLibelle());
		}
	}

	/**
	 * Ajouter une carte
	 */
	public static void addCarte() {
		Carte myCarte = new Carte();

		System.out.println("Saisir le libell� de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
	public static void editCarte() {
		showCartes();

		System.out.println("Choisir la carte � modifier : ");
		Carte myCarte = daoCarte.findById(sc.nextInt());

		System.out.println(String.format("Saisir le libell� de la carte [%s]", myCarte.getLibelle()));
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Supprimer une carte
	 */
	public static void deleteCarte() {
		showCartes();

		System.out.println("Choisir la carte � supprimer : ");
		daoCarte.deleteById(sc.nextInt());
	}

	/**
	 * Affiche la liste des parties
	 */
	public static void showParties() {
		for (Partie p : daoPartie.findAll()) {
			System.out.println(p.getId());
		}
	}
}
