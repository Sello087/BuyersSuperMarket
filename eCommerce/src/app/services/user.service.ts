
import { Product } from './full-product-info';
import { ProductOrder } from './product-order-info';
import { SmartShopper } from './smartShopper-info';
import { ShoppingCart } from './shoppingCart-info';
import { FullShoppingCart } from './full-shopping-cart-info';
import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterAddress } from './registerAddress-info';
import { catchError, tap, map } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
    providedIn: 'root'
})
export class UserService {
    private saveCartUrl = 'http://localhost:8080/api/ecommerce/addShoppingCart';
    private saveSmartShopperUrl = 'http://localhost:8080/api/ecommerce/addSmartShopper';
    private saveProductUrl = 'http://localhost:8080/api/ecommerce/addProduct';
    private saveAddressUrl = 'http://localhost:8080/api/ecommerce/address';
    private getAllProductsUrl = 'http://localhost:8080/api/ecommerce/getAllProduct';
    private placeOderUrl = 'http://localhost:8080/api/ecommerce/addProductOrder';
    private oneProductUrl = 'http://localhost:8080/api/ecommerce/oneProduct';
    private allOrdersUrl ='http://localhost:8080/api/ecommerce/getAllOrder';
    private updateShoppingCartUrl ='http://localhost:8080/api/ecommerce/updateShoppingCart';
    private getOneUserUrl ='http://localhost:8080/api/ecommerce/getAddress';
    private updateAddressUrl ='http://localhost:8080/api/ecommerce/updateAddress';
  private getProdByCategoryUrl ='http://localhost:8080/api/ecommerce/getAllCatProducts';
  private updateProductUrl ='http://localhost:8080/api/ecommerce/updateProduct';


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
      
      saveOrder(objProdOrer: ProductOrder): Observable<any> {
        return this.http.post(this.placeOderUrl, objProdOrer );
      }

      

      getOneProduct(barcode: number): Observable<any> {
        const url = `${this.oneProductUrl}/${barcode}`;
        return this.http.get<any>(url).pipe(
          tap(_ => console.log(`fetched product id    =${barcode}`)),
                );
      }

      getAllOrders(cartId: number): Observable<any> {
        const url = `${this.allOrdersUrl}/${cartId}`;
        return this.http.get(url).pipe(
          tap(_ => console.log(`fetched cart  =${cartId}`)),
                );
      }



 updateShoppingCart(objShoppingCart : FullShoppingCart): Observable<any> {
        
        return this.http.patch(this.updateShoppingCartUrl,objShoppingCart).pipe(
          tap(_ => console.log(`Updating cart `)),
                );
      }



      getOneUser(userId : number): Observable<any> {
        const url = `${this. getOneUserUrl}/${userId}`;
        return this.http.get<any>(url).pipe(
          tap(_ => console.log(`fetched User with userId:${userId}`)),
                );
      }

      updateProduct(objProduct : Product): Observable<any> {
      
        return this.http.patch(this.updateProductUrl,objProduct).pipe(
          tap(_ => console.log(`Updating product `)),
                );
      }

      
 updateAddress(objAddress : RegisterAddress): Observable<any> {
        
  return this.http.put(this.updateAddressUrl,objAddress).pipe(
    tap(_ => console.log(`Updating address `)),
          );
}


getProdByCategory(category: string){


  if(category=="All"){
    return this.http.get(this.getAllProductsUrl);
  }else{

    const url = `${this.getProdByCategoryUrl}/${category}`;
    return this.http.get<any>(url).pipe(
      tap(_ => console.log(`fetched Products with category :${category}`)),
            );
  

  }
 
}


  }