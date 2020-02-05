package com.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.ProductOrder;
import com.ecommerce.repository.ProductOrderRepository;
@Service
public class ProductOrderDao {
	
	@Autowired
	ProductOrderRepository objProductOrderRepository;
	
	public ProductOrder save(ProductOrder productOrder) {
		return objProductOrderRepository.save(productOrder);
	}
	

}
