package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;

@Controller
public class AccueilController {
	@Autowired
	private IDAOJoueur daoJoueur;
	@Autowired
	private IDAOUtilisateur daoUtilisateur;

	@GetMapping("/connexion")
	public String accueil() {
		return "connexion";
	}

	@PostMapping("/connexion")
	public String connexion(@RequestParam String username, String motDePasse, Model model  ) {
		model.addAttribute("joueurs", daoUtilisateur.connexion(username, motDePasse));
		return "connexion";
	}
	
	@GetMapping("/inscription")
	public String inscription() {
		return "inscription";
	}

	@GetMapping("/accueil")
	public String accueilJeu() {
		return "accueil-jeu";
	}

}
