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
import { RegisterAddress } from '../services/registerAddress-info';



@Component({
  selector: 'app-product-order-detail',
  templateUrl: './product-order-detail.component.html',
  styleUrls: ['./product-order-detail.component.css']
})
export class ProductOrderDetailComponent implements OnInit {
  private objAllOrders : Array<any>;
  
  objProductOrder : ProductOrder;
  errorMessage = '';
  product : Product;
  objFullProductOrder : FullProductOrder;
  objUser : User;
  objTempUser : User;
  objOrderPrice: number;
  objUsernanme: string;
  orderForm:FormGroup;
  modalForm:FormGroup;
  qtyOrder: number;
  authority : string;
  private roles: string[];
  objAddress :  RegisterAddress;
  street : string;
  

  



isLoadingResults = true;  


  constructor(private route: ActivatedRoute, private fb: FormBuilder ,router: Router,private dataService: DataService,private userService: UserService,private objToken :TokenStorageService) { }

  ngOnInit() {

    this.modalForm = this.fb.group({
     
     });

    this.orderForm = this.fb.group({
      username:  ['', []], 
      qtyOrdered:  [0, []],
      houseNumber:  ['', []], 
      street:  ['', []],
      city:  ['', []],
      state:  ['', []],
      zipCode:  ['', []]
     }); 

      this.orderForm.controls['username'].setValue(this.objToken.getUsername());
     this.getProductDetails(this.route.snapshot.params['barcode']);
 
     

    
    if (this.objToken.getToken()) {
      this.roles = this.objToken.getAuthorities();
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


      
     
    }

    getProductDetails(ShoppingCart) {
      this.userService.getOneProduct(ShoppingCart)
        .subscribe(data => {
          this.product = data;

          console.log(this.product);
          this.isLoadingResults = false;
        });   

      
  }


  updateAddress(id) {

    this.objAddress.houseNumber  =this.orderForm.controls['houseNumber'].value;
   this.objAddress.street  =this.orderForm.controls['street'].value;
   this.objAddress.city  =this.orderForm.controls['city'].value;
    this.objAddress.state  =this.orderForm.controls['state'].value;
  this.objAddress.zipCode  =this.orderForm.controls['zipCode'].value;

    this.userService.updateAddress(id)
      .subscribe(data => {
       console.log(data);
      });
    }

 udateShoppingCart(id) {
          this.userService.updateShoppingCart(id)
            .subscribe(data => {
             console.log(data);
            });
          }


          findAdrress(id) {
            this.userService.getOneUser(id)
              .subscribe(data => {
                this.objAddress = data;

                
                this.orderForm.controls['street'].setValue(this.objAddress.street);      
  
                this.orderForm.controls['houseNumber'].setValue(this.objAddress.houseNumber);
    
    this.orderForm.controls['city'].setValue(this.objAddress.city);
    this.orderForm.controls['state'].setValue(this.objAddress.state);
    this.orderForm.controls['zipCode'].setValue(this.objAddress.zipCode);
                console.log(this.objAddress);
                
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



  initializeAll(){

    this.objProductOrder =null;
    this.errorMessage = '';
    this.product =null;
    this.objFullProductOrder =null;
  
  
    this.orderForm= null;
    this.qtyOrder= 0;
  
  
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
this.findAdrress(this.objUser.userId);




       },
       error => {
        console.log(error);
        this.errorMessage = error.error.message;

      }
    );


  }

}
