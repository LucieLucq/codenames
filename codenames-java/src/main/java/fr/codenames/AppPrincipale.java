package fr.codenames;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

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
	public static IDAOUtilisateur daoUtilisateur ;
	@Autowired
	public static IDAOCarte daoCarte ;
	@Autowired
	public static IDAOPartie daoPartie;
	@Autowired
	public static Utilisateur utilisateur;
	@Autowired
	public static Scanner sc;
	
	
	public void run(String[] args) {
		RechercheCarte();
//		ConnexionUtilisateur();
//		inscription();
//		emf.close() ;
	}
	
		public static void ConnexionUtilisateur(){
	    sc = new Scanner(System.in);
		System.out.print("Saisir votre nom d'utilisateur : ");
		String username = sc.nextLine();
		System.out.print("Saisir votre mot de passe : ");
		String motDePasse = sc.nextLine();
		try {
		daoUtilisateur.connexion(username,motDePasse);
		System.out.println("Vous etes connect� !");
		menu() ;
		}
		catch (AccountLockedException e) {
			System.out.println("Vous etes banni !");
		}
		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("Mot de passe ou username incorrect !");
		}
		finally {
		sc.close();
		}
		}
		
		public Utilisateur connexion(String username, String motDePasse)
				throws AccountLockedException, UsernameOrPasswordNotFoundException {

			// JPQL -> entityManager
			// Ajouter les param�tres (username, mdp)
			// R�cup�rer l'Utilisateur -> UN SEUL UTILISATEUR
			// SI YA PAS DUSER -> EXCEPTION DE TYPE NoResultException
			// SI CA CATCH PAS, CEST QUE LUSER EXISTE
			
			Utilisateur monUtilisateur;
			try {
				TypedQuery<Utilisateur> myQuery = em.createQuery(
						"select u from Utilisateur u where u.username = :username AND u.password = :password",
						Utilisateur.class);
				myQuery.setParameter("username", username);
				myQuery.setParameter("password", motDePasse);

				monUtilisateur = myQuery.getSingleResult();
				if (monUtilisateur instanceof Joueur) {
					if (((Joueur) monUtilisateur).isBanni()) {

						throw new AccountLockedException();

					}

				}

				return monUtilisateur;

			} catch (NoResultException e) {

				throw new UsernameOrPasswordNotFoundException();

			}

		}
	
	public static void RechercheCarte(){
		sc = new Scanner(System.in);
		System.out.print("Saisir votre mot : ");
		String libelle= sc.nextLine();
		daoCarte.findByLibelle(libelle);

		sc.close();
	
		}
		

	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (� saisir)
	 */
	public static void connection() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();

		if (username.equals("")) {
			inscription();
			return;
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.connexion(username, password);
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
		Joueur nouveauJoueur=new Joueur();
		
		sc = new Scanner(System.in);
		System.out.print("Indiquer votre nom : ");
		nouveauJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre pr�nom : ");
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
			System.out.println("Le nom d'utilisateur est d�j� utilis� !");
		}

		ConnexionUtilisateur();
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

				//editCarte();
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
