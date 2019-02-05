import { Injectable } from '@angular/core';
import { Carte} from './carte/carte';
import { ConfigService } from './config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';


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

}
