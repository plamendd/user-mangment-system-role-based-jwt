import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from './models/role.enum';
import { User } from './models/user.model';
import { AutheticationService } from './services/authetication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {

  title = 'angular-jwt-authorisation';

  currentUser: User = new User;

  constructor(private authenticationService: AutheticationService, private router: Router){
    this.authenticationService.currentUser?.subscribe(data => {
      this.currentUser = data;
    });
  }
  isAdmin(){
    return this.currentUser?.role === Role.ADMIN;
  }
  logOut(){
    this.authenticationService.logOut();
    this.router.navigate(['/login']);
  }
}
