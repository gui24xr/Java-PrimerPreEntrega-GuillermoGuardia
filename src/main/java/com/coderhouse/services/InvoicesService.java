package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CreateInvoiceDTO;
import com.coderhouse.models.Client;
import com.coderhouse.models.Invoice;
import com.coderhouse.repositories.InvoicesRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoicesService {
	
	@Autowired
	InvoicesRepository invoicesRepository;
	@Autowired
	ClientsService clientsService;
	
	public List<Invoice> getAllInvoices(){
		return invoicesRepository.findAll();
	}
	
	public Invoice findById(Long invoiceId) {
		return invoicesRepository.findById(invoiceId)
				.orElseThrow(()-> new IllegalArgumentException("Factura no encontrada..."));
	}
	
	public Boolean invoiceExists(Long invoiceId) {
		return invoicesRepository.existsById(invoiceId);
	}
	
	@Transactional
	public Invoice createInvoice(CreateInvoiceDTO newInvoiceDTO) {
		//Crea una factura vacia con la fecha invoiceDate y para el client clientId 
		//El dto trae invoiceDate y clientId
		Invoice invoiceForSave = new Invoice();
		invoiceForSave.setInvoiceDate(newInvoiceDTO.getInvoiceDate());
		invoiceForSave.setAmount(0);
		//Me faltaria setear el cliente pero como tengo el cliente id y tengo que ingresar el registro entero...
		Client searchedClient = clientsService.findById(newInvoiceDTO.getClientId());
		//Si hay error la excepcion viene por consecuencia del servicio...
		invoiceForSave.setClient(searchedClient);
		return invoicesRepository.save(invoiceForSave);
	}
	
	/* NECESITO METODOS PARA
	 * 
	 * AGREGAR VENTA A DETAIL DE FACTURA
	 * QUITAR VENTA A DETAIL DE FACTURA
	 * EDITAR VENTA A DETAIL DE FACTURA
	 * 
	 * ESTOS METODOS TIENEN QUE ACTUALIZAR EL MONTO Y LAS VENTAS
	 * PONER FECHA DE PAGO A LAS IN VOICE
	 * 
	 * ELIMINAR FACTURA
	 * */
;
	

}
