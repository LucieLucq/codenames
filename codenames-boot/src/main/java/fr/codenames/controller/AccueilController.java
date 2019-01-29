package fr.codenames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccueilController {


	@GetMapping("/connexion")
	public String accueil(@RequestParam(required=false) String username, Model model ) {
		model.addAttribute("utilisateurs", username);
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
