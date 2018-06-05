import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ChampionPicksComponent } from './components/champion-picks/champion-picks.component';
import { RecommendedChampsComponent } from './components/recommended-champs/recommended-champs.component';
import { RunesComponent } from './components/runes/runes.component';
import { SummonerSpellsComponent } from './components/summoner-spells/summoner-spells.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ChampionPicksComponent,
    RecommendedChampsComponent,
    RunesComponent,
    SummonerSpellsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
