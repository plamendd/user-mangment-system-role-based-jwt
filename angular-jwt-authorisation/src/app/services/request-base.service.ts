import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { AutheticationService } from './authetication.service';


export abstract class RequestBaseService {
  protected currentUser: User = new User;

 protected constructor(protected authenticationService: AutheticationService, protected http: HttpClient) {
      this.authenticationService.currentUser?.subscribe(data => {
        this.currentUser = data;
      });
  }
  get getHeaders(): HttpHeaders{
    return new HttpHeaders(
      {
        authorization: 'Bearer ' + this.currentUser?.accessToken,
        "Content-Type": "application/json; charset=UTF-8"
      }
    )
  }
}
