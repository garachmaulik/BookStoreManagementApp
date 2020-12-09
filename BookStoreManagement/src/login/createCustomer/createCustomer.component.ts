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

  constructor(private router: Router, private service : CustomerService, private uService : UserService) { 
    this.customer = new Customer();
  }

  ngOnInit() {
  }
  
  saveCustomer() {
    this.initiate();
    this.service.createCustomer(this.customer);
    this.router.navigate(['login']);
  }
  
  initiate(){
    this.customer.registerOn = new Date();
  }
}
