export class ShoppingCart{
    totalPrice: number;
    deliveryMetthod: string;
    paymentStatus: string;
    deliveryStatus: string;

    constructor( totalPrice: number,deliveryMetthod: string,paymentStatus: string,deliveryStatus: string
    ){

        this.totalPrice = totalPrice;
        this.deliveryMetthod = deliveryMetthod;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        
    }
}