package com.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dao.AddressDao;
import com.ecommerce.model.Address;





	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/AddressApi")
public class AddressController {



	@Autowired
	AddressDao objAddressDAO;
	
	@PostMapping("/address")
	public Address createAddress(@Valid @RequestBody Address add) {
		return objAddressDAO.save(add);
	}
	

}
  