import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddOrderComponent } from './add-order/add-order.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { HomepageAdminComponent } from './homepage-admin/homepage-admin.component';
import { HomepageCustomerComponent } from './homepage-customer/homepage-customer.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { NavComponent } from './nav/navadmin.component';
import { NavHomeComponent } from './nav/navhome.component';


@NgModule({
  declarations: [AddOrderComponent, MyOrdersComponent, HomepageAdminComponent, HomepageCustomerComponent, NavComponent, NavHomeComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  exports: [AddOrderComponent, MyOrdersComponent, HomepageAdminComponent, HomepageCustomerComponent, NavComponent, NavHomeComponent]
})
export class OrderModule { }