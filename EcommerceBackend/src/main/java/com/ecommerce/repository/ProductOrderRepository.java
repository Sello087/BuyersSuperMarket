package com.ecommerce.repository;

import java.util.List;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{
	
	//@Query("select pO from Product_Order pO.ProductOrder.cartId = ?1 ")
	//List<ProductOrder> findOrder_By_Cart(int cart_Id) ;
	
	
	

List<ProductOrder> findByobjShoppingCart(ShoppingCart objShoppingCart);
	
	
}
