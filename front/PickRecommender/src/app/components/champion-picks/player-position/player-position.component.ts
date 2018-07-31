import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ChampionService } from '../../../services/champion.service';
import { HelperFunctions } from '../../../shared/util/helper-functions';
import { Constants } from '../../../shared/constants/constants';
import { SummonerService } from '../../../services/summoner.service';

@Component({
  selector: 'player-position',
  templateUrl: './player-position.component.html',
  styleUrls: ['./player-position.component.css']
})
export class PlayerPositionComponent implements OnInit {
  
  private positions = Constants.positions;
  public selectedChampions = {
    'top' : null,
    'jg' : null,
    'mid': null,
    'sup' : null,
    'bot' : null,
  };
  
  public activePosition: string;
  private selectAudio;
  private selectedPosition: string;

  @Input() private playersType: string;
  @Input() private customCss: object;
  @Input() private keyz: string[];
  @Input() private header: string;
  @Input() private shouldUseDefaultKeys: boolean;
  @Output() private positionClickEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(private champService: ChampionService, private summonerService: SummonerService) {
    // this.selectAudio = new Audio();
    // this.selectAudio.src = '../../../../assets/selectPosition.wav';
    // this.selectAudio.load();
    this.selectedPosition = summonerService.getSelectedPosition();  
  }

  ngOnInit() {
    if(HelperFunctions.isEmptyValue(this.keyz) && this.shouldUseDefaultKeys) {
      this.keyz = ['top','jg','mid','bot','sup'];
    }
  }

  onPositionClick(e) {
    const emitObj = {
      'type' : this.playersType,
      'id' : e.currentTarget.id
    };
    
    if(HelperFunctions.isEmptyValue(this.playersType)){
      this.activePosition = e.currentTarget.id;
    }
    // this.selectAudio.play();
    this.positionClickEvent.emit(emitObj);
  }

  determineActiveBtn(emitObj) {
    if(emitObj['type'] === this.playersType) {
      this.activePosition = emitObj['id'];
    } else {
      this.activePosition = null;
    }
  }

  getPlayersType() {
    return this.playersType;
  }

  setPickedChamps(selectedChampions: any) {
    this.selectedChampions = selectedChampions;
    console.log(this.selectedChampions);
  }

  getPickedChamps() {
    return this.selectedChampions;
  }

  getPickedChampsIdList() {
    let pickedChamps = {};
    const keys = Object.getOwnPropertyNames(Constants.positions);

    for(let i = 0; i < keys.length; i++) {
      pickedChamps[keys[i]] = this.selectedChampions[keys[i]].id;
    }

    return pickedChamps;
  }

  createImagePath(championName: string) {
    return this.champService.getImageLocation('../../../../', championName);
  }
}
