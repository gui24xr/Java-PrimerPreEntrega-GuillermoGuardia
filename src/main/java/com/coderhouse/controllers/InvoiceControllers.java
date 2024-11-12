package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.CreateInvoiceDTO;
import com.coderhouse.dto.CreateSaleDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.services.InvoicesService;

import jakarta.validation.Valid;

 @RestController
 @RequestMapping("/api/invoices")
public class InvoiceControllers {
	@Autowired
	InvoicesService  invoicesService;
	

	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoices(){
		try {
			List<Invoice> invoices = invoicesService.getAllInvoices();
			return ResponseEntity.ok(invoices);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long invoiceId){
		try {
			Invoice searchedInvoice = invoicesService.findById(invoiceId);
			return ResponseEntity.ok(searchedInvoice);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	@PostMapping //Crea un invoice vacia con la fecha y el clientId indicados en el createInvoiceDTO
	public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody CreateInvoiceDTO newInvoiceDTO){
		try {
			Invoice createdInvoice = invoicesService.createInvoice(newInvoiceDTO);
			return ResponseEntity.ok(createdInvoice);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping("/{id}/sales")
	public ResponseEntity<Invoice> addSalesToInvoice(@PathVariable("id") Long invoiceId,@Valid @RequestBody List<CreateSaleDTO> newSalesListDTO){
		try {
			Invoice updatedInvoice = invoicesService.addSales(invoiceId, newSalesListDTO);
			return ResponseEntity.ok(updatedInvoice);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//El update d invoice podria tener para modificarle compras o details
	//Podria tener un put para modificar el clientID en la factura
	
	
}
