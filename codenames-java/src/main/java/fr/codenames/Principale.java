package fr.codenames;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;
import fr.codenames.dao.sql.DAOCarteSQL;
import fr.codenames.dao.sql.DAOJoueurSQL;
import fr.codenames.dao.sql.DAOPartieSQL;
import fr.codenames.dao.sql.DAOSQL;
import fr.codenames.dao.sql.DAOUtilisateurSQL;
import fr.codenames.model.Carte;
import fr.codenames.model.Grille;

public class Principale {
	public static void main(String[] args) {
		// DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
		// daoUtilisateur.connexion();
		ChoixAccueil();
	}

//////////////////////////////////////////////////////////////// UTILISATEUR
	public void listerUtiId() {
		DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();

		/// Lister tous les utilisateurs
		List<Utilisateur> lesUtilisateurs = daoUtilisateur.findAll();

		for (Utilisateur u : lesUtilisateurs) {
			System.out.println("------------------------------");
			System.out.println("le prenom = " + u.getPrenom());
			System.out.println("le nom = " + u.getNom());
			System.out.println("le username = " + u.getUsername());
			System.out.println("le password = " + u.getPassword());
		}
	}

	public void trouverUtiId() {
		/// Trouver l'utilisateur avec l'id =1
		DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
		System.out.println("------------------------------");
		Utilisateur leUtilisateur = daoUtilisateur.findById(1);
		System.out.println(
				"L'utilisateur numero 1 s'appelle : " + leUtilisateur.getNom() + " " + leUtilisateur.getPrenom());
	}

	public void ajoutUti() {
		// Ajouter un nouveau utilisateur
		DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
		System.out.println("------------------------------");
		Utilisateur nouveauUtilisateur = new Utilisateur();

		nouveauUtilisateur.setNom("Polux");
		nouveauUtilisateur.setPrenom("Charles");
		nouveauUtilisateur.setUsername("PopoCha");
		nouveauUtilisateur.setPassword("P8C3asH");

		daoUtilisateur.save(nouveauUtilisateur);
		System.out.println("L'utilisateur ajouté s'appelle : " + nouveauUtilisateur.getNom() + " "
				+ nouveauUtilisateur.getPrenom());
	}

	public void majUti() {
		// MAJ d'un nouvel utilisateur
		DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
		System.out.println("------------------------------");
		Utilisateur nouveauUtilisateurModif = daoUtilisateur.findById(6);
		nouveauUtilisateurModif.setNom("Pondu");
		nouveauUtilisateurModif.setPrenom("Jeanne");
		nouveauUtilisateurModif.setUsername("JPondu");
		nouveauUtilisateurModif.setPassword("Pond52jkh");

		daoUtilisateur.save(nouveauUtilisateurModif);
		System.out.println("L'utilisateur mis à jour s'appelle : " + nouveauUtilisateurModif.getNom() + " "
				+ nouveauUtilisateurModif.getPrenom());
	}

	public void supprimerUtiId() {
		DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
		// Suppression d'un utilisateur via son id
		System.out.println("------------------------------");
		daoUtilisateur.deleteById(8);
		System.out.println("L'utilisateur a bien été supprimé ! ");
	}

////////////////////////////////////////////////////////////////PARTIE 

	public void listerPartieId() {
		DAOPartieSQL daoPartie = new DAOPartieSQL();

		/// Lister toutes les parties
		List<Partie> lesParties = daoPartie.findAll();
		System.out.println("Liste des parties : ");
		for (Partie p : lesParties) {
			System.out.println("------------------------------");
			System.out.println("Partie numero : " + p.getId());

		}
	}

	public void trouverPartieId() {
		/// Trouver la partie avec l'id =1
		DAOPartieSQL daoPartie = new DAOPartieSQL();
		System.out.println("------------------------------");
		Partie laPartie = daoPartie.findById(1);
		System.out.println("La partie numero 1 s'appelle : " + laPartie.getId());
	}

	public void ajoutPartie() {
		// Ajouter d'une nouvelle partie
		DAOPartieSQL daoPartie = new DAOPartieSQL();
		System.out.println("------------------------------");
		Partie nouvellePartie = new Partie();
		Grille laGrille = new Grille();
		laGrille.setId(1);

		nouvellePartie.setGrille(laGrille);

		daoPartie.save(nouvellePartie);
		System.out.println("Une nouvelle partie est ajoutée à la grille numero : " + laGrille.getId());
	}

	public void supprimerPartieId() {
		DAOPartieSQL daoPartie = new DAOPartieSQL();
		// Suppression d'une partie via son id
		System.out.println("------------------------------");
		daoPartie.deleteById(3);
		System.out.println("La partie a bien été supprimée ! ");
	}

////////////////////////////////////////////////////////////////CARTE

	public void listerCartes() {
		// Lister toutes les cartes
		DAOCarteSQL daoCarte = new DAOCarteSQL();
		List<Carte> lesCartes = daoCarte.findAll();

		for (Carte c : lesCartes) {
			System.out.println("------------------------------");
			System.out.println("La carte " + c.getId() + " : " + c.getLibelle());

		}
	}

	public void trouverCartesId() {
		/// Trouver les cartes avec l'id =1
		DAOCarteSQL daoCarte = new DAOCarteSQL();
		System.out.println("------------------------------");
		Carte laCarte = daoCarte.findById(1);
		System.out.println("La carte numero 1 est : " + laCarte.getLibelle());
	}

	public void ajoutCarte() {
		// Ajouter une nouvelle carte
		DAOCarteSQL daoCarte = new DAOCarteSQL();
		System.out.println("------------------------------");
		Carte nouvelleCarte = new Carte();

		nouvelleCarte.setLibelle("manège");

		daoCarte.save(nouvelleCarte);
		System.out.println("La carte ajoutée est : " + nouvelleCarte.getLibelle());
	}

	public void majCarte() {
		// MAJ d'une nouvelle carte
		DAOCarteSQL daoCarte = new DAOCarteSQL();
		System.out.println("------------------------------");
		Carte nouvelleCarteModif = daoCarte.findById(1);
		nouvelleCarteModif.setLibelle("four");

		daoCarte.save(nouvelleCarteModif);
		System.out.println("La carte mise à jour est : " + nouvelleCarteModif.getLibelle());
	}

	public void supprimerCarteId() {
		// Suppression d'une carte via son id
		DAOCarteSQL daoCarte = new DAOCarteSQL();
		System.out.println("------------------------------");
		daoCarte.deleteById(5);
		System.out.println("La carte a bien été supprimée ! ");

	}
////////////////////////////////////////////////////////////////JOUEUR

	public void listerJoueurId() {
		DAOJoueurSQL daoJoueur = new DAOJoueurSQL();

		/// Lister tous les joueurs
		List<Joueur> lesJoueurs = daoJoueur.findAll();
		System.out.println("Liste des Joueurs : ");
		for (Joueur j : lesJoueurs) {
			System.out.println("------------------------------");
			System.out.println("Joueur numero : " + j.getId());

		}
	}

	public void trouverJoueurId() {
		/// Trouver le joueur avec l'id =1
		DAOJoueurSQL daoJoueur = new DAOJoueurSQL();
		System.out.println("------------------------------");
		Joueur leJoueur = daoJoueur.findById(1);
		System.out.println("Le Joueur numero 1 a le pseudo : " + leJoueur.getPseudo());
	}

	public void ajoutJoueur() {
		// Ajouter d'un nouveau joueur
		DAOJoueurSQL daoJoueur = new DAOJoueurSQL();
		System.out.println("------------------------------");
		Joueur nouveauJoueur = new Joueur();
		Utilisateur leUtilisateur = new Utilisateur();
		leUtilisateur.setId(4);

		nouveauJoueur.setPseudo("PouPiPou");
		nouveauJoueur.setBanni(false);
		nouveauJoueur.setUtilisateur(leUtilisateur);

		daoJoueur.save(nouveauJoueur);
		System.out.println("Le nouveau joueur est associé à l'utilisateur numero : " + leUtilisateur.getId());
	}
	/////////////////////////////////////////////////////////////// Accueil

	static int Accueil() {
		// Creation du menu
		ecrire("===================================");
		nouvelleLigne();
		ecrire("|              Accueil            |");
		nouvelleLigne();
		ecrire("===================================");
		nouvelleLigne();
		ecrire("| Actions possibles :             |");
		nouvelleLigne();
		ecrire("|        1. Connexion             |");
		nouvelleLigne();
		ecrire("|        2. Inscription           |");
		nouvelleLigne();
		ecrire("===================================");
		nouvelleLigne();
		ecrire("Saisir une action : ");
		int reponse = lireEntier();
		return reponse;
	}

	static void ChoixAccueil() {
		int reponse = Accueil();
		//while (reponse != 0) {

			if (reponse == 1) {
				// Afficher les mots avec leur id
				ecrire("--> Action 1 sélectionnée <--");
				nouvelleLigne();
				DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
				daoUtilisateur.connexion();

			}

			if (reponse == 2) {
				ecrire("--> Action 2 sélectionnée <--");
				nouvelleLigne();
				DAOUtilisateurSQL daoUtilisateur = new DAOUtilisateurSQL();
				daoUtilisateur.inscription();
				ecrire("--> Votre inscription a été validée !  <--");

				/*
				 * Scanner myScanner = new Scanner(System.in);
				 * System.out.println("Veuillez saisir un mot : "); String b =
				 * myScanner.nextLine();
				 * System.out.println("Vous avez ajouté la carte suivante : " + b + " => " +
				 * "Id : " + a); Carte Mot = Carte.creerMot(a, b); mots.add(Mot);
				 */

			}

		//}
		if (reponse > 2) {
			ecrire("--> Sélection invalide <--");
		}
	}

////////////////////////////////////////////////////////////////MENU
	/*
	 * private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>(); private
	 * static ArrayList<Carte> mots = new ArrayList<Carte>(); private static
	 * ArrayList<Partie> parties = new ArrayList<Partie>();
	 * 
	 * /* Fonction ecrire caractere
	 */
	static void ecrire(char[] a) {
		System.out.print(a);
	}

	/* Fonction ecrire entier */
	static void ecrire(int a) {
		System.out.print(a);
	}

	/* Fonction ecrire */
	static void ecrire(String a) {
		System.out.print(a);
	}

	static void nouvelleLigne() {
		System.out.print("\r");
	}

	static char[] lireChaine() {
		Scanner myScanner = new Scanner(System.in);
		try {
			return myScanner.nextLine().toCharArray();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new char[1];
		}
	}

	static int lireEntier() {
		Scanner myScanner = new Scanner(System.in);
		try {
			return myScanner.nextInt();
		} catch (Exception ex) {
			return 0;
		}
	}

	/* Fonction chaine de caractere */
	static char[] chaine(String s) {
		return s.toCharArray();
	}

	/* Fonction taille */
	static int taille(Object tab) {
		return Array.getLength(tab);
	}

	static Carte creerMot(int id, String libelle) {
		Carte c = new Carte();
		c.setId(id);
		c.setLibelle(libelle);
		return c;
	}

	static int Menu() {
		// Creation du menu
		ecrire("===================================");
		nouvelleLigne();
		ecrire("|        MENU DE SELECTION        |");
		nouvelleLigne();
		ecrire("===================================");
		nouvelleLigne();
		ecrire("| Actions possibles :             |");
		nouvelleLigne();
		ecrire("|        1. Liste des cartes      |");
		nouvelleLigne();
		ecrire("|        2. Ajout de cartes       |");
		nouvelleLigne();
		ecrire("|        3. Démarrer partie       |");
		nouvelleLigne();
		ecrire("|        4. Déconnexion           |");
		nouvelleLigne();
		ecrire("===================================");
		nouvelleLigne();
		ecrire("Saisir une action : ");
		int reponse = lireEntier();
		return reponse;

	}

	public void creerJoueur() {

		// TODO Auto-generated method stub

		// Creation des joueurs
		Joueur Joueur1 = Joueur.creerJoueur(01, "Dupont", "Jean", "JeanDup", "JeAnDuP", "JeanDup", false);
		Joueur Joueur2 = Joueur.creerJoueur(02, "Dupierre", "Paul", "PaulDup", "PaUlDuP", "PaulDup", false);
		Joueur Joueur3 = Joueur.creerJoueur(03, "Martin", "Luc", "LucMar", "LuCmAr", "LucMar", false);
		Joueur Joueur4 = Joueur.creerJoueur(04, "DuFeuille", "Martin", "MarDuf", "MaRdUf", "MarDuf", false);

		// Ajout des joueurs
		joueurs.add(Joueur1);
		joueurs.add(Joueur2);
		joueurs.add(Joueur3);
		joueurs.add(Joueur4);

		// Creation des mots
		Carte Mot1 = Carte.creerMot(1, "pont");
		Carte Mot2 = Carte.creerMot(02, "pierre");
		Carte Mot3 = Carte.creerMot(03, "maison");
		Carte Mot4 = Carte.creerMot(04, "feuille");

		// Ajout des mots
		mots.add(Mot1);
		mots.add(Mot2);
		mots.add(Mot3);
		mots.add(Mot4);

		// Creation partie
		Partie Partie1 = Partie.creerPartie(1);

		// Ajout partie
		parties.add(Partie1);

		// Switch construct
		int reponse = Menu();
		while (reponse != 0) {

			if (reponse == 1) {
				// Afficher les mots avec leur id
				ecrire("--> Action 1 sélectionnée <--");
				nouvelleLigne();
				ecrire("La liste de carte est la suivante : ");
				nouvelleLigne();
				for (Carte c : mots) {
					System.out.println(c.getLibelle() + " => Id : " + c.getId());
				}

			}

			if (reponse == 2) {
				ecrire("--> Action 2 sélectionnée <--");
				nouvelleLigne();
				int a = mots.size() + 1;
				Scanner myScanner = new Scanner(System.in);
				System.out.println("Veuillez saisir un mot : ");
				String b = myScanner.nextLine();
				System.out.println("Vous avez ajouté la carte suivante : " + b + " => " + "Id : " + a);
				Carte Mot = Carte.creerMot(a, b);
				mots.add(Mot);

			}

			if (reponse == 3) {
				ecrire("--> Action 3 sélectionnée <--");
				nouvelleLigne();

			}

			if (reponse == 4) {
				ecrire("--> Action 4 sélectionnée <--");
				nouvelleLigne();

			}
			if (reponse > 4) {
				ecrire("--> Sélection invalide <--");
			}
			reponse = Menu();
		}
	}

}
