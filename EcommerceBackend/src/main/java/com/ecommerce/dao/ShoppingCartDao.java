package com.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.model.ShoppingCart;
import com.ecommerce.repository.ShoppingCartRepository;

@Service
public class ShoppingCartDao {

	
	@Autowired
	ShoppingCartRepository objShoppingCartRepository;
	public ShoppingCart save(ShoppingCart objShoppingCart) {
		return objShoppingCartRepository.save(objShoppingCart);
	}
	public ShoppingCart findOne(int cartId) {
		return objShoppingCartRepository.getOne(cartId);
		
	}
	
	
}
