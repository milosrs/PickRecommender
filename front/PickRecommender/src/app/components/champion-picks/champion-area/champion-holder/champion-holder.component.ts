import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Champion } from '../../../../model/champion';
import { ChampionService } from '../../../../services/champion.service';

@Component({
  selector: 'champion-holder',
  templateUrl: './champion-holder.component.html',
  styleUrls: ['./champion-holder.component.css']
})
export class ChampionHolderComponent implements OnInit {

  @Input() private champion: Champion;
  @Output() private championSelectionEvent: EventEmitter<any> = new EventEmitter<any>();
  private imagePath: string;

  constructor(protected service: ChampionService) {
  }

  ngOnInit() {
    this.imagePath = this.service.getImageLocation('../../../../../', this.champion.imageInfo.full);
  }

  championSelect(event) {
    console.log('Selected champ: ', this.champion.name);
    this.championSelectionEvent.emit(this.champion);
  }

  createImagePath() {
    return this.service.getImageLocation('../../../../../', this.champion.imageInfo.full);
  }
}
