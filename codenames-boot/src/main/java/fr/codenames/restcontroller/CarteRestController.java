package fr.codenames.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cartes")
public class CarteRestController {
	@Autowired
	private IDAOCarte daoCarte;

	@GetMapping("/liste")
	public List<Carte> findAll() {
		return this.daoCarte.findAll();
	}

}
