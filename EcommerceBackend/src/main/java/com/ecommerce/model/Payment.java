package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int payId;
	@Column(name ="bank")
	private String bank;
	@Column(name ="accountNumber")
	private int accountNumber;
	@Column(name ="amount")
	private double amount;
	@ManyToOne
	@JoinColumn(name="orderId")
	private ProductOrder productOrder ;
	
	@ManyToOne
	@JoinColumn(name="cardNumber")
	private SmartShopper  smartShopper;
	public Payment() {
		super();
		
	}
	public Payment(int payId, String bank, int accountNumber, double amount, ProductOrder productOrder) {
		super();
		this.payId = payId;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.productOrder = productOrder;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	
	
	
	
	
}
