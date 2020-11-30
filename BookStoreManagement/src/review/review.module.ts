import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddComponent } from './add/add.component';
import { UpdateComponent } from './update/update.component';
import { ViewComponent } from './view/view.component';
import { DeleteComponent } from './delete/delete.component';
import { ListComponent } from './list/list.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  declarations: [AddComponent, UpdateComponent, ViewComponent, DeleteComponent, ListComponent],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ],
  exports :[AddComponent, UpdateComponent, ViewComponent, DeleteComponent, ListComponent]
})
export class ReviewModule { }
