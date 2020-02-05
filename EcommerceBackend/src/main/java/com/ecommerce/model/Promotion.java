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
@Table(name = "Promotion")
@EntityListeners(AuditingEntityListener.class)
public class Promotion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int promotionId;
	@Column(name ="discount")
	private double discount;
	@Column(name ="endDate")
	private String endDate;
	
	@ManyToOne
	@JoinColumn(name="barcode")
	private Product product;

	public Promotion() {
		super();
		
	}

	public Promotion(int promotionId, double discount, String endDate, Product product) {
		super();
		this.promotionId = promotionId;
		this.discount = discount;
		this.endDate = endDate;
		this.product = product;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
