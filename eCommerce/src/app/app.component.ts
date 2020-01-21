import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { Component , OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit {
  info: any;
  private roles: string[];
  private authority: string;
  private LoginComponent 
  private isLoggedIn: Boolean;
 
  constructor(private tokenStorage: TokenStorageService) { }
 
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          this.isLoggedIn = true;
          return false;
        } else if (role === 'ROLE_SUPPLIER') {
          this.authority = 'supplier';
          this.isLoggedIn = true;
          return false;
          
        }
        this.authority = 'user';
        this.isLoggedIn = true;
        return true;
        
      });
    }
    this.info = {
      token: this.tokenStorage.getToken(),
      username: this.tokenStorage.getUsername(),
      authorities: this.tokenStorage.getAuthorities()
    };
  }
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
    this.isLoggedIn = false;
  }
}
