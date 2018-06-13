import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AbstractService } from './abstract.service';
import { Champion } from '../model/champion';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SummonerService extends AbstractService<Champion, number> {

  private imageLocation = '{0}assets/8.9.1/img/champion/{1}.png';

  constructor(protected http: HttpClient, protected auth: AuthService) {
    super(http, 'champions', auth);
  }

  init() {

  }

  getImageLocation(whereInFolders:string, championName: string): string {
    return this.imageLocation.replace('{0}', whereInFolders).replace('{1}', championName);
  }
}
