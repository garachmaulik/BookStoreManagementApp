import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userList : User[] = [];
  private baseUrl : string = "http://localhost:8080";

  constructor(private router : Router, private http : HttpClient) { }

  addUser(user : User) {
    this.http.post(this.baseUrl + "/user/add", user).subscribe(
      data => data = user
    );
  }

  removeUserbyId(id) {
    return this.http.get<User>(this.baseUrl + "/user/delete/" + id);
  }

  searchByUserId(id : number) {
    return this.http.get<User>(this.baseUrl + "/user/validate/" + id);
  }
}
