package com.ecommerce.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.ProductOrderDao;
import com.ecommerce.dao.ShoppingCartDao;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.reponse.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

	
	@Autowired
ShoppingCartDao objShoppingCartDao;
	@Autowired
	ProductDao objProductDao;
	@Autowired
	ProductOrderDao objProductOrderDao;
	private Product objProduct;
	
	
	 @PostMapping("/addProduct")
	  public ResponseEntity<ResponseMessage> saveProduct(@RequestParam("file") MultipartFile file,@RequestParam("product") String product) throws JsonMappingException, JsonProcessingException,IOException{
		
		  objProduct= new ObjectMapper().readValue(product,Product.class);
		  objProduct.setLogo(file.getBytes());
		  objProduct.setFileName(file.getOriginalFilename());
		  objProduct.setUser(AuthRestAPIs.supplier);
		  Product dbProduct = objProductDao.save(objProduct);
		  if(dbProduct !=null) {
			  return new ResponseEntity<ResponseMessage> (new ResponseMessage("Product Saved Successfully"), HttpStatus.OK);
		  }else {
			  return new ResponseEntity<ResponseMessage> (new ResponseMessage("Product is not saved"), HttpStatus.BAD_REQUEST);  
		  }
		  
		  
	  }
	
	 @RequestMapping("/getAllProduct")
	 public List<Product> getAllProduct(){
		 return objProductDao.findAll();
	 }
	 
	 
	 @GetMapping("/getAllOrder/{cartId}")
	 public  List<ProductOrder> getAllOrders(@PathVariable(value="cartId") int cartId){
		 ShoppingCart objShoppingCart = objShoppingCartDao.findOne(cartId);
		 return objProductOrderDao.findAll(objShoppingCart);
	 }
	 
	 
	 
	 @GetMapping("/oneProduct/{barcode}")
	 public ResponseEntity<Product> getProdById(@PathVariable(value="barcode") int barcode){
		 Product objProduct = objProductDao.findOne(barcode);
		 if(objProduct==null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok().body(objProduct);
	 } 
	 
	 @PutMapping("/Product/{id}")
	 public ResponseEntity<Product> updateProd(@PathVariable(value="id") int barcode, @Valid @RequestBody Product objProduct){

		 Product product = objProductDao.findOne(barcode);
		 if (product==null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 product.setProdName(objProduct.getProdName());
		 product.setCategory(objProduct.getCategory());
		 product.setSize(objProduct.getSize());
		 product.setQtyInStock(objProduct.getQtyInStock());
		 product.setPrice(objProduct.getPrice());
		 
		 Product updateProduct =objProductDao.save(product);	 
		 return ResponseEntity.ok().body(updateProduct);
	 }
	
	@DeleteMapping("/Product/{id}")
	public ResponseEntity<Product> deleteProd(@PathVariable(value="id") int barcode){
		Product objProduct = objProductDao.findOne(barcode);
		if(objProduct==null) {
			return ResponseEntity.notFound().build();
		}
		objProductDao.deleteProduct(objProduct);
		return ResponseEntity.ok().build();
 	}
	 
	
	 @PostMapping("/addProductOrder")
		public ProductOrder createProductOrder(@Valid @RequestBody ProductOrder objProdOrder) {
		 TimeZone.setDefault(TimeZone.getTimeZone("UTC")); 
		 ProductOrder objProductOrder = new ProductOrder(objProdOrder.getQtyOrdered(),objProdOrder.getObjProduct());
		 objProductOrder.setObjProduct(objProdOrder.getObjProduct());
		objProductOrder.setObjShoppingCart(AuthRestAPIs.objCart);
		objProductOrder.setOrderDate(new Date());
		objProductOrder.setObjUser(AuthRestAPIs.customer);
		 return objProductOrderDao.save(objProductOrder);
		}	
	 
	 
}
