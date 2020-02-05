package com.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.model.SmartShopper;
import com.ecommerce.repository.SmartShopperRepository;




@Service
public class SmartShopperDao {
	
	@Autowired
	SmartShopperRepository  objSmartShopperRepository;
	
	public SmartShopper save(SmartShopper objSmartShopper) {
		return objSmartShopperRepository.save(objSmartShopper);
	}

}
