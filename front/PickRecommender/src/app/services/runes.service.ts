import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { RuneRecommendation } from '../model/rune-recommendation';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { AuthService } from './auth.service';
import { Token } from '../model/token';

@Injectable({
  providedIn: 'root'
})
export class RunesService{

  private token: Token;

  constructor(protected http: HttpClient, protected auth: AuthService) {
  }

  init() {
    this.token = this.auth.getToken();
  }

  public generateRecommendation(championId: number, playerPosition: string) {
    const header = this.auth.getJSONAuthHeader();
    return this.http.post('http://localhost:8081/runes/' + championId, playerPosition, {headers: header});
  }
}
