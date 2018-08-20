import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private isLoggedIn: boolean;

  constructor(private auth: AuthService) { }

  ngOnInit() {
    this.isLoggedIn = this.auth.isLoggedInSimple();
    this.auth.isLoggedIn().subscribe(
      logged => {
        this.isLoggedIn = logged;
      }
    );
  }

  logout() {
    this.auth.logout();
  }
}
