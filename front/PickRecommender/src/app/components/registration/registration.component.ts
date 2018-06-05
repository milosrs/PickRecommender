import { Component, OnInit, NgModule } from '@angular/core';
import { HelperFunctions } from '../../shared/util/helper-functions';
import {Router} from '@angular/router';
import { Message } from '../../shared/model/message';
import { CommonModule } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import { NgForm } from '@angular/forms';

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
    name: null,
    lastName: null,
    host: 'tmp'
  };
  private errorMessage = null;

  constructor(protected rt: Router) {
  }

  ngOnInit() {
  }

  tryRegister() {
    const areAnyEmptyValues = HelperFunctions.containsEmptyValues(this.regInfo);
    const arePasswordsMatching = this.regInfo.password === this.repeatPW;
    const shouldSendToServer = !areAnyEmptyValues && arePasswordsMatching;

    if (shouldSendToServer) {
      this.regInfo.host = null;
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
    this.regInfo.name = '';
    this.regInfo.lastName = '';
    this.regInfo.userName = '';
    this.regInfo.host = 'tmp';
  }

  clearImportantDetails() {
    this.regInfo.password = '';
    this.repeatPW = '';
  }
}
