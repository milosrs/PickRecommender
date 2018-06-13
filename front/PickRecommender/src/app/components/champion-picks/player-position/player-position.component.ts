import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'player-position',
  templateUrl: './player-position.component.html',
  styleUrls: ['./player-position.component.css']
})
export class PlayerPositionComponent implements OnInit {
  
  private selectedChampions = {
    'top' : null,
    'jg' : null,
    'mid': null,
    'sup' : null,
    'bot' : null,
  }
  private activePosition: string;
  private selectAudio;
  @Input() private playersType: string;
  @Input() private customCss: object;
  @Output() private positionClickEvent: EventEmitter<any> = new EventEmitter<any>();
  
  constructor() {
    // this.selectAudio = new Audio();
    // this.selectAudio.src = '../../../../assets/selectPosition.wav';
    // this.selectAudio.load();
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
  }

  getPickedChamps() {
    return this.selectedChampions;
  }
}
