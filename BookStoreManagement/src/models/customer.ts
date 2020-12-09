import { Address } from './address';

export class Customer {
    customerId : number;
    email : string;
    fullName : string;
    password : string;
    address : Address = new Address();
    mobileNumber : string;
    registerOn : Date;
}
