import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'player-position',
  templateUrl: './player-position.component.html',
  styleUrls: ['./player-position.component.css']
})
export class PlayerPositionComponent implements OnInit {

  @Input() private playersType: string;
  @Input() private customCss: object;

  constructor() { }

  ngOnInit() {
  }

}
