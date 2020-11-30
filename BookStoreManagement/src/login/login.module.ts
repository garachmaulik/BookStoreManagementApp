import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ValidateComponent } from './validateUser/validateUser.component';
import { CreateComponent } from './createCustomer/createCustomer.component';

@NgModule({
  declarations: [ValidateComponent, CreateComponent],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ],
  exports : [ValidateComponent, CreateComponent]
})

export class LoginModule { }
