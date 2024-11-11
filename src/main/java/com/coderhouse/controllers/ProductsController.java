package com.coderhouse.controllers;

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

import com.coderhouse.dto.CreateProductDTO;
import com.coderhouse.dto.UpdateProductDetailsDTO;
import com.coderhouse.dto.UpdateProductStockDTO;
import com.coderhouse.dto.UpdateProductPriceDTO;
import com.coderhouse.models.Product;
import com.coderhouse.services.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	ProductsService productsService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> products = productsService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
		try {
			Product searchedProduct = productsService.findById(productId);
			return ResponseEntity.ok(searchedProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDTO newProduct) {
		try {
			Product createdProduct = productsService.saveProduct(newProduct);
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProductDetails(@Valid @PathVariable("id") Long productId,@RequestBody UpdateProductDetailsDTO productNewValues){
		try {
			Product updatedProduct = productsService.updateProductDetails(productId,productNewValues);
			return ResponseEntity.ok(updatedProduct);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/stock/{id}")
	public ResponseEntity<Product> updateProductStock(@PathVariable("id") Long productId,@RequestBody UpdateProductStockDTO newStockDTO){
	
		try {
			Product updatedProduct = productsService.updateProductStock(productId, newStockDTO);
			return ResponseEntity.ok(updatedProduct);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/price/{id}")
	public ResponseEntity<Product> updateProductPrice(@PathVariable("id") Long productId,@RequestBody UpdateProductPriceDTO newPriceDTO){
	
		try {
			Product updatedProduct = productsService.updateProductPrice(productId, newPriceDTO);
			return ResponseEntity.ok(updatedProduct);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId){
		try {
			productsService.deleteProductById(productId);
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
