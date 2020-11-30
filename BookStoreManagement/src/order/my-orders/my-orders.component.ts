import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/models/customer';
import { OrderDetails } from 'src/models/order-details';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  myOrdersList : OrderDetails[] = [];
  private custId : number =  97 //sessionStorage.userId;  

  constructor(private router : Router, private orderService : OrderService) { 
   }

  ngOnInit() {
    this.orderService.listOrdersForCustomer(this.custId).subscribe(data => this.myOrdersList = data);
    console.log(this.myOrdersList[0]);
  }

}
