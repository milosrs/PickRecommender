import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Token } from '../../model/token';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private token: Token;
  private isLoggedIn = false;
  
  constructor(protected auth: AuthService) {  }

  ngOnInit() {
    this.token = this.auth.getToken();
    this.isLoggedIn = this.auth.isLoggedInSimple();
    console.log(this.isLoggedIn);
  }

}
