import { Book } from './book';
import { Customer } from './customer';

export class Review {
    reviewId : number;
    book : Book;
    customer : Customer;
    headLine : string;
    comment : string;
    rating : number;
    reviewOn : Date;
}
