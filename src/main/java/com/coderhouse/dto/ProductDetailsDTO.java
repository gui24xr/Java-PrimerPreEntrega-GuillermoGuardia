package com.coderhouse.dto;

import java.sql.Date;

public class ProductDetailsDTO {
	
	private Long productId;
	private String code;
	private String title; 
	private Date createdAt;
	private Date lastUpdated;
	
	
	public ProductDetailsDTO(Long productId, String code, String title) {
		super();
		
		this.code = code;
		this.title = title;
	}


	@Override
	public String toString() {
		return "ProductDetailsDTO [productId=" + productId + ", code=" + code + ", title=" + title + ", createdAt="
				+ createdAt + ", lastUpdated=" + lastUpdated + "]";
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
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


	public Date getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	
	
	
	
	
}
