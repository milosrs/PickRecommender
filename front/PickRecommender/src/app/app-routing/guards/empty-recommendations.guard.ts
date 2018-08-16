import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ChampionService } from '../../services/champion.service';
import { HelperFunctions } from '../../shared/util/helper-functions';

@Injectable({
  providedIn: 'root'
})
export class EmptyRecommendationsGuard implements CanActivate {
  constructor(private championService: ChampionService, private router:Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      const shouldRedirect = HelperFunctions.isEmptyValue(this.championService.getRecommendations());

      if(shouldRedirect) {
        this.router.navigate(['']);
      }

      return !shouldRedirect;
  }

  
}
