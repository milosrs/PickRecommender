import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { ChampionPicksComponent } from './components/champion-picks/champion-picks.component';
import { RecommendedChampsComponent } from './components/recommended-champs/recommended-champs.component';
import { RunesComponent } from './components/runes/runes.component';
import { SummonerSpellsComponent } from './components/summoner-spells/summoner-spells.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PlayerPositionComponent } from './components/champion-picks/player-position/player-position.component';
import { ChampionAreaComponent } from './components/champion-picks/champion-area/champion-area.component';

import { AbstractService } from './services/abstract.service';
import { AuthService } from './services/auth.service';
import { ChampionService } from './services/champion.service';
import { SummonerService } from './services/summoner.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegistrationComponent,
    LoginComponent,
    ChampionPicksComponent,
    RecommendedChampsComponent,
    RunesComponent,
    SummonerSpellsComponent,
    NavbarComponent,
    PlayerPositionComponent,
    ChampionAreaComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
  ],
  providers: [
    AuthService,
    ChampionService,
    SummonerService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
