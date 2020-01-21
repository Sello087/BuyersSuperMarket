export class RegisterUser {
    firstName: string;
    surname: string;
    name: String;
    username: string;
    email: string;
    contactNumber: string;
    password: string;
    addId: number;
    role: string[];
 
    constructor(firstName: string, surname: string,name: String,username: string, email: string,contactNumber: string, password: string) {
        this.firstName = firstName;
        this.surname = surname;
        this.name = name;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.addId = 1;
        this.role = ['user'];
        
    }




}