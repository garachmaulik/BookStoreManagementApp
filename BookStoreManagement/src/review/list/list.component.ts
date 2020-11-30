import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from 'src/models/review';
import { ReviewService } from 'src/review/services/review.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  reviewList : Review[] = [];

  constructor( private service : ReviewService ) { }

  ngOnInit() {
    this.service.getList().subscribe(data => this.reviewList = data);
  }

}
