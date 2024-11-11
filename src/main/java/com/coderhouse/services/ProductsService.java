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
	

	//Devuelve todos los productos.
	public List<Product> getAllProducts(){
		return productsRepository.findAll();
	}

	//Devuelve un producto por su id.
	public Product findById(Long id){
		return  productsRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
	}
	
	public Product saveProduct(CreateProductDTO newProduct) {
		//Para el producto pedimos code, title,price,stock y le tenemos que setear el createdAt
		Product productForSave = new Product();
		productForSave.setCode(newProduct.getCode());
		productForSave.setTitle(newProduct.getTitle());
		productForSave.setCurrentPrice(newProduct.getCurrentPrice());
		productForSave.setStock(newProduct.getStock());
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
		updatingProduct.setLastUpdate(new Date(0));
		
		return productsRepository.save(updatingProduct);
	}
	
	@Transactional 
	public Product updateProductStock(Long productId,UpdateProductStockDTO newStockDTO) {
		
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
		
		//Aca no valido que sea mayor que cero xq rcibi un dto y eso se valido ahi
			updatingProduct.setStock(newStockDTO.getNewStock());
			updatingProduct.setStockLastUpdate(new Date(0));
			return productsRepository.save(updatingProduct);
	
	}
	
	@Transactional 
	public Product updateProductPrice(Long productId,UpdateProductPriceDTO newPriceDTO) {
		Product updatingProduct = productsRepository.findById(productId)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado..."));
		
		
			updatingProduct.setCurrentPrice(newPriceDTO.getNewCurrentPrice());;
			updatingProduct.setCurrentPriceLastUpdate(new Date(0));
			return productsRepository.save(updatingProduct);
		
	}
	
	
	public void deleteProductById(Long productId) {
		if (!productsRepository.existsById(productId)) throw new IllegalArgumentException("Producto no encontrado...");
		//Aca vendria solo si existe x lo cual puedo borralos sin problemas.
		productsRepository.deleteById(productId);
	}	
	
	
	

}