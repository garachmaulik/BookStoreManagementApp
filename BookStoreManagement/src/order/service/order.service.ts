import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/models/book';
import { OrderDetails } from 'src/models/order-details';
import { tap } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseUrl = "http://localhost:8080";

  constructor(private http : HttpClient) { }

  addOrder(orderDetails : OrderDetails){ 
    this.http.post(this.baseUrl + "/order/add", orderDetails).subscribe(data => data = orderDetails);
  }
  listAllOrders(){
    return this.http.get<OrderDetails[]>(this.baseUrl + "/order/listAllOrders");
  }
  listAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.baseUrl + "/book/list");
  }
  listOrdersForCustomer(custId : number){
    return this.http.get<OrderDetails[]>(this.baseUrl + "/order/listOrderByCustomer?id=" + custId);
  }
}
