import { UserService } from './../services/user.service';
import { Product } from './../services/product-info';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '../../../node_modules/@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  public userFile : any = File;
  product : Product;
  form: any = {};
  errorMessage = '';

  constructor(private userService :UserService) { }

  onSelectFile(event){
    const file = event.target.files[0];
    this.userFile = file;
  }

  
  ngOnInit() {
  }


  onSubmit(){

    console.log(this.form);
    this.product = new Product(
      this.form.prodName,
      this.form.category,
      this.form.size,
      this.form.qtyInStock,
      this.form.price
    );
  //const product = submitForm.value;

    const formData = new FormData();
    formData.append('product',JSON.stringify(this.product));
     formData.append('file ', this.userFile);
     this.userService.saveProduct(formData).subscribe((data) =>{
      console.log(data);
         
       },
       error => {
        console.log(error);
        this.errorMessage = error.error.message;

      }
    );


  }
}
