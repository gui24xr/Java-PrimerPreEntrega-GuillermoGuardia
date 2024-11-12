package com.coderhouse.services;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CreateInvoiceDTO;
import com.coderhouse.dto.CreateSaleDTO;
import com.coderhouse.dto.UpdateProductStockDTO;
import com.coderhouse.models.Client;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.Product;
import com.coderhouse.models.InvoiceDetail;
import com.coderhouse.repositories.InvoicesRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoicesService {
	
	@Autowired
	InvoicesRepository invoicesRepository;
	@Autowired
	ClientsService clientsService;
	@Autowired
	ProductsService productsService;
	@Autowired
	InvoicesDetailsService invoicesDetailsService;
	
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
	
	
	@Transactional //Agrega una lista de compras a la invoiceID
	public Invoice addSales(Long invoiceId,List<CreateSaleDTO> listSalesDTO) {
		List<Product> productsWithoutStock = new ArrayList<>();
		
		try {
			 Invoice updatingInvoice = this.findById(invoiceId); //Usando el metodo del mismo service comprobamos la existencia.
		 	
			 for (CreateSaleDTO saleItem : listSalesDTO) {
				 //Si no existe un producto entonces no se agrega nada
				 if (!productsService.existsProduct(saleItem.getProductId())) throw new IllegalArgumentException("Uno o mas productos que se pretenden agregar no existen...");
				 //Como ya sabemos que el producto existe entonces lo buscvamos y miramos el stock.
				 Product searchedProduct = productsService.findById(saleItem.getProductId());
				//Si El producto que no tiene stock lo metemos en la lista de withouStock, si tiene creamos el detalle, actualizamos stock..
				 if (searchedProduct.getStock()>=saleItem.getQuantity()) {
					 //Procedo a la venta y agregarle a la factura
					 //1- Lo Agrego a la lista de detalles de la factura, Al agregarlo a la tabla se agregaria en cascada solo.
					InvoiceDetail newInvoiceDetail = new InvoiceDetail(searchedProduct.getCurrentPrice(),saleItem.getQuantity(),updatingInvoice,searchedProduct);
					//2- Descuento el stock
					UpdateProductStockDTO updateProductStockObject = new UpdateProductStockDTO();
					updateProductStockObject.setNewStock(searchedProduct.getStock() - saleItem.getQuantity());
					
					productsService.updateProductStock(saleItem.getProductId(), updateProductStockObject);
					//3- Guardo el detail pidendole al servicio de details y por cascada se guarda en la factura-
					invoicesDetailsService.createSale(newInvoiceDetail);
					//Cambiamos el amount;
					int newAmount = (int) (updatingInvoice.getAmount() + (((saleItem.getQuantity() * searchedProduct.getCurrentPrice()))));
					updatingInvoice.setAmount(newAmount);
					invoicesRepository.save(updatingInvoice);
					
				 }
				 else {
					 //Lo agrego en la lista de los sin stock
					 productsWithoutStock.add(searchedProduct);
				 }
				 
			
		        }
			 
			 //Como la modificacion la jhicios en cascada a travez del servicio de invoicesDetails ahora podemos devolver actualizada la invoice.
			 Invoice searchedInvoice = this.findById(invoiceId);
			 return searchedInvoice;
		
	 
		}catch(Exception err) {
			 throw new IllegalArgumentException("Error al crear la venta: " + err.getMessage(), err);
		}
		
		
		
		
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
