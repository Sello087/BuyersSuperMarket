package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategory(String category);
	
	@Query("select product from Product product where product.category = :category ")
	List<Product> getProductByCategory(@Param("category")String category) ;

}