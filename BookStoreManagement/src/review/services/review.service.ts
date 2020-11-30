import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from 'src/models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  ReviewList : Review[] = [];
  private baseURL : string = "http://localhost:8080";
  
constructor(private router : Router, private http : HttpClient) { }

  addReview(review : Review) {
    this.http.post(this.baseURL + "/review/add", review).subscribe(
    data => data = review );
    this.ReviewList.push(review);    
  }

  getList() {
    return this.http.get<Review[]>(this.baseURL + "/review/list");
    //return this.ReviewList
  }

  deleteReview(index : number) : Review[]{
    return this.ReviewList.splice(index,1)
  }


}