export class Product {

  prodName : string;
  category : string;
  size : number;
  qtyInStock : number;  
  price : number;

  constructor(prodName : string,category : string,size : number,qtyInStock : number, price : number){
      this.prodName = prodName;
      this.category= category;
      this.size = size;
      this.qtyInStock = qtyInStock;
      this.price = price;
  }
  

}