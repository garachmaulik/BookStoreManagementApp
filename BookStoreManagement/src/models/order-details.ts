import { Book } from './book';
import { BookOrder } from './book-order';

export class OrderDetails {
    id : number;
    book : Book = new Book();
    bookOrder : BookOrder = new BookOrder();
    quantity : number;
    subtotal : number;
}
