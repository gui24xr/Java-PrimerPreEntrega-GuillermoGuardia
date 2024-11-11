package com.coderhouse.controllers;


import com.coderhouse.dto.CreateSaleDTO;

import com.coderhouse.models.Sale;
import com.coderhouse.services.SalesService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/sales")
public class SalesControllers {
	
	@Autowired
	SalesService salesService;
	

	@GetMapping
	public ResponseEntity<List<Sale>> getAllSales() {
		try {
			List<Sale> sales = salesService.getAllSales();
			return ResponseEntity.ok(sales);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Sale> geSaleById(@PathVariable("id") Long saleId) {
		try {
			Sale searchedSale = salesService.findById(saleId);
			return ResponseEntity.ok(searchedSale);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@PostMapping
	public ResponseEntity<Sale> createProduct(@Valid @RequestBody CreateSaleDTO newSaleDTO) {
		try {
			Sale createdSale = salesService.createSale(newSaleDTO);
			return ResponseEntity.ok(createdSale);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	
	
	
}
