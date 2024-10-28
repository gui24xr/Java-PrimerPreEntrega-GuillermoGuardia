package com.coderhouse.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
 @Table(name="products_price")
public class ProductPrice {

	@Id
	@Column(name = "product_id")
	private long productId;
	
	@Column(name = "quantity",nullable = false)
	private float price;
	
	@Column(name = "last_updated")
	private Date lastUpdated;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "ProductPrice [productId=" + productId + ", price=" + price + ", lastUpdated=" + lastUpdated + "]";
	}
	
	
	
}
