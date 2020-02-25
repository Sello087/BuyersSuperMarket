package com.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "ProductOrder")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrder {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	private Date  orderDate;
	private int qtyOrdered;
	@ManyToOne
	@JoinColumn(name="cartId")
	private ShoppingCart objShoppingCart;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="barcode")
	private Product objProduct;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User objUser;
	
	
	@JsonIgnore
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "productOrder")
		private List<Payment> payment= new ArrayList<>();

	 public ProductOrder( ) {
			super();
			
			
		}

	 
	 public ProductOrder( int qtyOrdered, Product objProduct) {
			super();
			
			this.qtyOrdered = qtyOrdered;
			this.objProduct = objProduct;
			
		}
 
	 

	 public ProductOrder( Date orderDate, int qtyOrdered, ShoppingCart objShoppingCart,User objUser, Product objProduct) {
			super();
			
			this.orderDate = orderDate;
			this.qtyOrdered = qtyOrdered;
			this.objShoppingCart = objShoppingCart;
			this.objUser = objUser;
			this.objProduct = objProduct;
			
		}
	 public ProductOrder(int orderId, Date orderDate, int qtyOrdered, ShoppingCart objShoppingCart,User objUser, Product objProduct) {
			super();
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.qtyOrdered = qtyOrdered;
			this.objShoppingCart = objShoppingCart;
			this.objUser = objUser;
			this.objProduct = objProduct;
			
		}

	public ProductOrder(int orderId, Date orderDate, int qtyOrdered, ShoppingCart objShoppingCart,User objUser, Product objProduct,List<Payment> payment) {
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
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
