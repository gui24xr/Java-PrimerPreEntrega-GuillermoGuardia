package com.coderhouse.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long idCliente;
	
	@Column (name = "code",nullable=false, unique=true)
	private String code;
	
	@Column(name="title")
	private String title;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductStock productStock;
	
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false, unique = true)
	private ProductPrice productprice;

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
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

	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}

	public ProductPrice getProductprice() {
		return productprice;
	}

	public void setProductprice(ProductPrice productprice) {
		this.productprice = productprice;
	}

	@Override
	public String toString() {
		return "Product [idCliente=" + idCliente + ", code=" + code + ", title=" + title + ", createdAt=" + createdAt
				+ ", productStock=" + productStock + ", productprice=" + productprice + "]";
	}

	public Product(long idCliente, String code, String title, Date createdAt) {
		super();
		this.idCliente = idCliente;
		this.code = code;
		this.title = title;
		this.createdAt = createdAt;
	}
	 



}
