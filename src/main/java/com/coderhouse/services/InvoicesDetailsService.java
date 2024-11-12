package com.coderhouse.services;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.coderhouse.models.InvoiceDetail;

import com.coderhouse.repositories.InvoicesDetailsRepository;

import jakarta.transaction.Transactional;


@Service
public class InvoicesDetailsService {
	
	@Autowired
	InvoicesDetailsRepository invoicesDetailsRepository;
	
	
	@Autowired
	ProductsService productsService;
	

	public List<InvoiceDetail> getAllSales(){
		return invoicesDetailsRepository.findAll();
	}
	
	public InvoiceDetail findById(Long saleId) {
		return invoicesDetailsRepository.findById(saleId)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada..."));
	}
	
	
	@Transactional 
	public InvoiceDetail createSale(InvoiceDetail invoiceDetail) {
		//Crear venta es igual a agregar un invoice detail
		return invoicesDetailsRepository.save(invoiceDetail);
	}
	
}