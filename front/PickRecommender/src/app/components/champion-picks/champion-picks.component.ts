import { Component, OnInit, Input, ViewChild, QueryList, ViewChildren } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { ChampionViewList } from '../../model/champion-view-list';
import { HelperFunctions } from '../../shared/util/helper-functions';
import { AuthService } from '../../services/auth.service';
import { Token } from '../../model/token';
import { PlayerPositionComponent } from './player-position/player-position.component';
import { ChampionPicks } from '../../model/champion-picks';
import { Champion } from '../../model/champion';
import { ChampionAreaComponent } from './champion-area/champion-area.component';

@Component({
  selector: 'champion-picks',
  templateUrl: './champion-picks.component.html',
  styleUrls: ['./champion-picks.component.css']
})
export class ChampionPicksComponent implements OnInit {
  
  private token: Token;
  private selectedPosition: any = null;
  private selectedChampions;
  @ViewChildren(PlayerPositionComponent) playerPosComponent: QueryList<PlayerPositionComponent>;

  constructor(protected championService: ChampionService, protected auth: AuthService) { }

  ngOnInit() {
    this.token = this.auth.getToken();
  }

  isUserLoggedIn() {
    return this.auth.isLoggedInSimple();
  }

  changeSelectedPosition(selPos) {
    this.selectedPosition = selPos;
    this.determineActive();
  }

  determineActive() {
    this.playerPosComponent.forEach(c => {
      c.determineActiveBtn(this.selectedPosition);
    })
  }

  generate() {
    const toSend = {
      'Friendly': [],
      'Opponent': [],
    };

    this.playerPosComponent.forEach(c => {
      toSend[c.getPlayersType()].push(c.getPickedChampsIdList());
    });
    console.log(toSend);
    
    if(!HelperFunctions.isEmptyValue(this.auth.getToken().username)) {
      this.championService.generate(new ChampionPicks(toSend['Friendly'], toSend['Opponent'], this.auth.getToken().username));
    }
  }

  shouldGenerate() {
    let shouldEnable = true;
    let numberOfPickedChamps = 0;

    this.playerPosComponent.forEach(c => {
      shouldEnable = shouldEnable &&
                     c.getNumberOfPickedChampions() > 0;
    });

    return shouldEnable;
  }

  setSelectedChampions(selectedChampions: any) {
    const childArray = this.playerPosComponent.toArray();

    for(let i = 0; i < childArray.length; i++) {
      if(selectedChampions['type'] === childArray[i].getPlayersType()) {
        delete selectedChampions['type'];
        childArray[i].setPickedChamps(selectedChampions);
        return;
      }
    }
  }

  getTest() {
    this.auth.test();
  }
}
