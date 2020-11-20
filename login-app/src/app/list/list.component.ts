import { Component, OnInit } from '@angular/core';
import { Register } from '../register.model';
import { ListService } from '../service/list.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  userList : Register[] = [];

  constructor(private service : ListService) {}

  ngOnInit() {
    this.userList = this.service.listUsers();
  }

}
