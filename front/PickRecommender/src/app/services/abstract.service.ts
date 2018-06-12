import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractService<Entity, Key> {

  actionUrl = 'http://localhost:8081/';

  constructor(protected http: HttpClient, protected url: string, protected authService: AuthService) {
    this.actionUrl = this.actionUrl + url;
    this.authService.init();
  }
  getAll(): Observable<any[]> {
    return this.http.get(this.actionUrl, {headers: this.authService.getJSONAuthHeader()})
      .pipe(map(resp => resp as Entity[]));
  }

  getAllByZone(zoneId: number): Observable<Entity[]> {
    return this.http.get(this.actionUrl + '/getall/' + zoneId, {headers: this.authService.getJSONAuthHeader()})
      .pipe(map(resp => resp as Entity[]));
  }

  getOne(id: Key): Observable<any> {
    return this.http.get(`${this.actionUrl}/${id}`, {headers: this.authService.getJSONAuthHeader()})
      .pipe(map(resp => resp as Entity[]));
  }

  insert(toInsert: any): Observable<Entity> {
    console.log(this.actionUrl + '/new');
    return this.http.post(this.actionUrl + '/new', toInsert, {headers: this.authService.getJSONAuthHeader()})
      .pipe(map(resp => resp as Entity));
  }

  delete(toDelete: Key): Observable<any> {
    return this.http.delete(this.actionUrl + '/delete/' + toDelete, { headers: this.authService.getJSONAuthHeader(),
      observe: 'response', responseType: 'text' });
  }

  update(toUpdate: any): Observable<any> {
    return this.http.put(this.actionUrl + '/update/', toUpdate, {headers: this.authService.getJSONAuthHeader()});
  }

}
