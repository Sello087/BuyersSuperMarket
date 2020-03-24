
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { ProductOrder } from '../services/product-order-info';
import { FullProductOrder } from '../services/full-product-order-info';
import { FormBuilder ,FormGroup } from '@angular/forms';

@Component({
  selector: 'app-product- display',
  templateUrl: './product-display.component.html',
  styleUrls: ['./product-display.component.css']
})
export class ProductDisplayComponent implements OnInit {
  private form : any;
  private objProducts : Array<any>;
  private sub : any;
  private objProduct : any;
  objProductOrder : ProductOrder;
  objOrderPrice: number;
  objQtyOrdered: number;
  objFullProductOrder : FullProductOrder;
  errorMessage = '';
  qty:number;
  orderForm:FormGroup;
  dataSaved: boolean = false;
  
  
  private objTempProduct : Array<any>;

  constructor(private fb: FormBuilder,private userService: UserService,private route: ActivatedRoute,private objUserService : UserService,private dataService: DataService) { }

  ngOnInit() {

    this.orderForm = this.fb.group({
      qtyOrdered:  [0, []]
    }); 

   
   // window.location.reload();
    let category = this.route.snapshot.params['category'];
   this.sub = this.objUserService.getProdByCategory(category).subscribe(data => {
      this.objProducts = data;
    });

  }

  setProduct(product){
    this.objProduct = product;
    this.resetForm();
    
  }

  calculatePrice(){

    this.objOrderPrice = Math.floor( this.qty* this.objProduct.price);
      }

      udateShoppingCart(id) {
        this.userService.updateShoppingCart(id)
          .subscribe(data => {
           console.log(data);
          });
        }

        updateProduct(product) {
          this.userService.updateProduct(product)
            .subscribe(data => {
             console.log(data);
            });
          }  



      saveOrder(){

        this.qty  =this.orderForm.controls['qtyOrdered'].value;
        this.objProductOrder = new ProductOrder(
          this.qty,
          this.objProduct
          
        );
    
        this.calculatePrice();
    


    
        this.userService.saveOrder(this.objProductOrder).subscribe((data) =>{
          console.log(data);
    this.objFullProductOrder = data;
    this.objOrderPrice =  this.objFullProductOrder.objShoppingCart.totalPrice+ this.objOrderPrice;
    this.objFullProductOrder.objShoppingCart.totalPrice= this.objOrderPrice;
    this.objProduct.qtyInStock =this.objProduct.qtyInStock-this.qty; 
    this.udateShoppingCart(this.objFullProductOrder.objShoppingCart);
    this.updateProduct(this.objProduct);
    

    
           },
           error => {
            console.log(error);
            this.errorMessage = error.error.message;
    
          }
        );
    
    
      }

  onSubmit()
  {
  
  }

  resetForm()
  {
  this.orderForm.reset();
  this.dataSaved = true;
  }


}
