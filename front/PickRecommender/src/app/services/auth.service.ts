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
  private readonly emptyToken = new Token('', '', undefined);
  loggedUserToken: Token;
  headers = new HttpHeaders({'Content-Type': 'application/json' });
  appUrl = 'http://localhost:8081/';

  constructor(protected http: HttpClient, protected router: Router) {
  }

  init() {
    const item = window.localStorage.getItem('currentUser');

    if (!HelperFunctions.isEmptyValue(item)) {
      if (!HelperFunctions.containsEmptyValues(item) && item === this.emptyToken) {
        const ls = JSON.parse(window.localStorage.getItem('currentUser'));
        this.loggedUserToken = new Token(ls['username'], ls['realm'], ls['id'], ls['token']);
      }
      if(HelperFunctions.containsEmptyValues(this.loggedUserToken)) {
        const ls = JSON.parse(window.localStorage.getItem('currentUser'));
        this.loggedUserToken = new Token(ls['username'], ls['realm'], ls['id'], ls['token']);
      }
    }
  }

  storeToken() {
    window.localStorage.setItem('currentUser', JSON.stringify(this.loggedUserToken));
  }

  register(user: SummonerAuth) {
    return this.http.post(this.appUrl + 'auth/register', user);
  }

  test() {
    return this.http.get(this.appUrl + 'auth/test', {responseType:'text'})
                    .subscribe(data => console.log('Got: ', data));
  }

  login(loginInfo: AuthenticationRequest) {
    return this.http.post<Token>(this.appUrl + 'auth/login', loginInfo)
      .subscribe(ret => {
        this.loggedUserToken =  new Token(loginInfo.username, ret['realm'], ret['id'], ret['token']);
        this.storeToken();
        this.logger.next(true);
        this.router.navigateByUrl('');
      });
  }

  logout() {
    window.localStorage.clear();
    this.loggedUserToken = null;
    this.router.navigate(['/home']);
    this.logger.next(false);
  }

  getJSONAuthHeader(contentType?: any): HttpHeaders {
    let tokenStr = null;

    if(!HelperFunctions.isEmptyValue(this.loggedUserToken)){
      tokenStr = this.loggedUserToken === null ? '' : this.loggedUserToken.token;

      if(HelperFunctions.isEmptyValue(contentType)) {
        return new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + tokenStr
        });
      } else {
        return new HttpHeaders({
          'Content-Type': contentType,
          'Authorization': 'Bearer ' + tokenStr
        });
      }

    } else {
      return null;
    }
  }
  
  getFORMHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded',
    });
  }

  getAuthHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + this.loggedUserToken.token,
    });
  }

  getAuthHeaderMultipart(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + this.loggedUserToken.token,
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
      this.loggedUserToken = new Token(ls['username'], ls['realm'], ls['id'], ls['token']);
      this.storeToken();
      token = this.loggedUserToken;
    }
    
    return token;
  }

  isLoggedIn(): Observable<boolean> {
    return this.logger.asObservable();
  }

  isLoggedInSimple(): boolean {
    const token = this.getToken();
    return token !== this.emptyToken && !HelperFunctions.isEmptyValue(token);
  }
}
