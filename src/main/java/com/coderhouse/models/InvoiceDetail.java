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
	private int saleQuantity;
	
	@Column(name = "subtotal_amount",nullable = false) //Cantidad de vendidos en la venta.
	private float subTotalAmount;
	
	
		
	@ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
	@JsonBackReference
    private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	// Constructor sin parámetros (requerido por JPA y Jackson)
    public InvoiceDetail() {
    }


    //Este constructor construye la factura con subtotal.
	public InvoiceDetail(float salePrice, int saleQuantity, Invoice invoice, Product product) {
		super();
		this.salePrice = salePrice;
		this.saleQuantity = saleQuantity;
		this.subTotalAmount = salePrice * saleQuantity;
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


	public int getSaleQuantity() {
		return saleQuantity;
	}


	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}


	public float getSubTotalAmount() {
		return subTotalAmount;
	}


	public void setSubTotalAmount(float subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
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

