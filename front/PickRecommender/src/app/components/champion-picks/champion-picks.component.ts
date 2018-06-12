import { Component, OnInit, Input } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { ChampionViewList } from '../../model/champion-view-list';
import { HelperFunctions } from '../../shared/util/helper-functions';
import { AuthService } from '../../services/auth.service';
import { Token } from '../../model/token';

@Component({
  selector: 'champion-picks',
  templateUrl: './champion-picks.component.html',
  styleUrls: ['./champion-picks.component.css']
})
export class ChampionPicksComponent implements OnInit {
  
  private champList: ChampionViewList;
  private token: Token;

  constructor(protected championService: ChampionService, protected auth: AuthService) { }

  ngOnInit() {
    this.championService.getChampViewList().subscribe(item => this.champList = item);
    this.token = this.auth.getToken();
  }

  isUserLoggedIn() {
    return this.auth.isLoggedInSimple();
  }
}
