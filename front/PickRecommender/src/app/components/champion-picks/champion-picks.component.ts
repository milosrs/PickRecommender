import { Component, OnInit, Input } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { ChampionViewList } from '../../model/champion-view-list';
import { HelperFunctions } from '../../shared/util/helper-functions';

@Component({
  selector: 'champion-picks',
  templateUrl: './champion-picks.component.html',
  styleUrls: ['./champion-picks.component.css']
})
export class ChampionPicksComponent implements OnInit {
  
  private champList: ChampionViewList;

  constructor(protected championService: ChampionService) { }

  ngOnInit() {
    this.championService.getChampViewList().subscribe(item => this.champList = item);
  }

}
