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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "SmartShopper")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmartShopper {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cardNumber;
	@Column(name ="points")
	private int points;
	@ManyToOne
	@JoinColumn(name="userId")
	private User objUser;
	

	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "productOrder")
		private List<Payment> payment= new ArrayList<>();

	 
	
	public SmartShopper() {
		super();
		
	}
	public SmartShopper(int cardNumber, int points, User objUser) {
		super();
		this.cardNumber = cardNumber;
		this.points = points;
		this.objUser = objUser;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public User getObjUser() {
		return objUser;
	}
	public void User(User objUser) {
		this.objUser = objUser;
	}
	
	
	

}
