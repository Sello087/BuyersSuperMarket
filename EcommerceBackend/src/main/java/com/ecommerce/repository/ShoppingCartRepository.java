package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.ShoppingCart;

public  interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Integer>{

}
