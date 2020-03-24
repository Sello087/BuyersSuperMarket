package com.ecommerce.dao;


//import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.repository.ProductOrderRepository;
@Service
public class ProductOrderDao {
	
	@Autowired
	ProductOrderRepository objProductOrderRepository;
	
	public ProductOrder save(ProductOrder productOrder) {
		return objProductOrderRepository.save(productOrder);
	}
	public List<ProductOrder> findAll(ShoppingCart objShoppingCart){
		//List<ProductOrder> prodOrder= new ArrayList<>();
		
		 //.forEach(prodOrder::add);
		return objProductOrderRepository.findByobjShoppingCart(objShoppingCart);
				///prodOrder;
	}
	
	//public List<ProductOrder> findAllOrder(int cartId){
	//	return objProductOrderRepository.findOrder_By_Cart(cartId);
	//}
	
	public List<ProductOrder> groupByProduct(int orderId){
		return objProductOrderRepository.getProductOrderByOrderId(orderId);
	}
	
	public ProductOrder findOne(int orderId) {
		return objProductOrderRepository.getOne(orderId);
	}
	

}
