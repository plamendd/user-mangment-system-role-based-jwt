import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/models/user.model';
import { AutheticationService } from 'src/app/services/authetication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
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
  register(){
    this.authenticationService.register(this.user).subscribe(data => {
      this.router.navigate(['/login']);
    }, err => {
      // implemented as .CONFILCT in backed.
      if(err?.status === 409){
        this.errorMessage = 'Username already exist.';
      } else {
        this.errorMessage = 'Unexpected error occured.';
        console.log(err);
      }
    }
    )
  }

}
