package com.ecommerce.dao;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
		List<ProductOrder> prodOrder= new ArrayList<>();
		objProductOrderRepository.findByobjShoppingCart(objShoppingCart)
		//objProductOrderRepository.findBy(objShoppingCart)
		.forEach(prodOrder::add);
		return prodOrder;
	}
	
	//public List<ProductOrder> findAllOrder(int cartId){
	//	return objProductOrderRepository.findOrder_By_Cart(cartId);
	//}
	
	
	

}
