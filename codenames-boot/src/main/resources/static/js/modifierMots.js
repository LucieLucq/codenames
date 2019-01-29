function modifierMots(libelle){
	var monMot = {
		
		libelle: $('input[name="modif_carte1"]').val(),
	}
	//REQUETE AJAX POUR AJOUTER LE PRODUIT
	$.ajax({
		method : 'POST',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		data : JSON.stringify(monMot),	//CONVERTIR L'OBJET JS EN JSON
		contentType : 'application/json',	//DE QUOI EST FAIT LE FLUX
		success : function(carte) {		//LA REPONSE DU SERVEUR
		}
	});
}
