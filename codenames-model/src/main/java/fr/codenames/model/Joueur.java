package fr.codenames.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="joueur")
@AttributeOverrides({
@AttributeOverride(name="id", column=@Column(name="JOU_ID")),
@AttributeOverride(name="nom", column=@Column(name="JOU_NOM")),
@AttributeOverride(name="prenom", column=@Column(name="JOU_PRENOM")),
@AttributeOverride(name="password", column=@Column(name="JOU_PASSWORD")),
})

public class Joueur extends Utilisateur {
	
	@Column(name="JOU_PSEUDO")
	@NotEmpty
	@NotNull
	private String pseudo;
	
	
	@Column(name="JOU_BANNI")
	private boolean banni;

	@OneToMany(mappedBy="joueur")
	private ArrayList<Message> messages;
	
	@OneToMany(mappedBy="joueur")
	private List<Participation> participations;
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean isBanni() {
		return banni;
	}

	public void setBanni(boolean banni) {
		this.banni = banni;
	}
	
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.JOUEUR;
	}
}