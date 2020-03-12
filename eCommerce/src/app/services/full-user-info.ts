import { RegisterAddress } from './registerAddress-info';


export class User {
    userId :number;
    firstName: string;
    surname: string;
   
    username: string;
    email: string;
    contactNumber: string;
    address: RegisterAddress;
    
 
 
    constructor(userId :number,firstName: string, surname: string,username: string, email: string,contactNumber: string,address:RegisterAddress) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
 
        
    }




}