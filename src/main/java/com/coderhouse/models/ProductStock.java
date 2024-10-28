package com.coderhouse.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
 @Table(name="products_stock")
public class ProductStock {

	@Id
	@Column(name = "product_id")
	private long productId;
	
	@Column(name = "quantity",nullable = false)
	private int quantity;
	
	@Column(name = "last_updated")
	private Date lastUpdated;

	@Override
	public String toString() {
		return "ProductStock [productId=" + productId + ", quantity=" + quantity + ", lastUpdated=" + lastUpdated + "]";
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}
