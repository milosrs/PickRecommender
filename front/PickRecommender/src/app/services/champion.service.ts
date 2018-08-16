import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { Champion } from '../model/champion';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Token } from '../model/token';
import { ChampionViewList } from '../model/champion-view-list';
import { map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { PickGeneratorInfo } from '../model/pick-generator-info';

@Injectable({
  providedIn: 'root'
})
export class ChampionService extends AbstractService<Champion, number> {

  private token: Token;
  private champViewList: ChampionViewList;
  private playerPosition: string;
  private teamFirstPick: string;
  private imageLocation = '{0}assets/8.9.1/img/champion/{1}';
  private friendlyPlayersOrder: string[];

  private recommendations: Champion[];

  constructor(protected http: HttpClient, protected auth: AuthService) {
    super(http, 'champions', auth);
   }

   init() {
    this.token = this.auth.getToken();
   }

   public getAllForList() {
    const headerobj = this.auth.getJSONAuthHeader();
    return this.http.get(this.actionUrl + '/forList', {headers: headerobj});
   }

   public getDataForChampion(champId: number) {
    const headerobj = this.auth.getJSONAuthHeader();
    return this.http.get(this.actionUrl + '/' + champId, {headers: headerobj});
   }

   public generate(championPicks: PickGeneratorInfo) {
    const headerobj = this.auth.getJSONAuthHeader();
    return this.http.post(this.actionUrl + '/generate', championPicks, {headers: headerobj});
   }

   public getVersion() {
    const headerobj = this.auth.getJSONAuthHeader('text');
    return this.http.get(this.actionUrl + '/version', {headers: headerobj, responseType: 'text'});
   }

   public getImageLocation(whereInFolders:string, championName: string): string {
    return this.imageLocation.replace('{0}', whereInFolders).replace('{1}', championName);
  }

  public getPlayerPosition() {
    return this.playerPosition;
  }

  public setPlayerPosition(pos: string) {
    this.playerPosition = pos;
  }

  public getFirstPick() {
    return this.teamFirstPick;
  }

  public setFirstPick(fp) {
    this.teamFirstPick = fp;
  }

  public getFriendlyPlayersOrder() {
    return this.friendlyPlayersOrder;
  }

  public setFriendlyPlayersOrder(order: string[]) {
    this.friendlyPlayersOrder = order;
  }

  public setRecommendations(recommendations: Champion[]) {
    this.recommendations = recommendations;
  }

  public getRecommendations() {
    return this.recommendations;
  }
}
