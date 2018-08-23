import { Component, OnInit, Input } from '@angular/core';
import { RuneRecommendation } from '../../model/rune-recommendation';

@Component({
  selector: 'runes',
  templateUrl: './runes.component.html',
  styleUrls: ['./runes.component.css']
})
export class RunesComponent implements OnInit {

  @Input() public runesObject: RuneRecommendation;

  constructor() { }

  ngOnInit() {
  }

}
