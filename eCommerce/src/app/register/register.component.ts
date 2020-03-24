import { SmartShopper } from '../services/smartShopper-info';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

import { RegisterUser } from '../services/registerUser-info';
import { RegisterAddress } from '../services/registerAddress-info';
import { ShoppingCart } from '../services/shoppingCart-info';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  info: any;
 
  registeraddress: RegisterAddress;
  registerUser: RegisterUser;
  shoppingCart: ShoppingCart;
  smartShopper: SmartShopper;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  private authority: string;
  private roles: string[];


  constructor(private authService: AuthService,private tokenStorage: TokenStorageService,private userService: UserService) { }

 
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_SUPPLIER') {
          this.authority = 'supplier';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
    this.info = {
      token: this.tokenStorage.getToken(),
      username: this.tokenStorage.getUsername(),
      authorities: this.tokenStorage.getAuthorities()
    };
  }
  onSubmit() {
    console.log(this.form);
 



    
    this.registeraddress = new RegisterAddress(
      this.form.houseNumber,
         this.form.street,
       this.form.city,
       this.form.state,
         this.form.zipCode);

    
      this.userService.saveAddress(this.registeraddress).subscribe((data) =>{
        console.log(data);
           
         },
         error => {
          console.log(error);
          this.errorMessage = error.error.message;
  
        }
      );
     
      

      this.registerUser = new RegisterUser(
        this.form.firstName,
        this.form.surname,
        this.form.supplyName,
        this.form.username,
        this.form.email,
        this.form.contactNumber,
        this.form.psw,
        this.registeraddress);
      
    this.authService.registerCust(this.registerUser).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },

      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );




    if(this.authority="user"){

      this.shoppingCart = new ShoppingCart(0,"none","none","none");
      this.userService.saveShoppingCart(this.shoppingCart).subscribe((data) =>{
        console.log(data);
           
         },
         error => {
          console.log(error);
          this.errorMessage = error.error.message;
  
        }
      );


      this.smartShopper = new SmartShopper();


      this.userService.saveSmartShopper(this.smartShopper).subscribe((data) =>{
        console.log(data);
           
         },
         error => {
          console.log(error);
          this.errorMessage = error.error.message;
  
        }
      );
    }

  

  }
}