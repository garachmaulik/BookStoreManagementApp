import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateBookComponent } from 'src/book/create/create.component';
import { CreateComponent } from 'src/login/createCustomer/createCustomer.component';
import { ValidateComponent } from 'src/login/validateUser/validateUser.component';
import { AddOrderComponent } from 'src/order/add-order/add-order.component';
import { HomepageAdminComponent } from 'src/order/homepage-admin/homepage-admin.component';
import { HomepageCustomerComponent } from 'src/order/homepage-customer/homepage-customer.component';
import { MyOrdersComponent } from 'src/order/my-orders/my-orders.component';
import { AddComponent } from 'src/review/add/add.component';
import { ListComponent } from 'src/review/list/list.component';

const routes: Routes = [ 
  { path:'', redirectTo:'login', pathMatch:'full'},
  { path:'homeadmin', component: HomepageAdminComponent },
  { path:'home', component: HomepageCustomerComponent },
  { path:'myOrders', component: MyOrdersComponent },
  { path:'placeOrder', component: AddOrderComponent },
  { path:'addreview' , component: AddComponent },
  { path:'listReviews', component: ListComponent },
  { path:'login', component: ValidateComponent },
  { path:'register', component: CreateComponent },
  { path:'addBook', component: CreateBookComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
