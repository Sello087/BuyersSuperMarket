

export class User {
    userId :number;
    firstName: string;
    surname: string;
   
    username: string;
    email: string;
    contactNumber: string;
    
 
 
    constructor(userId :number,firstName: string, surname: string,username: string, email: string,contactNumber: string) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        
 
        
    }




}