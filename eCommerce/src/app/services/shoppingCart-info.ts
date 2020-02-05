export class ShoppingCart{
    totalPrice: Number;
    deliveryMetthod: string;
    paymentStatus: string;
    deliveryStatus: string;

    constructor( ){

        this.totalPrice = 5;
        this.deliveryMetthod = "none";
        this.paymentStatus = "none";
        this.deliveryStatus = "none";
        
    }
}