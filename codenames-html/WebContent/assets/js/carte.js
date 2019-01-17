function ajouterCarte() {
		var maCarte = {
			libelle: $('input[name="libelle"]').val(),
		}
		$.ajax({
			method: 'POST',
			url: 'http://192.168.1.110/codenames-ajax/carte',
			data:JSON.stringify(maCarte),
			contentType: "application/json", 
			success: function(carte){
				createRowCarte(carte);
			}	
		});
	}	

function createRowCarte(carte){
// CREATION DES COLONNES
var myColId =$('<td />');
var myColLibelle =$('<td />');

// AFFECTER LES VALEURS AUX COLONNES
myColId.html($('table tbody tr').length+1);
myColLibelle.html(carte.libelle);

// CREATION DE LA LIGNE
var myLigne = $('<tr />');

// ASSOCIER LES COLONNES A LA LIGNE
myLigne.append(myColId);
myLigne.append(myColLibelle);

// INSERER LA LIGNE AU TABLEAU
$('table tbody').append(myLigne);
}
	$.ajax({
		method: 'GET',
		url: 'http://192.168.1.110/codenames-ajax/carte',
		success: function(cartes) {
		for (let carte of cartes){
			createRowCarte(carte);
			}
		}
	});
	
	

function supprimerMots() {
	var monMot = {
			libelle: $('input[name="ancienne_carte"]').val(),
		}
		$.ajax({
			method : 'DELETE',
			url : 'http://192.168.1.110/codenames-ajax/carte/'+i,
			contentType : 'application/json',
			data : JSON.stringify(monMot),
			
		});
		
	};
//
//	
//	