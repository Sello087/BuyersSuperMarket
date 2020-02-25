package com.ecommerce.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Product")
@EntityListeners(AuditingEntityListener.class)


@JsonIgnoreProperties(ignoreUnknown = true)


public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int barcode;
	@Column(name ="prodName")
	private String prodName;
	@Column(name ="category")
	private String category;
	@Column(name ="size")
	private int size;
	@Column(name ="qtyInStock")
	private int qtyInStock;
	@Column(name ="price")
	private double price;
	@Column(name ="fileName")
	private String fileName;
	@Column(name ="logo")
	@Lob
	private byte[] logo ;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	 @OneToMany(cascade=CascadeType.ALL,mappedBy="product")
	 private List<Promotion> promoList = new ArrayList<>();
	 
	 @JsonIgnore
	 @OneToMany(cascade=CascadeType.ALL,mappedBy="objProduct")
	  
	 private List<ProductOrder> prodOrder = new ArrayList<>();
	 @JsonIgnore
	 @OneToMany(cascade=CascadeType.ALL, mappedBy= "product")
		private List<SupplyOrder> supplyOrder= new ArrayList<>();

	public Product() {
		super();
		
	}
	
	public Product(int barcode,String prodName, String category, int size, int qtyInStock, double price) {
		super();
		this.barcode = barcode;
		this.prodName = prodName;
		this.category = category;
		this.size = size;
		this.qtyInStock = qtyInStock;
		this.price = price;
		
	}
	

	public Product(String prodName, String category, int size, int qtyInStock, double price, String fileName,
			byte[] logo) {
		super();
		this.prodName = prodName;
		this.category = category;
		this.size = size;
		this.qtyInStock = qtyInStock;
		this.price = price;
		this.fileName = fileName;
		this.logo = logo;
	}


	





	public Product(int barcode, String prodName, String category, int size, int qtyInStock, double price, String fileName,
			byte[] logo, User user, List<Promotion> promoList, List<ProductOrder> prodOrder,
			List<SupplyOrder> supplyOrder) {
		super();
		this.barcode = barcode;
		this.prodName = prodName;
		this.category = category;
		this.size = size;
		this.qtyInStock = qtyInStock;
		this.price = price;
		this.fileName = fileName;
		this.logo = logo;
		this.user = user;
		this.promoList = promoList;
		this.prodOrder = prodOrder;
		this.supplyOrder = supplyOrder;
	}





	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getQtyInStock() {
		return qtyInStock;
	}
	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public List<Promotion> getPromoList() {
		return promoList;
	}


	public void setPromoList(List<Promotion> promoList) {
		this.promoList = promoList;
	}


	public List<ProductOrder> getProdOrder() {
		return prodOrder;
	}


	public void setProdOrder(List<ProductOrder> prodOrder) {
		this.prodOrder = prodOrder;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public byte[] getLogo() {
		return logo;
	}


	public void setLogo(byte[] logo) {
		this.logo = logo;
	}


	public List<SupplyOrder> getSupplyOrder() {
		return supplyOrder;
	}


	public void setSupplyOrder(List<SupplyOrder> supplyOrder) {
		this.supplyOrder = supplyOrder;
	}


	


	public String getCategory() {
		return category;
	}





	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [prodName=" + prodName + ", category=" + category + ", size=" + size + ", qtyInStock="
				+ qtyInStock + ", price=" + price + ", fileName=" + fileName + ", logo=" + Arrays.toString(logo) + "]";
	}

	
	
}
