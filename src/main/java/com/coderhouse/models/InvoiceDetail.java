package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="invoice_details")
public class InvoiceDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long detailId;
	
	@Column(name = "sale_price",nullable = false) //Precio al momneto de la venta.
	private float salePrice;
	
	@Column(name = "quantity",nullable = false) //Cantidad de vendidos en la venta.
	private float saleQuantity;
	
		
	@ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
	@JsonBackReference
    private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	

	public InvoiceDetail(float salePrice, float saleQuantity, Invoice invoice, Product product) {
		super();
		this.salePrice = salePrice;
		this.saleQuantity = saleQuantity;
		this.invoice = invoice;
		this.product = product;
	}



	public long getDetailId() {
		return detailId;
	}



	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}



	public float getSalePrice() {
		return salePrice;
	}



	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}



	public float getSaleQuantity() {
		return saleQuantity;
	}



	public void setSaleQuantity(float saleQuantity) {
		this.saleQuantity = saleQuantity;
	}



	public Invoice getInvoice() {
		return invoice;
	}



	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}

