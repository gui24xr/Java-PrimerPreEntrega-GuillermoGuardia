package com.coderhouse.models;

import java.sql.Date;



import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Schema(description = "Modelo de producto.") 
@Table(name="products")
public class Product {


	@Id
	@Schema(description = "Id del producto.") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	
	@Schema(description = "Codigo de identificacion del producto.") 
	@Column (name = "code",nullable=false, unique=true)
	private String code;
	
	@Schema(description = "Titulo del producto.") 
	@Column(name="title")
	private String title;
	
	@Schema(description = "Fecha de creacion del producto.") 
	@Column(name="created_at")
	@CreationTimestamp //Para que al crearse el registro
	private Date createdAt;
	
	@Schema(description = "Fecha de ultima actualizacion del producto.") 
	@Column(name="last_update")
	@CreationTimestamp //Para que al crearse el registro ponga el valor pero este luego sera actualizado maniualemente en los update.
	private Date lastUpdate;
	
	@Schema(description = "Precio actual producto.") 
	@Column(name="current_price")
	private float currentPrice;
	
	@Schema(description = "Fecha de ultima actualizacion de precio del producto.") 
	@Column(name="current_price_last_update")
	@CreationTimestamp //Luego de la creacion hay que actualiz<r manualmente,
	private Date currentPriceLastUpdate;
	
	@Schema(description = "Stock actual del producto.") 
	@Column(name="stock")
	private int stock;
	
	@Schema(description = "Fecha de ultima actualizacion del stock del producto.") 
	@Column(name="stock_last_update")
	@CreationTimestamp //Luego de la creacion hay que actualiz<r manualmente,
	private Date stockLastUpdate;
	
	@Schema(description = "Estado activo/inactivo del producto.") 
	@Column(name="active",nullable=false, columnDefinition = "BOOLEAN DEFAULT true") //Al crear un producto x default esta enabled.
	private boolean active;
	
	@Schema(description = "Fecha de ultima actualizacion de Estado activo/inactivo del producto.") 
	@Column(name="active_status_last_update")
	@CreationTimestamp 
	private Date activeStatusLastUpdate;
	
	
	


	
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
