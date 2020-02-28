import { ShoppingCart } from '../services/shoppingCart-info';
import { ProductOrder } from '../services/product-order-info';

import { Product } from '../services/full-product-info';
import { DataService } from '../services/data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { UserService } from '../services/user.service';
import { FullProductOrder } from '../services/full-product-order-info';
import { User } from '../services/full-user-info';
import { TokenStorageService } from '../services/token-storage.service';
import { FormBuilder,Validators,FormGroup } from '@angular/forms';



@Component({
  selector: 'app-product-order-detail',
  templateUrl: './product-order-detail.component.html',
  styleUrls: ['./product-order-detail.component.css']
})
export class ProductOrderDetailComponent implements OnInit {
  private objAllOrders : Array<any>;
  form: any = {};
  objProductOrder : ProductOrder;
  errorMessage = '';
  product : Product;
  objFullProductOrder : FullProductOrder;
  objUser : User;
  objOrderPrice: number;
  objUsernanme: string;
  orderForm:FormGroup;
  qtyOrder: number;
  



isLoadingResults = true;  


  constructor(private route: ActivatedRoute, private fb: FormBuilder ,router: Router,private dataService: DataService,private userService: UserService,private objToken :TokenStorageService) { }

  ngOnInit() {

    this.orderForm = this.fb.group({
      username:  ['', []], 
      qtyOrdered:  ['', []]
     }); 

      this.orderForm.controls['username'].setValue(this.objToken.getUsername());
     this.getProductDetails(this.route.snapshot.params['barcode']);
      
     
    }

    getProductDetails(ShoppingCart) {
      this.userService.getOneProduct(ShoppingCart)
        .subscribe(data => {
          this.product = data;

          console.log(this.product);
          this.isLoadingResults = false;
        });   

      
  }



 udateShoppingCart(id) {
          this.userService.updateShoppingCart(id)
            .subscribe(data => {
             console.log(data);
            });
          }


   getAllOrders(id) {
          this.userService.getAllOrders(id)
            .subscribe(data => {
              this.objAllOrders = data;

              console.log(this.objAllOrders);
              this.isLoadingResults = false;
            });
          }

  calculatePrice(){

this.objOrderPrice = Math.floor( this.qtyOrder* this.product.price);
  }



  onSubmit() {

  
    
    this.qtyOrder  =this.orderForm.controls['qtyOrdered'].value;
    this.objUsernanme = this.orderForm.controls['username'].value;
    
    
    this.objProductOrder = new ProductOrder(
      this.qtyOrder,
      this.product
      
    );

    this.calculatePrice();

    this.userService.saveOrder(this.objProductOrder).subscribe((data) =>{
      console.log(data);
this.objFullProductOrder = data;
this.getAllOrders(this.objFullProductOrder.objShoppingCart.cartId);
this.objUser = this.objFullProductOrder.objUser;
this.objOrderPrice =  this.objFullProductOrder.objShoppingCart.totalPrice+ this.objOrderPrice;
this.objFullProductOrder.objShoppingCart.totalPrice= this.objOrderPrice;
this.udateShoppingCart(this.objFullProductOrder.objShoppingCart);

       },
       error => {
        console.log(error);
        this.errorMessage = error.error.message;

      }
    );
  

  }

}
