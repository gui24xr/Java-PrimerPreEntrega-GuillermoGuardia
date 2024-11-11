package com.coderhouse.dto;
import jakarta.validation.constraints.Min;

public class CreateProductDTO {
	
	private String code;
	private String title;
	@Min(value = 1, message = "El precio debe ser mayor que 0.")
	private float currentPrice;
	 @Min(value = 1, message = "El stock debe ser mayor que 0.")
	private int stock;
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
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		if (currentPrice > 0) {
			this.currentPrice = currentPrice;
		}
	
		else {
			throw new IllegalArgumentException("El precio debe ser mayor a cero.");
		}
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		if (stock > -1) {
			this.stock = stock;
		}
	
		else {
			throw new IllegalArgumentException("El stock debe ser cero o mayor...");
		}
	}
	
	
	
}
