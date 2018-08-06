import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { Champion } from '../model/champion';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Token } from '../model/token';
import { ChampionViewList } from '../model/champion-view-list';
import { map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ChampionPicks } from '../model/champion-picks';

@Injectable({
  providedIn: 'root'
})
export class ChampionService extends AbstractService<Champion, number> {

  private token: Token;
  private champViewList: ChampionViewList;
  private imageLocation = '{0}assets/8.9.1/img/champion/{1}';

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

   public generate(championPicks: ChampionPicks) {
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
}
