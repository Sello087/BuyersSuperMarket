package com.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Address;

import com.ecommerce.repository.AddressRepository;





@Service
public class AddressDao {
	
	@Autowired
	AddressRepository objAddressRepository;
	
	public Address save(Address add) {
		return objAddressRepository.save(add);
	}
	
	public Address findOne(int addId) {
		return objAddressRepository.getOne(addId);		
	}
}
