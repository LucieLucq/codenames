package fr.codenames.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administrateur")
@AttributeOverrides({
@AttributeOverride(name="id", column=@Column(name="ADM_ID")),
@AttributeOverride(name="nom", column=@Column(name="ADM_NOM")),
@AttributeOverride(name="prenom", column=@Column(name="ADM_PRENOM")),
@AttributeOverride(name="password", column=@Column(name="ADM_PASSWORD")),
})

public class Administrateur extends Utilisateur {
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.ADMINISTRATEUR;
	}
}
