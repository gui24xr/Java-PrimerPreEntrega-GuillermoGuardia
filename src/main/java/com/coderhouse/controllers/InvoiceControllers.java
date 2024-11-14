package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.CreateInvoiceDTO;
import com.coderhouse.dto.CreateSaleDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.InvoiceDetail;
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
	
	
	@PostMapping("/{id}/details")
	public ResponseEntity<Invoice> addSalesToInvoice(@PathVariable("id") Long invoiceId,@Valid @RequestBody CreateSaleDTO newSaleCreateObject){
		try {
			Invoice updatedInvoice = invoicesService.addDetailToInvoice(invoiceId, newSaleCreateObject);
			return ResponseEntity.ok(updatedInvoice);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable("id") Long invoiceId){
		try {
			invoicesService.deleteInvoiceById(invoiceId);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	
	//-------DETAILS -------------------------------------//
	
	@GetMapping("/details")
	public ResponseEntity<List<InvoiceDetail>> getAllDetails(){
		try {
			List<InvoiceDetail> invoicesDetails = invoicesService.getAllDetails();
			return ResponseEntity.ok(invoicesDetails);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/details/{id}")
	public ResponseEntity<InvoiceDetail> getInvoiceDetailById(@PathVariable("id") Long detailId){
		try {
			InvoiceDetail searchedInvoiceDetail = invoicesService.findDetailById(detailId);
			return ResponseEntity.ok(searchedInvoiceDetail);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	
	
	@DeleteMapping("/details/{id}")
	public ResponseEntity<Void> deleteDetail(@PathVariable("id") Long detailId){
		try {
			invoicesService.deleteDetail(detailId);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
}
