import { RegisterUser } from './registerUser-info';
import { Product } from './full-product-info';
import { FullShoppingCart } from './full-shopping-cart-info';

export class FullProductOrder{

    orderId : number;
    orderDate : Date;
    qtyOrdered : number;
    objShoppingCart : FullShoppingCart;
    objUser : RegisterUser;
   objProduct: Product; 


    constructor(orderId : number, orderDate : Date,qtyOrdered : number,objShoppingCart : FullShoppingCart,objUser : RegisterUser,objProduct: Product){
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.qtyOrdered = qtyOrdered;
        this.objShoppingCart = objShoppingCart;
        this.objUser = objUser; 
        this.objProduct = objProduct;

    }


}