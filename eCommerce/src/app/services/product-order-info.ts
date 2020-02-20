import { Product } from './full-product-info';

export class ProductOrder{

    qtyOrdered : number;
   objProduct: Product;

    constructor(qtyOrdered : number,objProduct: Product){
        
        this.qtyOrdered = qtyOrdered;
        this.objProduct = objProduct;

    }


}