import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from 'src/models/review';
import { ReviewService } from 'src/review/services/review.service';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  review : Review;

  constructor(private router : Router, private service : ReviewService ) { 
    this.review = new Review();
   }

  ngOnInit() {
  }

  saveReview(){
    this.review.reviewOn = new Date();
    this.service.addReview(this.review);
    this.router.navigate(['listReviews']);
  //  alert("insaveReview");


  }

}
