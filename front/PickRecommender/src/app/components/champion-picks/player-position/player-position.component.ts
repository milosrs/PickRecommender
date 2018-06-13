import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ChampionService } from '../../../services/champion.service';

@Component({
  selector: 'player-position',
  templateUrl: './player-position.component.html',
  styleUrls: ['./player-position.component.css']
})
export class PlayerPositionComponent implements OnInit {
  
  public keyz = ['top','jg','mid','sup','bot'];
  public selectedChampions = {
    'top' : null,
    'jg' : null,
    'mid': null,
    'sup' : null,
    'bot' : null,
  };
  public positions = {
    'top' : ['Top', 'Position1'],
    'jg' : ['Jungle', 'Position2'],
    'mid': ['Mid', 'Position3'],
    'sup' : ['Support', 'Position4'],
    'bot' : ['Bottom', 'Position5'],
  };
  public activePosition: string;
  private selectAudio;
  @Input() private playersType: string;
  @Input() private customCss: object;
  @Output() private positionClickEvent: EventEmitter<any> = new EventEmitter<any>();
  
  constructor(private champService: ChampionService) {
    // this.selectAudio = new Audio();
    // this.selectAudio.src = '../../../../assets/selectPosition.wav';
    // this.selectAudio.load();
    console.log(this.selectedChampions);
  }

  ngOnInit() {
  }

  onPositionClick(e) {
    const emitObj = {
      'type' : this.playersType,
      'id' : e.currentTarget.id
    };
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
    const keys = Object.getOwnPropertyNames(this.positions);

    for(let i = 0; i < keys.length; i++) {
      pickedChamps[keys[i]] = this.selectedChampions[keys[i]].id;
    }

    return pickedChamps;
  }

  createImagePath(championName: string) {
    return this.champService.getImageLocation('../../../../', championName);
  }
}
