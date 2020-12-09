import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from 'src/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl : string = "http://localhost:8080";
  constructor(private router : Router, private http : HttpClient) { }

  validate(user : User) : Promise<User>{
    const param = new HttpParams().append('email', user.email).append('pass', user.password);
    return this.http.get<User>(this.baseUrl + '/user/validate', {params : param}).toPromise();
  }
}
