import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  public url: string="http://192.168.1.128:8080/api/";
    constructor() { }
  }
