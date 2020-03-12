
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { ProductOrder } from '../services/product-order-info';
import { FullProductOrder } from '../services/full-product-order-info';

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
  qtyOrdered: number;
  objFullProductOrder : FullProductOrder;
  errorMessage = '';
  
  private objTempProduct : Array<any>;

  constructor(private userService: UserService,private route: ActivatedRoute,private objUserService : UserService,private dataService: DataService) { }

  ngOnInit() {
    this.qtyOrdered =0;
   // window.location.reload();
    let category = this.route.snapshot.params['category'];
   this.sub = this.objUserService.getProdByCategory(category).subscribe(data => {
      this.objProducts = data;
    });

  }

  setProduct(product){
    this.objProduct = product;
  }

  calculatePrice(){

    this.objOrderPrice = Math.floor( this.qtyOrdered* this.objProduct.price);
      }

  onSubmit() {
     
    this.objProductOrder = new ProductOrder(
      this.form.qtyOrdered,
      this.objProduct
      
    );

    this.calculatePrice();


    this.userService.saveOrder(this.objProductOrder).subscribe((data) =>{
      console.log(data);
this.objFullProductOrder = data;





       },
       error => {
        console.log(error);
        this.errorMessage = error.error.message;

      }
    );
    



    
  }


}
