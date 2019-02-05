import { Injectable, HostListener } from '@angular/core';
import { Carte} from './carte/carte';
import { ConfigService } from './config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
declare var $ :any


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

//Distribuer les mots sur la grille
distribuer() {
  this.httpClient.get<Carte[]>("http://192.168.1.128:8080/api/cartes/liste").subscribe(resp => this.cartes = resp);
  this.shuffle(this.cartes);
  for (let i: number = 1; i <= 25; i++) {
		$("#carte" + i + " .rectangle2").html(this.cartes[i - 1].libelle);
	}
}

//fonction shuffle pour mélanger
shuffle(array) {
	let counter: number = array.length;

	// While there are elements in the array
	while (counter > 0) {
		// Pick a random index
		let index: number = Math.floor(Math.random() * counter);

		// Decrease counter by 1
		counter--;

		// And swap the last element with it
		let temp = array[counter];
		array[counter] = array[index];
		array[index] = temp;
	}

	return array;
}

////Envoyer au serveur le mot cliqué
// @HostListener('click')
//   onClick() {
//     this.httpClient
//       .post("http://localhost:4200/plateau", carte.libelle)
//       .subscribe(resp => this.refresh());
//   }


// var myLink = $('.rectangle1');
// myLink.bind('click', function(event) {
// 	var mot=$(this).find(".rectangle2");
// 	var monLibelle= mot.text();
// 	$.ajax({
// 		method : 'POST',
// 		url : 'plateau',
// 		data : {
// 			"libelle" : monLibelle
// 		},
// 		success : function(data) {
// 		}
// 	});
// });











}
