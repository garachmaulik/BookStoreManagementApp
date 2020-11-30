import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookModule } from 'src/book/book.module';
import { Book } from 'src/models/book';
import { Category } from 'src/models/category';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl: String = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }
  create(book: Book) {
    this.http.post(this.baseUrl + "/book/add", book).subscribe(
      data => data = book);
    alert("New Book added");
  }

  listAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl + "/category/viewAllCategories");
  }

  addCategory(category: Category) {
    this.http.post(this.baseUrl + "/category/addCategory", category).subscribe(
      data => data = category);
    alert("New Category added");
  }
}
