package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Modelo de detalle de venta de productos en factura.") 
@Table(name="invoice_details")
public class InvoiceDetail {

	@Id
	@Schema(description = "Id de factura.") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long detailId;
	
	@Schema(description = "Precio de venta del producto en el momento que es vendido.") 
	@Column(name = "sale_price",nullable = false) //Precio al momneto de la venta.
	private float salePrice;
	
	@Schema(description = "Cantidad del producto en la factura.") 
	@Column(name = "quantity",nullable = false) //Cantidad de vendidos en la venta.
	private int saleQuantity;
	
	@Schema(description = "Total del detalle/SubTotal del item.") 
	@Column(name = "subtotal_amount",nullable = false) //Cantidad de vendidos en la venta.
	private float subTotalAmount;
	
	
	@Schema(description = "Informacion de factura en la cual se encuentre este detalle/venta.") 
	@ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
	@JsonBackReference
    private Invoice invoice;
	
	@Schema(description = "Informacion de producto vendido en este detalle/venta.") 
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

