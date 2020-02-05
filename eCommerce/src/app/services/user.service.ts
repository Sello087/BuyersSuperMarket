import { SmartShopper } from './smartShopper-info';
import { ShoppingCart } from './shoppingCart-info';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterAddress } from './registerAddress-info';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private saveCartUrl = 'http://localhost:8080/api/ecommerce/addShoppingCart';
    private saveSmartShopperUrl = 'http://localhost:8080/api/ecommerce/addSmartShopper';
    private saveProductUrl = 'http://localhost:8080/api/ecommerce/addProduct';
    private saveAddressUrl = 'http://localhost:8080/api/ecommerce/address';
    private getAllProductsUrl = 'http://localhost:8080/api/ecommerce/getAllProduct';
    constructor(private http: HttpClient) { }
   
    saveShoppingCart(objShoppingCart: ShoppingCart): Observable<any> {
      return this.http.post(this.saveCartUrl, objShoppingCart);
    }
   
    saveSmartShopper(objSmartShopper: SmartShopper): Observable<any> {
      return this.http.post(this.saveSmartShopperUrl, objSmartShopper);
    }
   
    saveAddress(objAddress: RegisterAddress): Observable<any> {
      return this.http.post(this.saveAddressUrl, objAddress );
    }

    saveProduct(objFormdata: FormData): Observable<any> {
        return this.http.post(this.saveProductUrl, objFormdata );
      }

      getAllProducts(): Observable<any> {
        return this.http.get(this.getAllProductsUrl);
      }


      

  }