package com.coderhouse.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	
	@Column (name = "code",nullable=false, unique=true)
	private String code;
	
	@Column(name="title")
	private String title;
	
	@Column(name="created_at")
	@CreationTimestamp //Para que al crearse el registro
	private Date createdAt;
	
	@Column(name="last_update")
	@CreationTimestamp //Para que al crearse el registro ponga el valor pero este luego sera actualizado maniualemente en los update.
	private Date lastUpdate;
	
	
	@Column(name="current_price")
	private float currentPrice;
	
	@Column(name="current_price_last_update")
	@CreationTimestamp //Luego de la creacion hay que actualiz<r manualmente,
	private Date currentPriceLastUpdate;
	

	@Column(name="stock")
	private int stock;
	
	@Column(name="stock_last_update")
	@CreationTimestamp //Luego de la creacion hay que actualiz<r manualmente,
	private Date stockLastUpdate;
	
	
    @OneToMany(mappedBy = "product")
    private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", code=" + code + ", title=" + title + ", createdAt=" + createdAt
				+ ", lastUpdate=" + lastUpdate + ", currentPrice=" + currentPrice + ", currentPriceLastUpdate="
				+ currentPriceLastUpdate + ", stock=" + stock + ", stockLastUpdate=" + stockLastUpdate + "]";
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Date getCurrentPriceLastUpdate() {
		return currentPriceLastUpdate;
	}

	public void setCurrentPriceLastUpdate(Date currentPriceLastUpdate) {
		this.currentPriceLastUpdate = currentPriceLastUpdate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getStockLastUpdate() {
		return stockLastUpdate;
	}

	public void setStockLastUpdate(Date stockLastUpdate) {
		this.stockLastUpdate = stockLastUpdate;
	}


	
}
