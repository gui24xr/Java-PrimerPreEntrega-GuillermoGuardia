package com.coderhouse.services;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.coderhouse.dto.CreateSaleDTO;
import com.coderhouse.dto.UpdateProductStockDTO;

import com.coderhouse.models.Invoice;
import com.coderhouse.models.Product;
import com.coderhouse.models.Sale;

import com.coderhouse.repositories.SalesRepository;

import jakarta.transaction.Transactional;


@Service
public class SalesService {
	
	@Autowired
	SalesRepository salesRepository;
	
	@Autowired
	InvoicesService invoiceService;

	
	
	@Autowired
	ProductsService productsService;
	

	public List<Sale> getAllSales(){
		return salesRepository.findAll();
	}
	
	public Sale findById(Long saleId) {
		return salesRepository.findById(saleId)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada..."));
	}
	
	@Transactional //Creamos y agrgamos a una factura la venta ademas de descontar el stock. etc
	public Sale createSale(CreateSaleDTO newSaleDTO) {
		//Para crear la venta le pregunto primero al servicio de productos si esta el stock y hay cantidad, si es asi que me los de.
		
	
		try {
			//Buscamos que exista la factura
			if (!invoiceService.invoiceExists(newSaleDTO.getInvoiceId())) throw new IllegalArgumentException("No existe la factura en la cual se pretende agregar la venta...");
			//Ya que ahora sabemos que existe la factura miramos si hay stock.
			Product searchedProduct = productsService.findById(newSaleDTO.getProductId());
			//Tengo el producto miro el stock. Si el stock es menor al requerido ntonces lanzo excepcion.
			if (!(searchedProduct.getStock() >= newSaleDTO.getQuantity())) throw new IllegalArgumentException("No existe el producto o  no hay stock suficiente...");
			//Ya estamos seguros de que el producto exist y hay por lo cual podemos ir armando la venta.
			
			//Pido la factura al invoice service...
			Invoice searchedInvoice = invoiceService.findById(newSaleDTO.getInvoiceId());
			
			
			Sale newSale = new Sale();
			newSale.setInvoice(searchedInvoice);
			newSale.setProduct(searchedProduct);
			newSale.setSalePrice(searchedProduct.getCurrentPrice());
			newSale.setSaleQuantity(newSaleDTO.getQuantity());
			//Falta guardarla pero antes le pedimos al servicio de productos que actualice el stocl;
			
			UpdateProductStockDTO updateProductStockObject = new UpdateProductStockDTO();
			updateProductStockObject.setNewStock(searchedProduct.getStock()-newSaleDTO.getQuantity());
			productsService.updateProductStock(newSaleDTO.getProductId(),updateProductStockObject);
			
			//Y ahora habria que actualizar el precio del total de la factura
			
			
			//Guardamos la nueva factuar
			return salesRepository.save(newSale);
		}catch(Exception err) {
			 throw new IllegalArgumentException("Error al crear la venta: " + err.getMessage(), err);
		}
		

	}
	
	
}