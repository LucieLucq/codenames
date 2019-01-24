function distribuerMots(cartes){
	//AFFECTER LA VALEUR
	for (let i =1 ; i <= 25; i++) {
		
	$("#carte" + i +  " .rectangle2").html(cartes[i-1].libelle);

	}
}

$.ajax({
	method: 'GET',
	url: 'http://192.168.1.110/codenames-ajax/carte',
	contentType: 'application/json',
	success: function(cartes) {
			
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
for (let i = 1; i<=25;i++) {
	
$('div#carte' +i).bind('click', function() {

	
	//this -> lien cliqué
	let mySelecteurACacher = $(this).attr('.rectangle1');
 
	$(mySelecteurACacher).hide();
	$('img#photo_carte').show(); 
	
	return false;
	
});
}