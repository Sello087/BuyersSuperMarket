package com.ecommerce.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "User",uniqueConstraints = {
			@UniqueConstraint(columnNames = {
	            "username"
	        }),
            @UniqueConstraint(columnNames = {
                "email"
            })
    })
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@Column(name ="firstName")
	private String firstName;
	
	@Column(name ="surname")
	private String surname;
	
	@Column(name ="supplyName")
	private String supplyName;
	
	
	@Size(min=3, max = 50)
	private String username;
	
	@NaturalId
    @NotBlank
    @Size(max = 50)
	@Column(name ="email")
	private String email;
	
	@Column(name ="contactNumber")
	private String contactNumber;
	
	@Column(name ="password")
	private String password;

	@ManyToOne
	@JoinColumn(name="addId")
	private Address address;
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles", 
	      joinColumns = @JoinColumn(name = "user_id"), 
	      inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	
	 @OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	  
	 private List<Product> prodList = new ArrayList<>();
	 
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "objUser")
		private List<SmartShopper> smartShopper = new ArrayList<>();
	 
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "objUser")
		private List<ShoppingCart> shoppingCart = new ArrayList<>();
	 
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "objUser")
		private List<ProductOrder> productOrder= new ArrayList<>();

	 
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "user")
		private List<SupplyOrder> supplyOrder= new ArrayList<>();

	 
	public User() {
		super();
		
	}
	

	public User(int userId, String firstName, String surname, String supplyName,
			 @Size(min = 3, max = 50) String username,  @Size(max = 50) String email,
			 String contactNumber,  String password, Address objAddress, Set<Role> roles,List<Product> prodList,List<SmartShopper> smartShopper, List<ProductOrder> productOrder,List<ShoppingCart> shoppingCart) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surname = surname;
		this.supplyName = supplyName;
		this.username = username;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.address = objAddress;
		this.roles = roles;
		this.prodList = prodList;
		this.productOrder = productOrder;
		this.smartShopper = smartShopper;
		this.productOrder = productOrder;
		this.shoppingCart = shoppingCart;
		
	}


	public User(String firstName,String surname,String supplyName, String username, String email, String contactNumber,String password,Address address) {
        this.supplyName= supplyName;
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.address = address;
    }


	public int getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSurname() {
		return surname;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public Address getAddress() {
		return address;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	
	public List<Product> getProdList() {
		return prodList;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAddress(Address objAddress) {
		this.address = objAddress;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}


	public List<SmartShopper> getSmartShopper() {
		return smartShopper;
	}


	public void setSmartShopper(List<SmartShopper> smartShopper) {
		this.smartShopper = smartShopper;
	}


	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}


	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}


	public List<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(List<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	public List<SupplyOrder> getSupplyOrder() {
		return supplyOrder;
	}


	public void setSupplyOrder(List<SupplyOrder> supplyOrder) {
		this.supplyOrder = supplyOrder;
	}

	
	

	
	
	 
	 
	 
}
