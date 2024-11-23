package com.coderhouse.services;



import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CreateInvoiceDTO;
import com.coderhouse.dto.CreateSaleDTO;

import com.coderhouse.models.Client;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.Product;
import com.coderhouse.models.InvoiceDetail;
import com.coderhouse.repositories.InvoicesDetailsRepository;
import com.coderhouse.repositories.InvoicesRepository;

import jakarta.transaction.Transactional;

@Service
//Este sewrvicio trabajara con las entidades invoice e invoiceDetail
public class InvoicesService {
	
	@Autowired
	InvoicesRepository invoicesRepository;
	@Autowired
	InvoicesDetailsRepository invoicesDetailsRepository;
	@Autowired
	ClientsService clientsService;
	@Autowired
	ProductsService productsService;
	@Autowired
	TimeService timeService;

	
	public List<Invoice> getAllInvoices(){
		return invoicesRepository.findAll();
	}
	

	public List<InvoiceDetail> getAllDetails(){
		return invoicesDetailsRepository.findAll();
	}
	
	public InvoiceDetail findDetailById(Long saleId) {
		return invoicesDetailsRepository.findById(saleId)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada..."));
	}
	
	
	
	public Invoice findById(Long invoiceId) {
		return invoicesRepository.findById(invoiceId)
				.orElseThrow(()-> new IllegalArgumentException("Factura no encontrada..."));
	}
	
	public Boolean invoiceExists(Long invoiceId) {
		return invoicesRepository.existsById(invoiceId);
	}
	
	public boolean existsDetailWithProductId(Long productId) {
		//Devuelve un true o false segun existan o no, detalles que tengan al producto
		List<InvoiceDetail> listOfDetails = invoicesDetailsRepository.findAll();
		Optional<InvoiceDetail> searchedDetail = listOfDetails.stream().filter(detail -> detail.getProduct().getProductId() == productId).findFirst();
		
		if (searchedDetail.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	
	}
	
	public void deleteInvoiceById(Long clientId) {
		if (!invoicesRepository.existsById(clientId)) throw new IllegalArgumentException("Factura no encontrada...");
		//Aca vendria solo si existe x lo cual puedo borralos sin problemas.
		invoicesRepository.deleteById(clientId);
	}
	
	@Transactional
	public Invoice createInvoice(CreateInvoiceDTO newInvoiceDTO) {
		//Crea una factura vacia con la fecha invoiceDate y para el client clientId 
		//El dto trae invoiceDate y clientId
		Invoice invoiceForSave = new Invoice();
		
		
		/*IMPORTANTE: si en el dto de creacion no vino una fecha, usamos el date del timeService.*/
		if (newInvoiceDTO.getInvoiceDate() == null ) {
			invoiceForSave.setInvoiceDate(timeService.getCurrentDateFromAPI());
		}
		else {
			
			invoiceForSave.setInvoiceDate(newInvoiceDTO.getInvoiceDate());
		}
		
		//Seteamos su amount en cero
		invoiceForSave.setAmount(0);
		
		//Me faltaria setear el cliente pero como tengo el cliente id y tengo que ingresar el registro entero...
		Client searchedClient = clientsService.findById(newInvoiceDTO.getClientId());
		//Si hay error la excepcion viene por consecuencia del servicio...
		invoiceForSave.setClient(searchedClient);
		return invoicesRepository.save(invoiceForSave);
	}
	

	public Invoice setPayedInvoiceStatus(Long invoiceId,boolean payedValue) {
		//cambia el estado de pago de la factura con la fecha actual, o sea abre o cierra la factura...
		Optional<Invoice> searchedInvoice = invoicesRepository.findById(invoiceId);
		if (!searchedInvoice.isEmpty()) {
			searchedInvoice.get().setPayedDateLastUpdate(new Date(System.currentTimeMillis()));
			searchedInvoice.get().setPayed(payedValue);
		}
		return invoicesRepository.save(searchedInvoice.get());
	}
	
		
	@Transactional //Agrega una lista de compras a la invoiceID siempre y cuando exista y no este paga(o sea cerrada)....
	public Invoice addDetailToInvoice(Long invoiceId,CreateSaleDTO createSaleObject) {
		try {
			//1-Existe la factura donde agregare el producto? Si no existe salimos con excpecion. 
			Invoice updatingInvoice = this.findById(invoiceId); //Usando el metodo del mismo service comprobamos la existencia.
			
			//2- Esta paga? Si esta pagada salimos con excepcion ya que es una factuta cerrada.
			if (updatingInvoice.getPayed() == true) throw new IllegalArgumentException("La factura ya se encuentra cerrada por lo cual no se pueden agregar productos...");
			
			//3- Existe el producto? Si no existe salimos..
			if (!productsService.existsProduct(createSaleObject.getProductId())) throw new IllegalArgumentException("Uno o mas productos que se pretenden agregar no existen...");
			//4- Buscamos el producto usando el objeto de creacion de venta que me trae el id del producto y la cantidad requerida. Tambien averiguamos el stock.
			Product searchedProduct = productsService.findById(createSaleObject.getProductId());
			//SI el producto no esta activo/habilitado tambien salimos con excepcion xq no se puede agregar.
			if (!searchedProduct.isActive())throw new IllegalArgumentException("Producto no habilitado a la venta...");
			//5- No hay stock? Salimos con excpecion
			if (!(searchedProduct.getStock()>=createSaleObject.getQuantity()))  throw new IllegalArgumentException("No Hay stock suficiente del producto para agregar...");
			//Como ya se que tengo todo lo necesario puedo proceder.
			//Creo y guardo el detalle y actualizo el amount. en la factura.
			
			
			//Existe ya este producto en la factura? Si existe actualizo la cantidad del detalle, si no existe creo el detalle.
			//Puedo buscar en la invoiceId los detalles y ver si alguno tiene ese producto.
			List<InvoiceDetail> listOfDetailsInInbox = updatingInvoice.getDetail();
			Optional<InvoiceDetail> searchedDetail = listOfDetailsInInbox.stream().filter(detail -> detail.getProduct().getProductId() == createSaleObject.getProductId()).findFirst();
			
			if (searchedDetail.isPresent()) {
				//Si esta presente modifico el detalle y actualizo la cantidad sumandole la requerida por la compra.
				
				searchedDetail.get().setSaleQuantity(searchedDetail.get().getSaleQuantity() + createSaleObject.getQuantity());
				updatingInvoice.adjustAmount(searchedDetail.get().getSubTotalAmount());
			}
			else {
				//Si no esta el detalle, lo creo..
				InvoiceDetail newInvoiceDetail = new InvoiceDetail(searchedProduct.getCurrentPrice(),createSaleObject.getQuantity(),updatingInvoice,searchedProduct);
				invoicesDetailsRepository.save(newInvoiceDetail);
				//Le sumamos a la factura el submonto total que ya se calculo al crearla con el constructor que utiice
				updatingInvoice.adjustAmount(newInvoiceDetail.getSubTotalAmount());
			}
		
			
			// Le pido al servicio de productos que ajuste el stock del producto vendido.
			productsService.adjustProductStock(createSaleObject.getProductId(), createSaleObject.getQuantity() * (-1));
			// Ya todas las operaciones hechas retorno la invoice updateada...
			return invoicesRepository.save(updatingInvoice);
		}catch(Exception err) {
			 throw err;
		}
	
	}
	
	//Borra el detalle, devuelve el stock y actualiza el amount de la factura.
	@Transactional
	public void deleteDetail(Long detailId) {
		try {
			InvoiceDetail searchedInvoiceDetail = this.findDetailById(detailId); //Factura a actualizar.
			Invoice invoiceToUpdateAmount = searchedInvoiceDetail.getInvoice();//Producto a actualizar el stocl.
			Product productToUpdateStock = searchedInvoiceDetail.getProduct();//Producto a actualizar el stock.
			
			//Con las propiedades del detalle sabemos cantidades de stock a devolver y monto a actujalizar.
			int restockQuantity = searchedInvoiceDetail.getSaleQuantity();
			float amountToQuitInInvoice = searchedInvoiceDetail.getSubTotalAmount() * (-1);
			//Borro el detalle, ajusto monto de la factura y devuelvo stock del producto.
			invoicesDetailsRepository.deleteById(detailId);
			invoiceToUpdateAmount.adjustAmount(amountToQuitInInvoice);
			productsService.adjustProductStock(productToUpdateStock.getProductId(), restockQuantity); 
		}catch(Exception err) {
			throw err;
		}
		
	}
	
	
	
}
