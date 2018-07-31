import { Component, OnInit, NgModule, EventEmitter, Input, Output } from '@angular/core';
import { HelperFunctions } from '../../shared/util/helper-functions';
import { Message } from '../../shared/model/message';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { AuthenticationRequest } from '../../model/authentication-request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgForm
  ],
})
export class LoginComponent  implements OnInit {
  private errorMessage = null;
  private logInfo = {
    uname: '',
    password: ''
  };

  constructor(protected auth: AuthService, private router : Router) {
  }

  ngOnInit() {
  }

  clearAllInfo() {
    this.logInfo.uname = '';
    this.logInfo.password = '';
  }

  tryLogin() {
    if (!HelperFunctions.containsEmptyValues(this.logInfo)) {
      this.errorMessage = null;
      const authRequest = new AuthenticationRequest(this.logInfo.uname, this.logInfo.password);
      this.auth.login(authRequest);
    }
  }

  hideError() {
    this.errorMessage = null;
  }
}
