import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../login.module';
import { ListService } from '../service/list.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = new Login;
  roles : string[];
  flag : boolean;

  constructor(private router : Router,private service : ListService) { 
      this.roles = ["ADMIN","CUSTOMER"];
   }

  ngOnInit() {
  }

  userLogin(){
    if (this.service.validate(this.login)) {
      this.router.navigate(['list']);
    }
    else{
      alert("Login failed");
    }
  }

}
