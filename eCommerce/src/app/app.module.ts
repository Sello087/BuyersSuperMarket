
import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';
import { UserService} from './services/user.service';
import { DataService} from './services/data.service';
import { ProductDisplayComponent } from './product-display/product-display.component';
import { HomeComponent } from './home/home.component';
import { ProductOrderDetailComponent } from './product-order-detail/product-order-detail.component';



@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ProductComponent,
    ProductDisplayComponent,
    HomeComponent,
    ProductOrderDetailComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserService, DataService],
  bootstrap: [AppComponent]
}) 
export class AppModule { }



