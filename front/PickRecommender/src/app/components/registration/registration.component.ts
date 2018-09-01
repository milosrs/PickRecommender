import { Component, OnInit, NgModule } from '@angular/core';
import { HelperFunctions } from '../../shared/util/helper-functions';
import {Router} from '@angular/router';
import { Message } from '../../shared/model/message';
import { CommonModule } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import { NgForm } from '@angular/forms';
import { RealmList } from '../../model/realm-list';
import { AuthService } from '../../services/auth.service';
import { SummonerAuth } from '../../model/summonerAuth';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgForm
  ],
})
export class RegistrationComponent implements OnInit {
  private registered = false;
  private repeatPW = '';
  private regInfo = {
    userName: null,
    password: null,
    realm: null
  };
  private errorMessage = null;
  private serverList: any;

  constructor(protected auth: AuthService, protected router: Router) {
  }

  ngOnInit() {
    this.serverList = new RealmList();
    this.serverList = this.serverList.transformToObjectList();
  }

  tryRegister() {
    debugger;
    const areAnyEmptyValues = HelperFunctions.containsEmptyValues(this.regInfo);
    const arePasswordsMatching = this.regInfo.password === this.repeatPW;
    const shouldSendToServer = !areAnyEmptyValues && arePasswordsMatching;

    if (shouldSendToServer) {
      this.errorMessage = null;
      const summonerAuth = new SummonerAuth(this.regInfo.userName, this.regInfo.password, this.regInfo.realm);
      this.auth.register(summonerAuth).subscribe(data => this.router.navigate(['/picker']),
                                                  err => this.errorMessage = 'Error registering you. Please try again.');
    } else {
      this.clearImportantDetails();
      if (arePasswordsMatching === false) {
        this.errorMessage = 'Passwords don\'t match. Please, try again.';
      } else if (areAnyEmptyValues) {
        this.errorMessage = 'Some fields were left empty. Please, fill in the form and try again.';
      } else {
        this.errorMessage = 'Error registering you. Please, try again.';
      }
    }
  }

  hideError() {
    this.errorMessage = null;
  }

  validateInformation(param): boolean {
    return HelperFunctions.isEmptyValue(param);
  }

  clearAllInfo() {
    this.regInfo.password = '';
    this.repeatPW = '';
    this.regInfo.realm = '';
    this.regInfo.userName = '';
  }

  clearImportantDetails() {
    this.regInfo.password = '';
    this.repeatPW = '';
  }
}
