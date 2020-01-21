
import { Injectable, } from '@angular/core';
import {HttpClient,HttpHeaders } from '@angular/common/http';
import {Observable } from 'rxjs';

import {JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { RegisterAddress } from './registerAddress-info';
import { RegisterUser } from './registerUser-info';


const httpOptions = {
    headers : new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
 providedIn: 'root'
})
export class AuthService{

    
    private loginUrl = 'http://localhost:8080/api/auth/signin';
    private  registerAddressUrl= 'http://localhost:8080/AddressApi/address';
    private registerUserUrl = 'http://localhost:8080/api/auth/signup';

    constructor(private http: HttpClient ){

    }

    attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
        return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
      }
     

    registerAdd(info: RegisterAddress ): Observable<string>{
        return this.http.post<string>(this.registerAddressUrl, info, httpOptions)
   }
    registerCust(info: RegisterUser): Observable<string>{
        return this.http.post<string>(this.registerUserUrl, info, httpOptions)
    }
}