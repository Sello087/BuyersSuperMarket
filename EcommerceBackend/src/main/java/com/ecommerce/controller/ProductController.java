package com.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dao.AddressDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.ProductOrderDao;
import com.ecommerce.dao.ShoppingCartDao;
import com.ecommerce.dao.UserDetailsDao;
import com.ecommerce.model.Address;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.User;
import com.ecommerce.model.reponse.ResponseMessage;
import com.ecommerce.repository.ProductRepository;
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
	ProductRepository objProductRepository;
	@Autowired
	ProductDao objProductDao;
	@Autowired
	ProductOrderDao objProductOrderDao;
	@Autowired
	AddressDao objAddressDao;
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
		 List<ProductOrder> objPodOrder =objProductOrderDao.findAll(objShoppingCart);
		 List<ProductOrder> objTempPodOrder = new ArrayList<>(); ;
		 for(int i=0 ;i<objPodOrder .size();i++) {
			 
			 
			 objTempPodOrder.add(objPodOrder.get(i));
		 }
		 User objUser = objPodOrder.get(0).getObjUser();
		 
		 
 		 
		 return objTempPodOrder ;
	 }
	 
	 
	 
	 @GetMapping("/oneProduct/{barcode}")
	 public ResponseEntity<Product> getProdById(@PathVariable(value="barcode") int barcode){
		 Product objProduct = objProductDao.findOne(barcode);
		 if(objProduct==null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok().body(objProduct);
	 } 
	 
	 @PatchMapping("/updateProduct")
	 public ResponseEntity<Product> updateProd( @Valid @RequestBody Product objProduct){

		 Product product = objProductDao.findOne(objProduct.getBarcode());
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
	 
	 
	 @GetMapping("/getAllCatProducts/{category}")
	 public  List<Product> getAllCatProducts(@PathVariable(value="category") String category){
		 //return objProductDao.findAllCatProduct(category);
		 return objProductRepository.getProductByCategory(category);
	 }
	 
	 
	 @PatchMapping("/updateShoppingCart")
	 public ResponseEntity<ShoppingCart> updateShoppingCart( @Valid @RequestBody ShoppingCart objShoppingCart){

		 ShoppingCart shoppingCart = objShoppingCartDao.findOne(objShoppingCart.getCartId());
		 if (shoppingCart==null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		shoppingCart.setDeliveryMethod(objShoppingCart.getDeliveryMethod());
		shoppingCart.setDeliveryStatus(objShoppingCart.getDeliveryStatus());
		shoppingCart.setDeliveryMethod( objShoppingCart.getPaymentStatus());
		shoppingCart.setTotalPrice(objShoppingCart.getTotalPrice());
		 
		  ShoppingCart updateShoppingCart =objShoppingCartDao.save(shoppingCart);	 
		 return ResponseEntity.ok().body(updateShoppingCart); 
	 }
	 
	
	 @PutMapping("/updateAddress")
	 public ResponseEntity<Address> updateAddress( @Valid @RequestBody Address objAddress){
		 Address address = objAddressDao.findOne(objAddress.getAddId());

		 if (address==null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		address.setHouseNumber(objAddress.getHouseNumber());
		address.setStreet(objAddress.getStreet());
		address.setCity(objAddress.getCity());
		address.setState(objAddress.getState());
		address.setZipCode(objAddress.getZipCode());
		 
		  Address updatedAddress =objAddressDao.save(address);	 
		 return ResponseEntity.ok().body(updatedAddress);
	 }
	 

	 @PatchMapping("/updateProductOrder")
	 public ResponseEntity<ProductOrder> updateProdOrder( @Valid @RequestBody ProductOrder objProductOrder){

		 ProductOrder productOrder = objProductOrderDao.findOne(objProductOrder.getOrderId());
		 if (productOrder==null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 productOrder.setQtyOrdered(objProductOrder.getQtyOrdered());
		 ProductOrder updateProductOrder =objProductOrderDao.save(productOrder);	 
		 return ResponseEntity.ok().body(updateProductOrder);
	 }
	 
	 @GetMapping("/getProdOrderByProducts/{orderId}")
	 public  List<ProductOrder> getByProduct(@PathVariable(value="orderId") int orderId){
		 return objProductOrderDao.groupByProduct(orderId);
	 }
	 
	 
}
