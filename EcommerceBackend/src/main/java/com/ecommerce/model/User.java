package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



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
	private Address objAddress;
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles", 
	      joinColumns = @JoinColumn(name = "user_id"), 
	      inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	
	 
	 
	 
	 public User() {
		super();
		
	}
	 
	 
	public User(int userId, String firstName, String surname, String supplyName,
			 @Size(min = 3, max = 50) String username,  @Size(max = 50) String email,
			 String contactNumber,  String password, Address objAddress, Set<Role> roles) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surname = surname;
		this.supplyName = supplyName;
		this.username = username;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.objAddress = objAddress;
		this.roles = roles;
	}


	public User(String firstName,String surname,String supplyName, String username, String email, String contactNumber,String password,Address address) {
        this.firstName = firstName;
        this.surname = surname;
        this.supplyName= supplyName;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.objAddress = address;
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
	public Address getObjAddress() {
		return objAddress;
	}
	public Set<Role> getRoles() {
		return roles;
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
	public void setObjAddress(Address objAddress) {
		this.objAddress = objAddress;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	 
	 
	 
}
