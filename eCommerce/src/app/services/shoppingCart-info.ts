export class ShoppingCart{
    totalPrice: number;
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