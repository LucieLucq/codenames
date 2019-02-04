import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import{ HttpClientModule } from '@angular/common/http';
import { CarteComponent } from './carte/carte.component';


//Configuration des routes
const routes: Routes = [
{ path: 'plateau', component: CarteComponent },
{ path: '', redirectTo: 'plateau', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    CarteComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
