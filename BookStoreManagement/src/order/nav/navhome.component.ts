import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navhome',
  templateUrl: './navhome.component.html'
})
export class NavHomeComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }
logOut(){
  sessionStorage.removeItem('userId');
  this.router.navigate(['login']);
}
}
