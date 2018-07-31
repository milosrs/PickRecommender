import { Component, OnInit } from '@angular/core';
import { Constants } from '../../shared/constants/constants';
import { HelperFunctions } from '../../shared/util/helper-functions';
import { Router } from '../../../../node_modules/@angular/router';
import { SummonerService } from '../../services/summoner.service';

@Component({
  selector: 'master-component',
  templateUrl: './master-component.component.html',
  styleUrls: ['./master-component.component.css']
})
export class MasterComponentComponent implements OnInit {

  private playerPosition: string;
  private playerPositionFull: string;
  private firstPick: string;
  
  constructor(private router: Router, private summoner: SummonerService) { }

  ngOnInit() {
    this.firstPick = null;
    this.playerPosition = null;
  }

  selectPlayerPosition(selection) {
    this.playerPosition = selection['id'];
    this.playerPositionFull = Constants.positions[this.playerPosition][0];
  }

  onPositionClick(e) {
    const id = e.currentTarget.id;
    this.summoner.setSelectedPosition(id);
  }

  canProceed() {
    return HelperFunctions.isEmptyValue(this.playerPosition) && 
          HelperFunctions.isEmptyValue(this.firstPick);
  }

  showMainPage() {
    this.router.navigate(['/picker']);
  }
}
