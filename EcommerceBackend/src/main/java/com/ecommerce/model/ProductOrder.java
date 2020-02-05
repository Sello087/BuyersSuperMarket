package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Entity
@Table(name = "ProductOrder")
@EntityListeners(AuditingEntityListener.class)
public class ProductOrder {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	private String orderDate;
	private int qtyOrdered;
	@ManyToOne
	@JoinColumn(name="cartId")
	private ShoppingCart objShoppingCart;
	
	@ManyToOne
	@JoinColumn(name="barcode")
	private User objUser;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Product objProduct;
	
	 
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "productOrder")
		private List<Payment> payment= new ArrayList<>();

	 
	 
	 public ProductOrder() {
		super();
		
	}


	public ProductOrder(int orderId, String orderDate, int qtyOrdered, ShoppingCart objShoppingCart,User objUser, Product objProduct,List<Payment> payment) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.qtyOrdered = qtyOrdered;
		this.objShoppingCart = objShoppingCart;
		this.objUser = objUser;
		this.objProduct = objProduct;
		this.payment = payment;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	public ShoppingCart getObjShoppingCart() {
		return objShoppingCart;
	}
	public void setObjShoppingCart(ShoppingCart objShoppingCart) {
		this.objShoppingCart = objShoppingCart;
	}
	public User getObjUser() {
		return objUser;
	}
	public void setObjUser(User objUser) {
		this.objUser = objUser;
	}
	public Product getObjProduct() {
		return objProduct;
	}
	public void setObjProduct(Product objProduct) {
		this.objProduct = objProduct;
	}


	public List<Payment> getPayment() {
		return payment;
	}


	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}
	
	
}
