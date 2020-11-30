import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-validate',
  templateUrl: './validateUser.component.html',
  styleUrls: ['./validateUser.component.css']
})

export class ValidateComponent implements OnInit {
  userId : number;
  user : User;
  roles : string[];
  flag : boolean;
  submitted : boolean;

  constructor(private service : UserService, private router : Router) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  loggedIn(){
    this.service.searchByUserId(this.userId).subscribe(data => this.user = data);
    if(this.user != null){
      this.submitted = true;
      this.flag=true;
      sessionStorage.userId=this.userId;
      let id =sessionStorage.userId;
      if(this.user.role == "Admin"){
        this.router.navigate(['homeadmin']);
      }
      this.router.navigate(['home']);
    }
  }
  
}
