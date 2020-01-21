package com.ecommerce.model.request;


import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.*;

import com.ecommerce.model.Address;
 
public class SignUpForm {
    
    @Size(min = 3, max = 50)
    private String firstName;
 
    
    @Size(min = 3, max = 50)
    private String surname;
 
    
    @Size(min = 3, max = 50)
    private String supplyName;
 
    @NotBlank
    @Size(min=3, max = 50)
	private String username;
 
    
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    @NotBlank
	@Column(name ="contactNumber")
	private String contactNumber;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    private Address address;
    
    
    private Set<String> role;


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


	public Set<String> getRole() {
		return role;
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


	public void setAddress(Address address) {
		this.address = address;
	}


	public void setRole(Set<String> role) {
		this.role = role;
	}
    
   
 
   
}