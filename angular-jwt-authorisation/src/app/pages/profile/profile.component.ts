import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/models/role.enum';
import { User } from 'src/app/models/user.model';
import { AutheticationService } from 'src/app/services/authetication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User = new User;
  errorMessage: string = "";

  constructor(private userService: UserService,
              private authenticationService: AutheticationService,
              private router: Router) { }

  ngOnInit(): void {
    this.authenticationService.currentUser?.subscribe(data => {
      this.currentUser = data;
    });
  }

  changeRole() {
    const newRole = this.currentUser.role === Role.ADMIN ? Role.USER : Role.ADMIN;

    this.userService.changeRole(newRole).subscribe(() => {
      this.authenticationService.logOut();
      this.router.navigate(['/login']);
    }, error => {
      this.errorMessage = 'Unexpected error occurred.';
      console.log(error);
    })
  }

}
