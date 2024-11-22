package com.coderhouse.models;

import java.sql.Date;



import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	
	
	@Column(name="active_status_last_update")
	@CreationTimestamp 
	private Date activeStatusLastUpdate;
	
	@Column(name="active",nullable=false, columnDefinition = "BOOLEAN DEFAULT true") //Al crear un producto x default esta enabled.
	private boolean active;
	

	

	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", code=" + code + ", title=" + title + ", createdAt=" + createdAt
				+ ", lastUpdate=" + lastUpdate + ", currentPrice=" + currentPrice + ", currentPriceLastUpdate="
				+ currentPriceLastUpdate + ", stock=" + stock + ", stockLastUpdate=" + stockLastUpdate
				+ ", activeStatusLastUpdate=" + activeStatusLastUpdate + ", enabled=" + active + "]";
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

	public Date getActiveStatusLastUpdate() {
		return activeStatusLastUpdate;
	}

	public void setActiveStatusLastUpdate(Date activeStatusLastUpdate) {
		this.activeStatusLastUpdate = activeStatusLastUpdate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	
	
}
