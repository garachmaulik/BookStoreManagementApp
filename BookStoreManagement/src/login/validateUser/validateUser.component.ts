import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/models/user';
import { CustomerService } from '../services/customer.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-validate',
  templateUrl: './validateUser.component.html',
  styleUrls: ['./validateUser.component.css']
})

export class ValidateComponent implements OnInit {
  user: User;
  validUser: User;

  constructor(private service: UserService, private router: Router, private custService: CustomerService) {
    this.user = new User();
  }

  ngOnInit() {
  }

 async login() {
    await this.service.validate(this.user).then(data=> this.validUser = data);
    if (this.validUser != null) {
      if (this.validUser.role == "Admin") {
        sessionStorage.userId = this.validUser.userId;
         this.router.navigate(['homeadmin']);
      }
      else{
        this.custService.findCust(this.validUser.email, this.validUser.password).subscribe(data=> sessionStorage.userId = data.customerId);
        this.router.navigate(['home']);
      }
    }
    else{
      alert("Login failed. Please try again");
    }
  }
}
