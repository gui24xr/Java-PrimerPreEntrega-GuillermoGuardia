package com.coderhouse.services;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CreateProductDTO;
import com.coderhouse.dto.UpdateProductDetailsDTO;
import com.coderhouse.dto.UpdateProductStockDTO;
import com.coderhouse.dto.UpdateProductPriceDTO;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductsRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductsService {
	
	@Autowired
	ProductsRepository productsRepository;
	
	//@Autowired
	//InvoicesService invoicesService;
	

	//Devuelve todos los productos.
	public List<Product> getAllProducts(){
		return productsRepository.findAll();
	}

	//Devuelve un producto por su id.
	public Product findById(Long id){
		return  productsRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
	}
	
	public Boolean existsProduct(Long id) {
		return productsRepository.existsById(id);
	}
	
	public Product saveProduct(CreateProductDTO newProduct) {
		//Para el producto pedimos code, title,price,stock y le tenemos que setear el createdAt
		Product productForSave = new Product();
		productForSave.setCode(newProduct.getCode());
		productForSave.setTitle(newProduct.getTitle());
		productForSave.setCurrentPrice(newProduct.getCurrentPrice());
		productForSave.setStock(newProduct.getStock());
		productForSave.setActive(true); //Lo creo activo al producto.
		productForSave.setActiveStatusLastUpdate(new Date(System.currentTimeMillis()));
		return productsRepository.save(productForSave);
		
		
	}
	
	@Transactional 
	public Product updateProductDetails(Long productId,UpdateProductDetailsDTO productDetails){
		//Este metodo actualiza los detalles de un producto (code y title) pero no su precio y stock.
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
		
			
		if (productDetails.getCode() != null) updatingProduct.setCode(productDetails.getCode());
		if (productDetails.getTitle() != null) updatingProduct.setTitle(productDetails.getTitle());
		//Pongo en lastUpdated con esta fecha.
		updatingProduct.setLastUpdate(new Date(System.currentTimeMillis()));
		
		return productsRepository.save(updatingProduct);
	}
	
	@Transactional  //Este metodo es para updatear el producto el stock desde el mismo servicio de productos.
	public Product updateProductStock(Long productId,UpdateProductStockDTO newStockDTO) {
		
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
		
		//Aca no valido que sea mayor que cero xq rcibi un dto y eso se valido ahi
			updatingProduct.setStock(newStockDTO.getNewStock());
			updatingProduct.setStockLastUpdate(new Date(System.currentTimeMillis()));
			return productsRepository.save(updatingProduct);
	
	}
	
	//Este lo utilizamos para que otros servicios de la app actualicen el stock
	@Transactional 
	public void adjustProductStock(Long productId,int variationQuantity) {
		try {
			 Product product = productsRepository.findById(productId)
			            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
			 product.setStock(product.getStock() + variationQuantity);
			productsRepository.save(product);
		}catch(Exception err) {
			throw err;
		}
	}
	
	@Transactional 
	public Product updateProductPrice(Long productId,UpdateProductPriceDTO newPriceDTO) {
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
		
		
			updatingProduct.setCurrentPrice(newPriceDTO.getNewCurrentPrice());;
			updatingProduct.setCurrentPriceLastUpdate(new Date(System.currentTimeMillis()));
			return productsRepository.save(updatingProduct);
		
	}
	
	
	public Product changeProductActiveStatus(Long productId, boolean newStatus) {
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
			updatingProduct.setActive(newStatus);
			updatingProduct.setActiveStatusLastUpdate(new Date(System.currentTimeMillis()));
		return productsRepository.save(updatingProduct);
	}
	
	
	
	public void deleteProductById(Long productId) {
		//El producto solo se podra borrar siempre y cuando no exista algun detalle de factura que tenga el producto, ya que si no, romperiamos datos.
		//Si esta en una factura, lo desactivamos para que luego no pueda ser agregado a detalles de factura....
		
		//Quedarian registro huerfanos o borrarias detalles e informacion importante de facturas...
		try {
			if (!productsRepository.existsById(productId)) throw new IllegalArgumentException("Producto no encontrado...");
			/*if (invoicesService.existsDetailWithProductId(productId) == true ) {
				this.changeProductActiveStatus(productId,false);
				throw new IllegalArgumentException("Producto no puede ser borrado para no romper integridad de datos, el mismo ah sido desactivado...");
			} */
			//Aca vendria solo si existe x lo cual puedo borralos sin problemas.
			productsRepository.deleteById(productId);
		}catch(Exception err) {
			throw err;
		}
		
	}	
	
	
	

}