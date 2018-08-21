import { Component, OnInit, Input } from '@angular/core';
import { Router } from '../../../../node_modules/@angular/router';
import { SummmonerSpell } from '../../model/summmoner-spell';
import * as $ from 'jquery';

@Component({
  selector: 'summoner-spells',
  templateUrl: './summoner-spells.component.html',
  styleUrls: ['./summoner-spells.component.css']
})
export class SummonerSpellsComponent implements OnInit {

  @Input() private summonerSpells: SummmonerSpell[];
  private hoveredSpell: SummmonerSpell;

  constructor(private router: Router) { }

  ngOnInit() {
    this.hoveredSpell = null;
  }

  togglePopover() {
    $('.popover').popover('toggle');
  }

  onMouseEnter(spell: SummmonerSpell) {
    this.hoveredSpell = spell;
    console.log("Spell: {0}", spell); this.hoveredSpell = spell;}

  onMouseLeave(spell: SummmonerSpell) {
    this.hoveredSpell = null;
    console.log("Left spell: {0}", spell);
  }
}
