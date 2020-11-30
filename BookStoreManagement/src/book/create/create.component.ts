import { Component, OnInit } from '@angular/core';
import { Book } from 'src/models/book';
import { Category } from 'src/models/category';
import { Review } from 'src/models/review';
import { BookService } from 'src/book/service/book.service';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateBookComponent implements OnInit {
  book : Book;  
  categoryList : Category[]= [];
  selectedCategory : string = "";
  category : Category;
  display : String ="Other";


  constructor(private service : BookService) { 
   this.book=new Book();
  }

  ngOnInit() {
    this.service.listAllCategories().subscribe(data => this.categoryList= data);
    console.log(this.categoryList[0]);
    
  }

  selectChangeHandler (event: any) {
    //update the ui
    this.selectedCategory = event.target.value;
    console.log(this.selectedCategory)
    if(this.selectedCategory == "Others"){
      this.selectedCategory=prompt("Enter the new category");
      if(this.selectedCategory.length != 0)
      {
      this.createCategory(this.selectedCategory);
      this.display=this.selectedCategory;
      }
    }
  }

  
  createCategory(cat: string){
    this.category = new Category();
    this.category.categoryName=cat;
    this.service.addCategory(this.category);
  }


  saveBook(){
   this.service.create(this.book);
  }

}
