export class Product {

    barcode: number;
    prodName : string;
    category : string;
    size : number;
    qtyInStock : number;  
    price : number;
  
    constructor(barcode: number, prodName : string,category : string,size : number,qtyInStock : number, price : number){
        this.barcode = barcode;
        this.prodName = prodName;
        this.category= category;
        this.size = size;
        this.qtyInStock = qtyInStock;
        this.price = price;
    }
    
  
  }