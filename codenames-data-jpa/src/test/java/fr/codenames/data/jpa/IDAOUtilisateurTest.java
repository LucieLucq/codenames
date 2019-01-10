package fr.codenames.data.jpa;

import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Utilisateur;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.codenames.exception.AccountLockedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JPAConfig.class })

public class IDAOUtilisateurTest  {
	@Autowired(required=false)
	private IDAOUtilisateur daoUtilisateur;

	@Test
	public void exists() {
		assertNotNull("la DAO n'existe pas", daoUtilisateur);
	}
	@Test
	public void getutilisateur() {
		Utilisateur myOptionalUtilisateur =daoUtilisateur.connexion("DupJean", "Dup15");
		assertNotNull("L'utilisateur trouvé mais resultat nul", myOptionalUtilisateur);
		assertNotNull("L'utilisateur trouvé mais infos non remontées", myOptionalUtilisateur.getUsername());
	}
}