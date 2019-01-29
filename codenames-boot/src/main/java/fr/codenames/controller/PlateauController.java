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
import org.springframework.web.bind.annotation.ResponseBody;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;


@Controller
public class PlateauController {
	@Autowired
	private IDAOCarte daoCarte;
	
		// LISTER
		@GetMapping("/plateau")
		public String Plateau(Model model) {
			model.addAttribute("cartes" , daoCarte.findAll().subList(0, 25));
			return "plateau";
		}
		@PostMapping("/plateau")
		@ResponseBody
		public String plateau(@RequestParam String libelle) {
			System.out.println("Le mot sélectionné est : " + libelle);
			return "libelle";
		}
}
