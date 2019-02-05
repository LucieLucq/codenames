function supprimerMots() {
	var monMot = {
			libelle: $('input[name="ancienne_carte"]').val(),
		}
		$.ajax({
			method : 'DELETE',
			url : 'http://192.168.1.110/codenames-ajax/carte/'+ monMot.libelle,
			data : JSON.stringify(monMot),
			contentType : 'application/json',
			success : function(carte) {		//LA REPONSE DU SERVEUR
			}
		});
	};
//
//	
