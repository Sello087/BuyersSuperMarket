import { ProductDisplayComponent } from './product-display/product-display.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';
import { HomeComponent } from './home/home.component';
import { ProductOrderDetailComponent } from './product-order-detail/product-order-detail.component';

const routes: Routes = [
  {path:'register', component: RegisterComponent, pathMatch:'full'}
  ,
  { path: 'login', component: LoginComponent},
  { path: 'product', component: ProductComponent},
  { path: 'product-display/:category', component: ProductDisplayComponent },
  { path: 'webHome', component: HomeComponent },
  { path: 'product-order-detail/:barcode', component: ProductOrderDetailComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
