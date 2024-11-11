package com.coderhouse.dto;

import jakarta.validation.constraints.Min;

public class UpdateProductStockDTO {
	@Min(value = -1, message = "El stock debe ser mayor que 0.")
    private int newStock;
   
  
    public int getNewStock() {
        return newStock;
    }

    public void setNewStock(int newStock) {
    	if (newStock > -1) {
			this.newStock = newStock;
		}
	
		else {
			throw new IllegalArgumentException("El stock debe ser cero o mayor...");
		}
    }
}
