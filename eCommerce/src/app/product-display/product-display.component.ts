
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-product- display',
  templateUrl: './product-display.component.html',
  styleUrls: ['./product-display.component.css']
})
export class ProductDisplayComponent implements OnInit {
  private objProducts : Array<any>;
  private category: string;
  private objTempProduct : Array<any>;

  constructor(private objUserService : UserService,private dataService: DataService) { }

  ngOnInit() {
    this.category = this.dataService.category; 
    this.objUserService.getAllProducts().subscribe(data => {
      
      this.objTempProduct = data;
      for (let i = 0; i < this.objTempProduct.length; i++) {
        if(this.category===this.objTempProduct[i].category){
          this.objProducts[i].push(this.objTempProduct[i]);
        }
        
      }
    });

  }

  




}
