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
@Table(name = "ShoppingCart")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	@Column(name ="totalPrice")
	private double totalPrice;
	@Column(name ="deliveryMethod")
	private String deliveryMethod;
	@Column(name ="paymentStatus")
	private String paymentStatus;
	@Column(name ="deliveryStatus")
	private String deliveryStatus;
	@ManyToOne
	@JoinColumn(name="userId")
	private User objUser;
	

	@OneToMany(cascade=CascadeType.ALL, mappedBy= "objShoppingCart")
	private List<ProductOrder> productOrder = new ArrayList<>();

	public ShoppingCart() {
		super();
		
	}
	


	public ShoppingCart(int cartId, double totalPrice, String deliveryMethod, String paymentStatus,
			String deliveryStatus,List<ProductOrder> productOrder,User objUser) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.deliveryMethod = deliveryMethod;
		this.paymentStatus = paymentStatus;
		this.deliveryStatus = deliveryStatus;
		this.productOrder = productOrder;
		this.objUser = objUser;
	
		
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}



	public User getObjUser() {
		return objUser;
	}



	public void setObjUser(User objUser) {
		this.objUser = objUser;
	}
	
	
}
