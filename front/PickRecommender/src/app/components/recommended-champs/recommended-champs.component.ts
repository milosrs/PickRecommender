import { Component, OnInit } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Champion } from '../../model/champion';
import { SummmonerSpell } from '../../model/summmoner-spell';
import { RunesService } from '../../services/runes.service';
import { RuneRecommendation } from '../../model/rune-recommendation';

@Component({
  selector: 'app-recommended-champs',
  templateUrl: './recommended-champs.component.html',
  styleUrls: ['./recommended-champs.component.css']
})
export class RecommendedChampsComponent implements OnInit {

  private champList: Champion[];
  private summonerSpells: SummmonerSpell[];
  private selectedChampion: Champion;
  private runesObject: RuneRecommendation;

  constructor(private champService: ChampionService, 
              private auth: AuthService, private router: Router,
              private runesService: RunesService) { }

  ngOnInit() {
    const generatedVals = this.champService.getRecommendations();
    this.champList = generatedVals.champRecommendations;
    this.summonerSpells = generatedVals.spellRecommendations;
  }

  public getRunes(champion: Champion) {
    if(champion !== this.selectedChampion) {
      this.selectedChampion = champion;

      this.runesService.generateRecommendation(this.selectedChampion.id, this.champService.getPlayerPosition())
                      .subscribe(resp => {
                        console.log(this.runesObject);
                        this.runesObject = resp as RuneRecommendation;
                      });
    }
  }
}
