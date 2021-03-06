import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { RegistrationComponent } from '../components/registration/registration.component';
import { MasterComponentComponent } from '../components/master-component/master-component.component';
import { LoginGuard } from './guards/login.guard';
import { ChampionPicksComponent } from '../components/champion-picks/champion-picks.component';
import { EmptyInfoGuard } from './guards/empty-info.guard';
import { RecommendedChampsComponent } from '../components/recommended-champs/recommended-champs.component';
import { EmptyRecommendationsGuard } from './guards/empty-recommendations.guard';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: '',
    component: MasterComponentComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'picker',
    component: ChampionPicksComponent,
    canActivate: [LoginGuard, EmptyInfoGuard]
  },
  {
    path: 'recommendations',
    component: RecommendedChampsComponent,
    canActivate: [EmptyRecommendationsGuard]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }


