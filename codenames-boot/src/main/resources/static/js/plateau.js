////Envoyer au serveur le mot cliqué
var myLink = $('.rectangle1');
myLink.bind('click', function(event) {
	var mot=$(this).find(".rectangle2");
	var monLibelle= mot.text();
	$.ajax({
		method : 'POST',
		url : 'plateau',
		data : {
			"libelle" : monLibelle
		},
		success : function(data) {
		}
	});
});


////Distribuer les mots sur la grille
function distribuerMots(cartes) {
	//AFFECTER LA VALEUR
	for (let i = 1; i <= 25; i++) {

		$("#carte" + i + " .rectangle2").html(cartes[i - 1].libelle);

	}
}

$.ajax({
	method : 'GET',
	url : 'http://192.168.1.110/codenames-ajax/carte',
	contentType : 'application/json',
	success : function(cartes) {

		distribuerMots(shuffle(cartes));

	}

});

//fonction shuffle pour mélanger
function shuffle(array) {
	let counter = array.length;

	// While there are elements in the array
	while (counter > 0) {
		// Pick a random index
		let index = Math.floor(Math.random() * counter);

		// Decrease counter by 1
		counter--;

		// And swap the last element with it
		let temp = array[counter];
		array[counter] = array[index];
		array[index] = temp;
	}

	return array;
}

//Cacher/afficher des images
for (let i = 1; i <= 25; i++) {

	$('div#carte' + i).bind('click', function() {

		//this -> lien cliqué
		let mySelecteurACacher = $(this).attr('.rectangle1');

		$(mySelecteurACacher).hide();
		$('img#photo_carte').show();

		return false;

	});
}

function afficherMot() {

}

//var eventSource=new EventSource("http://192.168.1.128/codenames-web/plateau");
////onemessage qui se declenche quand un message est recu du serveur 
////close qui permet de fermer la connexion 
//eventSource.message=function(evt){
//	//evt.data la donnée recue du serveur
//	var monProduitRecuDuServeur=JSON.parse(evt.data);
//	afficherMot(monProduitRecuDuServeur);
//}

