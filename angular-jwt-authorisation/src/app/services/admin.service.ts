import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AutheticationService } from './authetication.service';
import { RequestBaseService } from './request-base.service';

const API_URL = environment.BASE_URL + '/api/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService extends RequestBaseService{

  constructor(autheticationService: AutheticationService, http: HttpClient) { 
    super(autheticationService, http);
  }
  findAllUsers(): Observable<any>{
    return this.http.get(API_URL + '/all', {headers: this.getHeaders});
  }
}
