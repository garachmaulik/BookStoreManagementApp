import { Category } from './category';

export class Book {
    bookId : number;
    title : string;
    author : string;
    category : Category = new Category();
    description : string;
    isbn : string;
    price : number;
    publishDate: Date;
    localDate : Date;
}
