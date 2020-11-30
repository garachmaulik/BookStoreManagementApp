import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/models/address';
import { Customer } from 'src/models/customer';
import { User } from 'src/models/user';
import { CustomerService } from '../services/customer.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-create',
  templateUrl: './createCustomer.component.html',
  styleUrls: ['./createCustomer.component.css']
})
export class CreateComponent implements OnInit {
  customer : Customer;
  address : Address;
  user : User;

  constructor(private router: Router, private service : CustomerService, private uService : UserService) { 
    this.address = new Address();
    this.customer = new Customer();
    this.user=new User();
    this.user.role="Customer";
  }

  ngOnInit() {
  }
  
  saveCustomer() {
    this.service.createCustomer(this.customer);
    this.uService.addUser(this.user);
    this.router.navigate(['login']);
  }
}
