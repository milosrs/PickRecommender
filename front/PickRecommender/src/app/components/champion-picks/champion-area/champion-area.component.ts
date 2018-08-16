import { Component, OnInit, Input, ViewChildren, QueryList, Output, EventEmitter } from '@angular/core';
import { ChampionService } from '../../../services/champion.service';
import { ChampionViewList } from '../../../model/champion-view-list';
import { Champion } from '../../../model/champion';
import { HelperFunctions } from '../../../shared/util/helper-functions';
import { PlayerPositionComponent } from '../player-position/player-position.component';
import { Constants } from '../../../shared/constants/constants';
import { Router } from '@angular/router';


@Component({
  selector: 'champion-area',
  templateUrl: './champion-area.component.html',
  styleUrls: ['./champion-area.component.css']
})
export class ChampionAreaComponent implements OnInit {
  private positions = {
    'top' : ['Top', 'Position1'],
    'jg' : ['Jungle', 'Position2'],
    'mid': ['Mid', 'Position3'],
    'sup' : ['Support', 'Position4'],
    'bot' : ['Bottom', 'Position5'],
  };
  private friendlyChampions = {
    'top' : null,
    'jg' : null,
    'mid': null,
    'sup' : null,
    'bot' : null,
  }
  private opponentChampions = {
    'top' : null,
    'jg' : null,
    'mid': null,
    'sup' : null,
    'bot' : null,
  }
  private champs: ChampionViewList;
  private filteredChampList: Champion[];
  private filter: string;

  @Input() private champList: Champion[];
  @Input() private selectedPosition:any;
  @Input() private version: string;
  @Input() private shouldShowCaption: boolean;
  @Input() private shouldWaitForPositionSelection: boolean;
  @Output() private championSelectEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(protected championService: ChampionService, protected router: Router) {
  }

  createInfoForEveryChampion() {
    debugger;
    if(HelperFunctions.isEmptyValue(this.champList)) {
      Object.keys(this.champs.data).forEach(k => {
        const champObj = this.champs.data[k];
        const champToAdd = new Champion(champObj['id'], champObj['image'], champObj['name'], champObj['title']);
        this.champList.push(champToAdd);
      });
    }
    
    this.champList = HelperFunctions.sortArrayByKey(this.champList, 'name');
  }

  ngOnInit() {
    if(HelperFunctions.isEmptyValue(this.champList)) {
      this.championService.getAllForList().subscribe(resp => {
          this.champList = resp as Champion[];
          this.champList = HelperFunctions.sortArrayByKey(this.champList, 'name');
          this.filteredChampList = this.champList;
        });
    
        this.championService.getVersion().subscribe(resp => {
          this.version = resp as string
        });
    } else {
      this.filteredChampList = this.champList;
    }
  }

  filterChamps() {
    this.filteredChampList = HelperFunctions.filterArrayItems(this.champList, 'name', 
                                                                this.filter, Constants.FilterType.CONTAINS);
    this.champList = HelperFunctions.sortArrayByKey(this.champList, 'name');
  }

  selectChampion(champion) {
    if(this.selectedPosition['type'] === 'Friendly') {
        this.friendlyChampions['type'] = this.selectedPosition['type'];
        this.friendlyChampions[this.selectedPosition['id']] = champion;
        this.championSelectEvent.emit(this.friendlyChampions);
    } else if (this.selectedPosition['type'] === 'Opponent') {
        this.opponentChampions['type'] = this.selectedPosition['type'];
        this.opponentChampions[this.selectedPosition['id']] = champion;
        this.championSelectEvent.emit(this.opponentChampions);
    } else {
        this.championSelectEvent.emit(champion);
    }
  }

  getFriendlyPicks() {
      return this.friendlyChampions;
  }

  getOpponentPicks() {
    return this.opponentChampions;
  }
}