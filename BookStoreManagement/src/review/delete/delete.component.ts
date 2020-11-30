import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReviewService } from 'src/review/services/review.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  constructor(private router : Router, private service : ReviewService) { }

  ngOnInit() {
  }

  delete(index :number){
    var ans = confirm("Are you sure you want to delete?")
    if(ans)
      this.service.deleteReview(index)
  }

}
