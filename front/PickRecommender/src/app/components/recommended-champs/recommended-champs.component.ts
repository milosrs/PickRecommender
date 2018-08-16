import { Component, OnInit } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Champion } from '../../model/champion';

@Component({
  selector: 'app-recommended-champs',
  templateUrl: './recommended-champs.component.html',
  styleUrls: ['./recommended-champs.component.css']
})
export class RecommendedChampsComponent implements OnInit {

  private champList: Champion[];
  private selectedChampion: Champion;

  constructor(private champService: ChampionService, private auth: AuthService, private router: Router) { }

  ngOnInit() {
    this.champList = this.champService.getRecommendations();
  }

  public setSelectedChampion(champion: Champion) {
    this.selectedChampion = champion;
  }

}
