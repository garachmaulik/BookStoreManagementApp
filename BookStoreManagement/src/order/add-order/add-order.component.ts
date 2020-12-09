import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/models/book';
import { OrderDetails } from 'src/models/order-details';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {

  private bookList : Book[] = [];
  private orderDetails : OrderDetails;
  private paymentMethod : string[] = [];
  private bookPrice = new FormControl('');
  private custId : number = sessionStorage.userId;
  private subTotal = new FormControl('');
  
  constructor(private router : Router,private orderService : OrderService) { 
    this.orderDetails = new OrderDetails();
    this.paymentMethod = ["Pay on delivery", "Debit Card"];
  }

  ngOnInit() {
    this.orderService.listAllBooks().subscribe(data => this.bookList =  data);
    console.log(this.custId);
  }

   addOrder(){
     this.initiate();
     this.orderService.addOrder(this.orderDetails);
     alert("Order placed. Hurray!!");
     this.router.navigate(['myOrders']);
   }

   initiate(){
    this.orderDetails.bookOrder.orderDate = new Date();
    this.orderDetails.bookOrder.status = "Order Placed";
    this.orderDetails.subtotal = this.subTotal.value;
    this.orderDetails.bookOrder.orderTotal = this.subTotal.value;
    this.orderDetails.bookOrder.customer.customerId = this.custId;
   }

   setPrice(bId : number){
    for (let index = 0; index < this.bookList.length; index++) {
      if(this.bookList[index].bookId == bId){
          this.bookPrice.setValue(this.bookList[index].price);
      }
    }
   }
   setSubTotal(quantity : number){
     this.subTotal.setValue(this.bookPrice.value * quantity);
  }

}
