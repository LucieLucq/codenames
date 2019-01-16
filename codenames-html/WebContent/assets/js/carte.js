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
				createRowProduit(carte);
			}	
		});
	}	

function createRowCarte(carte){
// CREATION DES COLONNES
var myColLibelle =$('<td />');

// AFFECTER LES VALEURS AUX COLONNES
myColLibelle.html(carte.libelle);

// CREATION DE LA LIGNE
var myLigne = $('<tr />');

// ASSOCIER LES COLONNES A LA LIGNE
myLigne.append(myColLibelle);

// INSERER LA LIGNE AU TABLEAU
$('table tbody').append(myLigne);
}
	$.ajax({
		method: 'GET',
		url: 'http://192.168.1.110/codenames-ajax/carte',
		success: function(cartes) {
		for (let carte of cartes){
			createRowProduit(carte);
			}
		}
	});
	
	
	