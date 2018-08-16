import { Component, OnInit, Input, ViewChild, QueryList, ViewChildren } from '@angular/core';
import { ChampionService } from '../../services/champion.service';
import { ChampionViewList } from '../../model/champion-view-list';
import { HelperFunctions } from '../../shared/util/helper-functions';
import { AuthService } from '../../services/auth.service';
import { Token } from '../../model/token';
import { PlayerPositionComponent } from './player-position/player-position.component';
import { Champion } from '../../model/champion';
import { ChampionAreaComponent } from './champion-area/champion-area.component';
import { PickGeneratorInfo } from '../../model/pick-generator-info';
import { Router } from '@angular/router';

@Component({
  selector: 'champion-picks',
  templateUrl: './champion-picks.component.html',
  styleUrls: ['./champion-picks.component.css']
})
export class ChampionPicksComponent implements OnInit {
  
  private token: Token;
  private selectedPosition: any = null;
  private playerPosition: string;
  private firstPick: any = null;
  private selectedChampions;
  private positionOrder: string[];
  @ViewChildren(PlayerPositionComponent) playerPosComponent: QueryList<PlayerPositionComponent>;

  constructor(protected championService: ChampionService, protected auth: AuthService, private router: Router) { }

  ngOnInit() {
    this.token = this.auth.getToken();
    this.playerPosition = this.championService.getPlayerPosition();
    this.firstPick = this.championService.getFirstPick();
    this.positionOrder = this.championService.getFriendlyPlayersOrder();
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
    let generate = null;

    this.playerPosComponent.forEach(c => {
      toSend[c.getPlayersType()] = c.getPickedChampsIdList();
    });

    if(!HelperFunctions.isEmptyValue(this.auth.getToken().username)) {
      const keys = Object.keys(toSend['Opponent']);
      let opponents = [];

      for(let i = 0; i < keys.length; i++) {
        opponents.push(toSend['Opponent'][keys[i]]);
      }
      generate = new PickGeneratorInfo(toSend['Friendly'], opponents, this.playerPosition, 
                                       this.firstPick, this.auth.getToken().username);
      console.log(generate);
    }
    
    this.championService.generate(generate)
      .subscribe(resp => {
        console.log(resp);
        this.championService.setRecommendations(resp as Champion[]);
        this.router.navigate(['recommendations']);
      });
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

  reset() {
    this.championService.setFirstPick(null);
    this.championService.setPlayerPosition(null);
    this.router.navigate(['/']);
  }
}
