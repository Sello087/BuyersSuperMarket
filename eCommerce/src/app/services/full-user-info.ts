

export class User {
    userId :number;
    firstName: string;
    surname: string;
    supplyName: string;
    username: string;
    email: string;
    contactNumber: string;
    password: string;
    addId: number;
    role: string[];
 
 
    constructor(userId :number,firstName: string, surname: string,supplyName: string,username: string, email: string,contactNumber: string   ) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.supplyName = supplyName;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        
 
        
    }




}