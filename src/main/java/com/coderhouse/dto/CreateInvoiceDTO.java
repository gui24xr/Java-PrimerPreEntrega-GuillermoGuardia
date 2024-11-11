package com.coderhouse.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class CreateInvoiceDTO {
	@NotNull
	private Date invoiceDate;
	@NotNull //Podria validar aca que el cliente exista o no, a futuro..
	private Long clientId;
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		//Agregar validaciones.
		this.invoiceDate = invoiceDate;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		//Agregar validaciones
		this.clientId = clientId;
	}
	
	
}
