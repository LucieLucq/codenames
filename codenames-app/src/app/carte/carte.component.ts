import { Component, OnInit } from '@angular/core';
import { CarteService } from '../carte.service';
import { Carte} from './carte';

@Component({
  selector: 'app-carte',
  templateUrl: './carte.component.html',
  styleUrls: ['./carte.component.css']
})
export class CarteComponent implements OnInit {

  public cartes:Array<Carte>=new Array<Carte>();

  constructor(private carteService: CarteService) {
  this.cartes=carteService.cartes ;
}

  ngOnInit() {
  }

}
