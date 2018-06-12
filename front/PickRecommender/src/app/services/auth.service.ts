import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HelperFunctions } from '../shared/util/helper-functions';
import { Token } from '../model/token';
import { SummonerAuth } from '../model/summonerAuth';
import { AuthenticationRequest } from '../model/authentication-request';
import { map } from 'rxjs/operators';
import { AbstractService } from './abstract.service';
import { Summoner } from '../model/summoner';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private logger = new Subject<boolean>();
  private readonly emptyToken = '{}';
  loggedUserToken: Token;
  headers = new HttpHeaders({'Content-Type': 'application/json' });
  appUrl = 'http://localhost:8081/';

  constructor(protected http: HttpClient, protected router: Router) {
    this.loggedUserToken = new Token('', '');
  }

  init() {
    const item = window.localStorage.getItem('currentUser');

    if (!HelperFunctions.isEmptyValue(item)) {
      if (!HelperFunctions.containsEmptyValues(item) && item === this.emptyToken) {
        const ls = JSON.parse(window.localStorage.getItem('currentUser'));
        this.loggedUserToken = new Token(ls['username'], ls['realm']);
      }
    }
  }

  storeToken() {
    window.localStorage.setItem('currentUser', JSON.stringify(this.loggedUserToken));
  }

  register(user: SummonerAuth) {
    return this.http.post(this.appUrl + 'auth/register', user);
  }

  login(loginInfo: AuthenticationRequest) {
    return this.http.post(this.appUrl + 'auth/login', loginInfo)
      .pipe(map(ret => {
        this.loggedUserToken =  new Token(loginInfo.username, ret['realm'], ret['token']);
        this.storeToken();
        this.logger.next(true);
      }));
  }

  logout() {
    window.localStorage.clear();
    this.loggedUserToken = null;
    this.router.navigate(['/home']);
    this.logger.next(false);
  }

  getJSONAuthHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.loggedUserToken === null ? '' : this.loggedUserToken.token,
      'Bearer ' : this.loggedUserToken === null ? '' : this.loggedUserToken.token
    });
  }
  getFORMHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded',
    });
  }
  getAuthHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + this.loggedUserToken.token,
      'Bearer ' : this.loggedUserToken === null ? '' : this.loggedUserToken.token
    });
  }
  getAuthHeaderMultipart(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + this.loggedUserToken.token,
      'Bearer ' : this.loggedUserToken === null ? '' : this.loggedUserToken.token
    });
  }
  getJSONHeader(): HttpHeaders {
    return this.headers;
  }
  getToken(): Token {
    let token = null;
    const storage = window.localStorage.getItem('currentUser');
    const emptyToken = '{}';

    if (this.loggedUserToken != null) {
      token = this.loggedUserToken;
    } else if (!HelperFunctions.containsEmptyValues(storage) && storage !== emptyToken) {
      const ls = JSON.parse(window.localStorage.getItem('currentUser'));
      this.loggedUserToken = new Token(ls['username'], ls['realm'], ls['token']);
      this.storeToken();
      token = this.loggedUserToken;
    }
    // console.log('Token: ' + token);
    return token;
  }

  isLoggedIn(): Observable<boolean> {
    return this.logger.asObservable();
  }

  isLoggedInSimple(): boolean {
    return this.getToken() !== null;
  }
}
