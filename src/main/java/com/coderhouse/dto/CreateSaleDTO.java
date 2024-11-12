package com.coderhouse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateSaleDTO {
	//Una venta va en una factura, tiene un producto y una cantidad solicitada.
	@NotNull
	private Long productId;
	@NotNull
	@Min(value = -1, message = "la cantidad requerida debe ser mayor que 0.")
	private int quantity;

	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
