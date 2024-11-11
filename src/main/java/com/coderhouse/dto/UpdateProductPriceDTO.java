package com.coderhouse.dto;

import jakarta.validation.constraints.Min;

public class UpdateProductPriceDTO {

	@Min(value = 1, message = "El precio debe ser mayor que 0.")
	private float newCurrentPrice;

	public float getNewCurrentPrice() {
		return newCurrentPrice;
	}

	public void setNewCurrentPrice(float newCurrentPrice) {
		if (newCurrentPrice > 0) {
			this.newCurrentPrice = newCurrentPrice;
		}
	
		else {
			throw new IllegalArgumentException("El precio debe ser mayor a cero.");
		}
			
	}
	
	
	
}
