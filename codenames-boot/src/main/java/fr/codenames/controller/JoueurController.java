package fr.codenames.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.Carte;
import fr.codenames.model.Joueur;
import fr.codenames.model.Utilisateur;


@Controller
public class JoueurController {
	@Autowired
	private IDAOJoueur daoJoueur;
	
	// AJOUTER
		@PostMapping("/inscription")
		public String ajouterJoueur(@Valid @ModelAttribute Joueur joueur, BindingResult result) {
			if(result.hasErrors()) {
			return "inscription";
		}
			daoJoueur.save(joueur);
			return "redirect:/connexion";
			
		}
}
