import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customerList : Customer[] = [];
  private baseUrl : string = "http://localhost:8080";

  constructor(private router : Router, private http : HttpClient) { }

  createCustomer(customer : Customer) {
    return this.http.post(this.baseUrl + "/customer/add", customer).subscribe(
      data => data = customer
    );
  }

  getList() {
    return this.http.get<Customer[]>(this.baseUrl + "/customer/getlist");
  }

  deleteCustomerbyId(id : number) {
    return this.http.get<Customer>(this.baseUrl + "/customer/deleteCustomer?id=" + id);

  }

  update(customer : Customer) {
    return this.http.post(this.baseUrl + "/customer/update", customer).subscribe(
      data => data = customer
    );
  }

  searchByCustomerId(id : number) {
    return this.http.get<Customer>(this.baseUrl + "/customer/get/" + id);
  }

  getByIndex(index: number) {
    return this.customerList[index];
  }

  findCust(email : string, pass : string){
    return this.http.get<Customer>(`${this.baseUrl}/customer/findCustomer?email=${email}&pass=${pass}`);
  }
}
