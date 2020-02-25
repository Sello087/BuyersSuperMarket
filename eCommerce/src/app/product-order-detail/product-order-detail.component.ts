import { ShoppingCart } from './../services/shoppingCart-info';
import { ProductOrder } from './../services/product-order-info';

import { Product } from './../services/full-product-info';
import { DataService } from './../services/data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { UserService } from './../services/user.service';
import { FullProductOrder } from './../services/full-product-order-info';

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
  objUser : any;
  
  



isLoadingResults = true;  


  constructor(private route: ActivatedRoute, private router: Router,private dataService: DataService,private userService: UserService) { }

  ngOnInit() {
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

   getAllOrders(id) {
          this.userService.getAllOrders(id)
            .subscribe(data => {
              this.objAllOrders = data;

              console.log(this.objAllOrders);
              this.isLoadingResults = false;
            });
          }



  onSubmit() {
    this.objProductOrder = new ProductOrder(
      this.form.qtyOrdered,
      this.product
      
    );

    this.userService.saveOrder(this.objProductOrder).subscribe((data) =>{
      console.log(data);
this.objFullProductOrder = data;
this.getAllOrders(this.objFullProductOrder.objShoppingCart.cartId);
this.objUser = this.objFullProductOrder.objUser;
       },
       error => {
        console.log(error);
        this.errorMessage = error.error.message;

      }
    );
  

  }

}
