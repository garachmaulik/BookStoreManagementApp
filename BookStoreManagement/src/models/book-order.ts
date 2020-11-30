import { Address } from './address';
import { Customer } from './customer';

export class BookOrder {
    orderId : number;
    customer : Customer = new Customer();
    orderDate : Date;
    orderTotal : number;
    status : string;
    shippingAddress : Address;
    paymentMethod : string;
    recipientName : string;
    recipientPhone : string;
}
