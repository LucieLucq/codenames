package fr.codenames;

import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.codenames.data.jpa.IDAOCarte;
import fr.codenames.data.jpa.IDAOPartie;
import fr.codenames.data.jpa.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Carte;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;

public class AppPrincipale {
	@Autowired
	public IDAOUtilisateur daoUtilisateur;
	@Autowired
	public  IDAOCarte daoCarte;
	@Autowired
	public IDAOPartie daoPartie;

	public Scanner sc;

	public void run(String[] args) {
//		InterfaceConnexion();
		inscription();
	}

	public void InterfaceConnexion() {
		sc = new Scanner(System.in);
		System.out.print("Saisir votre nom d'utilisateur : ");
		String username = sc.nextLine();
		System.out.print("Saisir votre mot de passe : ");
		String motDePasse = sc.nextLine();
		try {
			connexion(username, motDePasse);
			System.out.println("Vous etes connecte !");
			menu();
		} catch (AccountLockedException e) {
			System.out.println("Vous etes banni !");
		} catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("Mot de passe ou username incorrect !");
		} finally {
			sc.close();
		}
	}

	
	public Utilisateur connexion (String username, String motDePasse)
			throws AccountLockedException, UsernameOrPasswordNotFoundException {

		Utilisateur monUtilisateur = daoUtilisateur.connexion(username, motDePasse);
	
		if (monUtilisateur == null) {
			throw new UsernameOrPasswordNotFoundException();
		}
		
		if (monUtilisateur instanceof Joueur) {
			if (((Joueur) monUtilisateur).isBanni()) {
				throw new AccountLockedException();
			}
		}

			return monUtilisateur;

		

	}

	public  void RechercheCarte() {
		sc = new Scanner(System.in);
		System.out.print("Saisir votre mot : ");
		String libelle = sc.nextLine();
		daoCarte.findByLibelle(libelle);

		sc.close();

	}


	/**
	 * S'inscrire (creer un nouveau compte utilisateur)
	 */
	public void inscription() {
		Joueur nouveauJoueur = new Joueur();

		sc = new Scanner(System.in);
		System.out.print("Indiquer votre nom : ");
		nouveauJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre prenom : ");
		nouveauJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer le nom d'utilisateur : ");
		nouveauJoueur.setUsername(sc.nextLine());

		System.out.print("Indiquer le mot de passe : ");
		nouveauJoueur.setPassword(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		nouveauJoueur.setPseudo(sc.nextLine());

		nouveauJoueur.setBanni(false);

		try {
			daoUtilisateur.save(nouveauJoueur);
		}

		catch (NonUniqueUsernameException e) {
			System.out.println("Le nom d'utilisateur est deja utilise !");
		}

		InterfaceConnexion();
	}

	/**
	 * Affiche le menu et d�marre les sous-programmes
	 */
	public void menu() {
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
			System.out.println("|           5.	Demarrer une nouvelle partie   |");
			System.out.println("|           6.	Lister les parties             |");
			System.out.println("|           7.	Se deconnecter                 |");
			System.out.println("================================================");
			System.out.println("Veuillez selectionner votre action : ");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("=> Action 1 selectionnee <= ");

				showCartes();
				break;

			case 2:
				System.out.println("=> Action 2 selectionnee <= ");

				addCarte();
				break;

			case 3:
				System.out.println("=> Action 3 selectionnee <= ");

				// editCarte();
				break;

			case 4:
				System.out.println("=> Action 4 selectionnee <= ");

				deleteCarte();
				break;

			case 6:
				System.out.println("=> Action 6 selectionnee <= ");

				showParties();
				break;

			case 7:
				System.out.println("=> Action 7 selectionnee <= ");

				daoUtilisateur = null;
				menu = 0;
				System.out.println("Bye!");
				break;
			}
		} while (menu != 0);
	}

	/**
	 * Affiche la liste des cartes
	 */
	public void showCartes() {
		for (Carte c : daoCarte.findAll()) {
			System.out.println(c.getId() + ". " + c.getLibelle());
		}
	}

	/**
	 * Ajouter une carte
	 */
	public void addCarte() {
		Carte myCarte = new Carte();

		System.out.println("Saisir le libell� de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
//	public static void editCarte() {
//		showCartes();
//
//		System.out.println("Choisir la carte � modifier : ");
//		Carte myCarte = daoCarte.findById(sc.nextInt());
//
//		System.out.println(String.format("Saisir le libell� de la carte [%s]", myCarte.getLibelle()));
//		myCarte.setLibelle(sc.next());
//
//		daoCarte.save(myCarte);
//	}

	/**
	 * Supprimer une carte
	 */
	public void deleteCarte() {
		showCartes();

		System.out.println("Choisir la carte � supprimer : ");
		daoCarte.deleteById(sc.nextInt());
	}

	/**
	 * Affiche la liste des parties
	 */
	public void showParties() {
		for (Partie p : daoPartie.findAll()) {
			System.out.println(p.getId());
		}
	}
}
