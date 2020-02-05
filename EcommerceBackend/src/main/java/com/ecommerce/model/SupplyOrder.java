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
@Table(name = "SupplyOrder")
@EntityListeners(AuditingEntityListener.class)
public class SupplyOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int supplyOrderId;
	@Column(name ="status")
	private String status;
	@Column(name ="qtyOrdered")
	private int qtyOrdered;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="barcode")
	private Product product;
	
	
	

}
