import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/models/user.model';
import { AutheticationService } from 'src/app/services/authetication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();
  faUser = faUserCircle;
  errorMessage: string = "";

  constructor(private authenticationService: AutheticationService, private router: Router ) {
    
  }

 ngOnInit(): void {
   if (this.authenticationService.currentUserValue?.id){
     this.router.navigate(['profile']);
   }
 }
 login(){
  this.authenticationService.login(this.user).subscribe(data =>{
    this.router.navigate(['profile']);
  }, error => {
    this.errorMessage = 'Username or password is incorrect';
    console.log(error);
  });
 }

}
