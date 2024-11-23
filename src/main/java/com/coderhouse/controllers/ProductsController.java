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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="Gestion de productos", description="Endpoints de productos.")
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	ProductsService productsService;
	

	@Operation(summary = "Obtener todos los producto.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> products = productsService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@Operation(summary = "Obtener producto por id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto encontrado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
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
	


	@Operation(summary = "Crear un nuevo producto.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Product agregado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDTO newProduct) {
		try {
			Product createdProduct = productsService.saveProduct(newProduct);
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	


	@Operation(summary = "Actualizacion detalle de productos..")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto actualizado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
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
	
	
	@Operation(summary = "Actualizacion stock de un producto..")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Stock de producto editado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
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
	

	@Operation(summary = "Actualizacion precio de un producto..")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Precio de producto editado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
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
	

	@Operation(summary = "Actualizacion status activo/inactivo de un producto..")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Status de producto editado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
	@PutMapping("/{id}/status")
	public ResponseEntity<Product> updateProductStatus(@PathVariable("id") Long productId,@RequestBody boolean newStatus){
	
		try {
			Product updatedProduct = productsService.changeProductActiveStatus(productId, newStatus);
			return ResponseEntity.ok(updatedProduct);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@Operation(summary = "Eliminacion de un producto..")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
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
