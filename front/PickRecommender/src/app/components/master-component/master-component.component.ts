import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'master-component',
  templateUrl: './master-component.component.html',
  styleUrls: ['./master-component.component.css']
})
export class MasterComponentComponent implements OnInit {
  /* HAX - Angular 6 workaround */
  private trueValue = true;
  private falseValue = false;
  /* HAX -  Angular 6 workaround */

  private playerPosition: string;
  private pickSituation: any;
  private firstPick: string;
  public positions = {
    'top' : 'Top',
    'jg' : 'Jungle',
    'mid': 'Mid',
    'sup' : 'Support',
    'bot' : 'Bottom'
  };
  private keyz = ['top','jg','mid','sup','bot'];
  private positionOrder: any;
  private positionOrderKeys: string[];

  private shouldShowOrder: boolean;
  
  constructor() { }

  ngOnInit() {
    this.positionOrderKeys = [];
    this.positionOrder = [];
    this.shouldShowOrder = false;
    console.log(this.playerPosition);
  }

  selectPlayerPosition(selection) {
    this.playerPosition = selection['id'];
    this.shouldShowOrder = false;
  }

  pickPosition(e) {
    this.shouldShowOrder = true;
    alert('NEXT!');
  }

  onPositionClick(e) {
    const id = e.currentTarget.id;
    
    this.positionOrder[id] = this.positions[id];
    delete this.positions[id];
    this.positionOrderKeys.push(id);
  }
}
