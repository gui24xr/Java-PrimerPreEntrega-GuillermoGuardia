package com.coderhouse.dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema (description = "DTO para creacion de facturas")
public class CreateInvoiceDTO {
	
	@Schema (description = "Fecha de factura.")
	@NotNull
	private Date invoiceDate;
	@Schema (description = "Id de cliente para el cual se emite la factura.")
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
