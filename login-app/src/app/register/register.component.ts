import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Register } from '../register.model';
import { ListService } from '../service/list.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  register : Register;
  roles : string[];
  flag : Boolean;

  constructor(private router : Router, private service : ListService) {
    this.roles = ["ADMIN","CUSTOMER"];
    this.register = new Register();
   }

  ngOnInit() {
  }

  add(){
    this.service.addUser(this.register);
    this.router.navigate(['login']);
  }

  loggedIn(){
    this.flag = true;
  }
}
