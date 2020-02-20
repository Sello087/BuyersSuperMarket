import { RegisterAddress } from '../services/registerAddress-info';

export class RegisterUser {
    firstName: string;
    surname: string;
    supplyName: string;
    username: string;
    email: string;
    contactNumber: string;
    password: string;
    addId: number;
    role: string[];
    objAddress:  RegisterAddress;
 
    constructor(firstName: string, surname: string,supplyName: string,username: string, email: string,contactNumber: string, password: string,   objAddress:  RegisterAddress) {
        this.firstName = firstName;
        this.surname = surname;
        this.supplyName = supplyName;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.objAddress = objAddress;
        this.role = ['user'];
    }




}