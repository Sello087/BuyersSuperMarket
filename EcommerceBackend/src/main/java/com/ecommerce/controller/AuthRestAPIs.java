package com.ecommerce.controller;




import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecommerce.model.Role;
import com.ecommerce.model.RoleName;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.SmartShopper;
import com.ecommerce.model.User;
import com.ecommerce.dao.AddressDao;	
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.ShoppingCartDao;
import com.ecommerce.dao.SmartShopperDao;
import com.ecommerce.dao.UserDetailsDao;
import com.ecommerce.model.Address;
import com.ecommerce.model.Product;
import com.ecommerce.model.reponse.JwtResponse;
import com.ecommerce.model.reponse.ResponseMessage;
import com.ecommerce.model.request.LoginForm;
import com.ecommerce.model.request.SignUpForm;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.jwt.JwtProvider;

 

 
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/ecommerce")

public class AuthRestAPIs {
 
  @Autowired
  UserDetailsDao objUserDetailDao;
  
  @Autowired
  AuthenticationManager authenticationManager;
 
  @Autowired
  UserRepository userRepository;
 
  @Autowired
  RoleRepository roleRepository;
 
  @Autowired
  PasswordEncoder encoder;
 
  @Autowired
  JwtProvider jwtProvider;
   
  @Autowired
	AddressDao objAddressDAO;
  
  @Autowired
  SmartShopperDao objSmartShopperDao;
  
  @Autowired
  ShoppingCartDao objShoppingCartDao;
  
  @Autowired
  ProductDao objProductDao;
  
  @Autowired
  ServletContext context;
  
   Address objAddress ;
   
    static User supplier;
    static User customer;
    static ShoppingCart objCart;
  
 
  
  
 // @PostMapping("/addProductServer")
 // public ResponseEntity<ResponseMessage> saveProductServer(@RequestParam("file") MultipartFile file,@RequestParam("product") String product) throws JsonMappingException, JsonProcessingException,IOException{
	
	//  Product objProduct= new ObjectMapper().readValue(product,Product.class);
	  
	//  boolean isExist = new File(context.getRealPath("/products/")).exists();
	//  if(!isExist) {
	//	  new File(context.getRealPath("/products/")).mkdir();
	//  }
	//  String filename = file.getOriginalFilename();
	//  String modifiedFileName = FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
	//  File serverfile = new File (context.getRealPath("/product/"+File.separator+modifiedFileName));
	//  try {
	//	  FileUtils.writeByteArrayToFile(serverfile, file.getBytes());
	//  }catch(Exception ex) {
	//	  ex.printStackTrace();
	//  }
	//  objProduct.setFileName(modifiedFileName);
	 // Product dbProduct = objProductDao.save(objProduct);
//	 if(dbProduct !=null) {
	//	  return new ResponseEntity<ResponseMessage> (new ResponseMessage("Product Saved Successfully"), HttpStatus.OK);
	 // }else {
	//	  return new ResponseEntity<ResponseMessage> (new ResponseMessage("Product is not saved"), HttpStatus.BAD_REQUEST);  
	 // }
	  
	   
 // }
  
  
  
 // @PostMapping("/uploadImage")
//  public String upLoadImage(@RequestParam("imageFile") MultipartFile imageFile) {
	//  String returnValue = "";
	//  try {
	//	objProductDao.saveImage(imageFile);
	//} catch (Exception e) {
	//	// TODO Auto-generated catch block
//		e.printStackTrace();
	//}
//	  return returnValue;
 // }
  
  @PostMapping("/address")
	public Address createAddress(@Valid @RequestBody Address add) {
		objAddress =objAddressDAO.save(add);
	  return objAddress;
	}
  
  @PostMapping("/addSmartShopper")
	public SmartShopper createSmartShopper(@Valid @RequestBody  SmartShopper objSmartShopper) {
		return objSmartShopperDao.save(objSmartShopper);
	}

  @PostMapping("/addShoppingCart")
 	public ShoppingCart createShoppingCart(@Valid @RequestBody  ShoppingCart objShoppingCart) {
 		objCart = objShoppingCart;
	  return objShoppingCartDao.save(objShoppingCart);
 		
 	}
  
 
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 
    SecurityContextHolder.getContext().setAuthentication(authentication);
 
    String jwt = jwtProvider.generateJwtToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
  }
 
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }
 
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
          HttpStatus.BAD_REQUEST);
    }
  
    // Creating user's account
    
   
    User user = new User(signUpRequest.getFirstName(), signUpRequest.getSurname(),signUpRequest.getSupplyName(),signUpRequest.getUsername(), signUpRequest.getEmail(),
    		signUpRequest.getContactNumber(), encoder.encode(signUpRequest.getPassword()),signUpRequest.getAddress());
 
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
 
    strRoles.forEach(role -> {
      switch (role) {
      case "admin":
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(adminRole);
        
        break;
      case "supplier":
        Role supplyRole = roleRepository.findByName(RoleName.ROLE_SUPPLIER)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(supplyRole);
        supplier = user;
        break;
      default:
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        customer	 = user;
      }
    });
 
    user.setRoles(roles);
    user.setAddress(objAddress);
    userRepository.save(user);
    
    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
  }
  
  @GetMapping("/oneUser/{username}")
  public Optional<User> findOneUser(@PathVariable(value="username") String username){
	  return userRepository.findByUsername(username);
  }
  
  
}