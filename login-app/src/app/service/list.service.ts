import { Injectable } from '@angular/core';
import { Login } from '../login.module';
import { Register } from '../register.model';

@Injectable({
  providedIn: 'root'
})
export class ListService {

  userList : Register[] = [];

  constructor() { }

  addUser(register : Register){
    this.userList.push(register);
  }

  listUsers() : Register[]{
    return this.userList;
  }

  validate(user : Login) : boolean{
    let flag = false;
    for (let index = 0; index < this.userList.length; index++) {
      if (this.userList[index].userName == user.userName 
        && this.userList[index].password == user.password 
        && this.userList[index].role == user.role) {
      flag = true;
      }
    }
   return flag;
  }
}
