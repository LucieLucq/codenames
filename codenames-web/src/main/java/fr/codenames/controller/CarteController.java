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

import fr.codenames.data.jpa.IDAOCarte;
import fr.codenames.model.Carte;


@Controller
public class CarteController {
	@Autowired
	private IDAOCarte daoCarte;

	// AFFICHER LISTE CARTES
	@GetMapping("/lister-carte")
	public String listerCarte(Model model) {
		model.addAttribute("cartes", daoCarte.findAll());
		return "crud";
	}

	// AJOUTER CARTES
	@GetMapping("/ajouter-carte") // acc�s au formulaire
	public String ajouterCarte() {
		return "ajout-cartes";
	}

	@PostMapping("/ajouter-carte") // ajout dans la DB
	public String ajouterCarte(@Valid @ModelAttribute Carte carte, BindingResult result) {
		if (result.hasErrors()) {
			return "ajout-cartes";
		}
		daoCarte.save(carte);
		return "redirect:/lister-carte";
	}

	// SUPPRIMER CARTE
	@GetMapping("/supprimer-carte")
	public String supprimerCarte(@RequestParam int id) {
		daoCarte.deleteById(id);
		return "redirect:/lister-carte";

	}

	// EDITER CARTE
	@GetMapping("/editer-carte")
	public String editerCarte(@RequestParam int id, Model model) {
		model.addAttribute("carte", daoCarte.findById(id).get());
		return "ajout-cartes";
	}

	@PostMapping("/editer-carte")
	public String editerCarte(@RequestParam int id, @Valid @ModelAttribute Carte carte, BindingResult result) {
		if (result.hasErrors()) {
			return "ajout-cartes";
		}
		daoCarte.save(carte);
		return "redirect:/lister-carte";
	}
}
