package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity
@Table(name = "Address")
@EntityListeners(AuditingEntityListener.class)
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addId;
	
	@Column(name ="houseNumber")
	private int houseNumber;
	@Column(name ="street")
	private String street;
	@Column(name ="city")
	private String city;
	@Column(name ="state")
	private String state;
	@Column(name ="zipCode")
	private int zipCode;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy= "address")
	private List<User> user = new ArrayList<>();

	public Address() {
		super();
	}

	public Address(int addId, int houseNumber, String street, String city, String state, int zipCode,
			List<User> user) {
		super();
		this.addId = addId;
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.user = user;
	}

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
	
	
}

