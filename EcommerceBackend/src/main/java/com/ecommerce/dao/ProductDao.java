package com.ecommerce.dao;

import java.util.List;

//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductDao {
	
	@Autowired
	ProductRepository objProductRepository;

	//public void saveImage(MultipartFile imageFile) throws Exception{
	//	String folder ="/Product/";
	//	byte[] bytes = imageFile.getBytes();
		///Path path = Paths.get(folder+ imageFile.getOriginalFilename());
		//Files.write(path,bytes);
	//}

	public Product save(Product product) {
		return objProductRepository.save(product);
	}
	public List<Product> findAll(){
		return objProductRepository.findAll();
	}
	
	public Product findOne(int barcode) {
		return objProductRepository.getOne(barcode);
	}
	public void deleteProduct(Product objprod) {
		objProductRepository.delete(objprod);
	}
	
	
	public List<Product> findAllCatProduct(String objCategory ){
		return objProductRepository.findByCategory(objCategory);
	}
	

}
