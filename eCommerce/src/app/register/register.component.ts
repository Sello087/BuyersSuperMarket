import { TokenStorageService } from './../services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

import { RegisterUser } from '../services/registerUser-info';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  info: any;
 
  registerUser: RegisterUser;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  private authority: string;
  private roles: string[];


  constructor(private authService: AuthService,private tokenStorage: TokenStorageService) { }

 
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
 



    this.registerUser = new RegisterUser(
      this.form.firstName,
      this.form.surname,
      this.form.supplyName,
      this.form.username,
      this.form.email,
      this.form.contactNumber,
      this.form.psw);
 
 
      
      
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
  }
}