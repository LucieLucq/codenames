import { Injectable } from '@angular/core';
import { Carte} from './carte/carte';
import { ConfigService } from './config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
declare var $: any;

@Injectable({
  providedIn: 'root'
})

export class CarteService {
  public cartes:Array<Carte>=new Array<Carte>();
  public cartesAsync: any = null;
  public carte: any= new Carte();


  constructor(private httpClient: HttpClient) { }

findAll(){
  this.httpClient.get<Carte[]>("http://192.168.1.128:8080/api/cartes").subscribe(resp => this.cartes = resp);
}

findAllAsync(){
  if(this.cartesAsync == null){
    this.cartesAsync = this.httpClient.get("http://192.168.1.128:8080/api/cartes/liste");
}
return this.cartesAsync;
}

refresh(){
  this.cartesAsync = null;
}

////Distribuer les mots sur la grille
distribuer() {
  this.httpClient.get<Carte[]>("http://192.168.1.128:8080/api/cartes/liste").subscribe(resp => this.cartes = resp);
  this.distribuerMots(this.shuffle(this.cartes));
}

distribuerMots(cartes) {
	//AFFECTER LA VALEUR
	for (let i = 1; i <= 25; i++) {

		$("#carte" + i + " .rectangle2").html(cartes[i - 1].libelle);
	}
}

// $.ajax({
// 	method : 'GET',
// 	url : 'http://192.168.1.110/codenames-ajax/carte',
// 	contentType : 'application/json',
// 	success : function(cartes) {
//
// 		distribuerMots(shuffle(cartes));
//
// 	}
//
// });

shuffle(array) {
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
}
