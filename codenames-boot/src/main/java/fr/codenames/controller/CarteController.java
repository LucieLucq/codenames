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

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;
import fr.codenames.security.annotation.IsAdmin;


@Controller
public class CarteController {
	@Autowired
	private IDAOCarte daoCarte;

	// LISTER
		@GetMapping("/lister-carte")
		public String listerCarte(Model model) {
			model.addAttribute("cartes", daoCarte.findAll());
			return "liste-cartes";
		}
	
	// AJOUTER
	@IsAdmin
	@PostMapping("/lister-carte")
	public String ajouterCarte(@Valid @ModelAttribute Carte carte, BindingResult result) {
		if(result.hasErrors()) {
		return "liste-cartes";
	}
		daoCarte.save(carte);
		return "redirect:/lister-carte";
		
	}
	
	// SUPPRIMER
	@IsAdmin
	@GetMapping("/supprimerCarte")
	public String supprimerCarte(@RequestParam int id) {
		daoCarte.deleteById(id);
		return "redirect:/lister-carte";
	}
	
	//EDITER
	@IsAdmin
	@GetMapping("/editerCarte")
	public String editerCarte(@RequestParam int id, Model model) {
		model.addAttribute("carte",daoCarte.findById(id).get() );
		return "editer-cartes";
	}

	@IsAdmin
	@PostMapping("/editerCarte")
	public String editerCarte2(@RequestParam  int id,@Valid @ModelAttribute Carte carte, BindingResult result) {
		if(result.hasErrors()) {
			return "editer-cartes";
		}
		daoCarte.save(carte);
		return "redirect:/lister-carte";
	}
}
