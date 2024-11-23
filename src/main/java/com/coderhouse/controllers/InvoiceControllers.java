package com.coderhouse.controllers;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 @Tag(name="Gestion de facturas y detalles de ventas.", description="Endpoints de facturas y detalles de ventas.")
 @RequestMapping("/api/invoices")
public class InvoiceControllers {
	@Autowired
	InvoicesService  invoicesService;
	


	@Operation(summary = "Obtener todas las facturas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de facturass obtenida correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoices(){
		try {
			List<Invoice> invoices = invoicesService.getAllInvoices();
			return ResponseEntity.ok(invoices);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

	@Operation(summary = "Obtener factura por id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Factura encontrada...", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)) }),
			@ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content) })
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
	
	
	
	@Operation(summary = "Crear una nueva factura.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Factura creada correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping //Crea un invoice vacia con la fecha y el clientId indicados en el createInvoiceDTO
	public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody CreateInvoiceDTO newInvoiceDTO){
		try {
			Invoice createdInvoice = invoicesService.createInvoice(newInvoiceDTO);
			return ResponseEntity.ok(createdInvoice);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@Operation(summary = "Crear un nuevo detalle de venta en factura.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Detalle de venta agregado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping("/{id}/details")
	public ResponseEntity<Invoice> addSalesToInvoice(@PathVariable("id") Long invoiceId,@Valid @RequestBody CreateSaleDTO newSaleCreateObject){
		try {
			Invoice updatedInvoice = invoicesService.addDetailToInvoice(invoiceId, newSaleCreateObject);
			return ResponseEntity.ok(updatedInvoice);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	

	
	@Operation(summary = "Eliminacion de una factura.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Factura eliminada correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content) })
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
	
	
	

	@Operation(summary = "Actualizacion pagado/no pago de una factura...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)) }),
			@ApiResponse(responseCode = "404", description = "Operacion no realizada...", content = @Content) })
	@PutMapping("/{id}/payed")
	public ResponseEntity<Invoice> updatePayedState(@PathVariable("id") Long invoiceId,@RequestBody Boolean payed){
	
		try {
			Invoice updatedInvoice = invoicesService.setPayedInvoiceStatus(invoiceId, payed);
			return ResponseEntity.ok(updatedInvoice);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	//-------DETAILS -------------------------------------//
	


	@Operation(summary = "Obtener todas los detalles de venta en una factura.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de detalles de venta de facturas obtenida correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceDetail.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping("/details")
	public ResponseEntity<List<InvoiceDetail>> getAllDetails(){
		try {
			List<InvoiceDetail> invoicesDetails = invoicesService.getAllDetails();
			return ResponseEntity.ok(invoicesDetails);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	


	@Operation(summary = "Obtener detalle de venta por id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Detalle de venta encontrado...", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceDetail.class)) }),
			@ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content) })
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
	
	
	

	
	@Operation(summary = "Eliminacion de un detalle de venta en una factura.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Detalle de venta de factura eliminado correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado en factura.", content = @Content) })
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
