import { DataService } from './../services/data.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-product-order-detail',
  templateUrl: './product-order-detail.component.html',
  styleUrls: ['./product-order-detail.component.css']
})
export class ProductOrderDetailComponent implements OnInit {
  private barcode :number;

  constructor(private dataService: DataService) { }

  ngOnInit() {

    this.barcode = this.dataService.barcode; 

    


  }

}
