import { Component, OnInit } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Champion } from '../../model/champion';
import { SummmonerSpell } from '../../model/summmoner-spell';

@Component({
  selector: 'app-recommended-champs',
  templateUrl: './recommended-champs.component.html',
  styleUrls: ['./recommended-champs.component.css']
})
export class RecommendedChampsComponent implements OnInit {

  private champList: Champion[];
  private summonerSpells: SummmonerSpell[];
  private selectedChampion: Champion;

  constructor(private champService: ChampionService, private auth: AuthService, private router: Router) { }

  ngOnInit() {
    const generatedVals = this.champService.getRecommendations();
    this.champList = generatedVals.champRecommendations;
    this.summonerSpells = generatedVals.spellRecommendations;
  }

  public setSelectedChampion(champion: Champion) {
    this.selectedChampion = champion;
  }

}
