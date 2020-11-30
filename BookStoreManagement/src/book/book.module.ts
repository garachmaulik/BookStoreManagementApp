import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { CreateBookComponent } from './create/create.component';
import { OrderModule } from 'src/order/order.module';

@NgModule({
  declarations: [CreateBookComponent],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule,
    OrderModule
  ],
  exports: [CreateBookComponent]
})
export class BookModule { }
