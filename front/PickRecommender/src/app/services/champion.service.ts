import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { Champion } from '../model/champion';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Token } from '../model/token';
import { ChampionViewList } from '../model/champion-view-list';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChampionService extends AbstractService<Champion, number> {

  private token: Token;
  private champViewList: ChampionViewList;

  constructor(protected http: HttpClient, protected auth: AuthService) {
    super(http, 'champions', auth);
   }

   init() {
    this.token = this.auth.getToken();
    this.champViewList = this.getAllForList();
   }

   public getAllForList() {
    return this.http.get(this.actionUrl, {headers: this.auth.getJSONAuthHeader()})
            .pipe(map(resp => resp as ChampionViewList));
   }

   public getDataForChampion(champId: number) {
    return this.http.get(this.actionUrl + '/' + champId, {headers: this.auth.getJSONAuthHeader()})
            .pipe(map(resp => resp as Champion));
   }

   public getChampViewList(): Observable<ChampionViewList> {
     return new Observable(
       observer => {
         observer.next(this.champViewList);
       }
     );
   }
}
