import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ChampionService } from '../../services/champion.service';
import { HelperFunctions } from '../../shared/util/helper-functions';

@Injectable({
  providedIn: 'root'
})
export class EmptyInfoGuard implements CanActivate {

  constructor(protected champService: ChampionService, protected router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      const isValid = !HelperFunctions.isEmptyValue(this.champService.getPlayerPosition()) &&
                      !HelperFunctions.isEmptyValue(this.champService.getFirstPick());         
      if(!isValid) {
        this.router.navigate(['/']);
      }

      return isValid;
  }
}
