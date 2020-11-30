import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { OrderModule } from 'src/order/order.module';
import { ReviewModule } from 'src/review/review.module';
import { LoginModule } from 'src/login/login.module';
import { BookModule } from 'src/book/book.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    OrderModule,
    FormsModule,
    ReviewModule,
    LoginModule,
    BookModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
