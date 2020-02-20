export class FullShoppingCart{
    cartId: number;
    totalPrice: Number;
    deliveryMethod: string;
    paymentStatus: string;
    deliveryStatus: string;

    constructor(cartId: number,totalPrice: Number,deliveryMethod: string,paymentStatus: string,deliveryStatus: string ){

        this.cartId = cartId
        this.totalPrice = totalPrice;
        this.deliveryMethod = deliveryMethod;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        
    }
}